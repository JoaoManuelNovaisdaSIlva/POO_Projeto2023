package Encomendas;

import Artigos.*;
import Utilizadores.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Encomenda implements Serializable {
    private Comprador comprador;
    private ArrayList<Artigos> artigos;
    private Dimenssao dimenssao; // <=1 artigos - pequena; >=2 & <=5 - media; >= 5 - grande
    private float precoProdutos; // preço dos produtos com taxas de satisfação
    private float custosExpedicao; // isto fica separado ou junto com o proço final???
    private EstadoEncomenda estado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEntrega;

    public Encomenda(){
        this.comprador = new Comprador();
        this.artigos = new ArrayList<>();
        this.dimenssao = Dimenssao.Pequeno;
        this.precoProdutos = 0.00f;
        this.custosExpedicao = 0.00f;
        this.estado = EstadoEncomenda.Pendente;
        this.dataCriacao = LocalDateTime.now();
        this.dataEntrega = null;
    }

    public Encomenda(Comprador c, ArrayList<Artigos> artigos){
        this.comprador = c;
        this.artigos = new ArrayList<>(artigos);

        if(artigos.size() <= 1) this.dimenssao = Dimenssao.Pequeno;
        else if (artigos.size() <= 5) this.dimenssao = Dimenssao.Medio;
        else this.dimenssao = Dimenssao.Grande;

        this.precoProdutos = calculaPrecos(artigos);
        this.custosExpedicao = calculaPrecoExpedicao(artigos, dimenssao);
        this.estado = EstadoEncomenda.Pendente;
        this.dataCriacao = LocalDateTime.now();
        this.dataEntrega = null;
    }

    public Encomenda(Encomenda e){
        this.comprador = e.comprador;
        this.artigos = new ArrayList<>(e.artigos);
        this.dimenssao = e.dimenssao;
        this.precoProdutos = e.precoProdutos;
        this.custosExpedicao = e.custosExpedicao;
        this.estado = e.estado;
        this.dataCriacao = e.dataCriacao;
        this.dataEntrega = null;
    }

    public Comprador getComprador(){
        return this.comprador;
    }

    public void setComprador(Comprador c){ // Queremos que as mudanças feitas neste comprador noutro lado sejam feitas também na variavel comprador da encomenda
        this.comprador = c;
    }

    public ArrayList<Artigos> getArtigos() {
        return new ArrayList<>(artigos);
    }

    public void setArtigos(ArrayList<Artigos> artigos) {
        this.artigos = new ArrayList<>(artigos);
    }

    public Dimenssao getDimenssao() {
        return dimenssao;
    }

    public void setDimenssao(Dimenssao dimenssao) {
        this.dimenssao = dimenssao;
    }

    public float getPrecoProdutos() {
        return precoProdutos;
    }

    public void setPrecoProdutos(float precoProdutos) {
        this.precoProdutos = precoProdutos;
    }

    public float getCustosExpedicao() {
        return custosExpedicao;
    }

    public void setCustosExpedicao(float custosExpedicao) {
        this.custosExpedicao = custosExpedicao;
    }

    public EstadoEncomenda getEstado(){
        return this.estado;
    }

    public void setEstado(EstadoEncomenda e){
        this.estado = e;
    }

    public LocalDateTime getDataCriacao(){
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime l){
        this.dataCriacao = l;
    }

    public LocalDateTime getDataEntrega(){
        return this.dataEntrega;
    }

    public void setDataEntrega(LocalDateTime l){
        this.dataEntrega = l;
    }

    public void adicionaArtigoAEncomenda(Artigos a){
        if(this.estado == EstadoEncomenda.Pendente) {
            this.artigos.add(a);
            this.precoProdutos = calculaPrecos(this.artigos); // Atualizar o preço
            this.custosExpedicao = calculaPrecoExpedicao(this.artigos, this.dimenssao);

            if (artigos.size() <= 1) this.dimenssao = Dimenssao.Pequeno;
            else if (artigos.size() <= 5) this.dimenssao = Dimenssao.Medio;
            else this.dimenssao = Dimenssao.Grande;
            System.out.println("O artigo " + a + " foi adicionado com sucesso!");
        }else System.out.println("Não pode adicionar novos artigos à encomenda porque esta está no estado: " + this.estado);
    }

    public void removeArtigoAEncomenda(Artigos a){
        if(this.estado == EstadoEncomenda.Pendente) {
            this.artigos.remove(a);
            this.precoProdutos = calculaPrecos(this.artigos);
            this.custosExpedicao = calculaPrecoExpedicao(this.artigos, this.dimenssao);

            if (artigos.size() <= 1) this.dimenssao = Dimenssao.Pequeno;
            else if (artigos.size() <= 5) this.dimenssao = Dimenssao.Medio;
            else this.dimenssao = Dimenssao.Grande;
            System.out.println("O artigo " + a + " foi removido com sucesso!");
        } else System.out.println("Não pode remover artigos à encomenda porque esta está no estado: " + this.estado);
    }

    public void pagarEncomenda(){
        if(this.estado == EstadoEncomenda.Pendente) {
            this.estado = EstadoEncomenda.Finalizada;
            comprador.adicionarComprados(this.artigos);
            System.out.println("Valor a pagar: " + this.custosExpedicao);
            System.out.println("A sua encomenda foi paga com sucesso!");
        }else System.out.println("A sua encomenda já se encontra no estado: " + this.estado);
    }

    public void devolucao(){
        if(this.estado == EstadoEncomenda.Entregue) {
            Duration duration = Duration.between(this.dataEntrega, LocalDateTime.now());
            long horasPassadas = duration.toHours();
            if (horasPassadas >= 48) {
                System.out.println("Desculpe mas já passaram 48h desde a sua entrega, não pode devolver a encomenda!");
                return;
            } else {
                System.out.println("A sua encomenda será devolvida e o valor da venda será devolvido!");
                System.out.println("Valor devolvido: " + this.custosExpedicao);
                this.comprador.removerComprados(this.artigos);
            }
        } else System.out.println("Não é possível devolver a encomenda porque esta encontra-se no estado: " + this.estado);
    }

    public float calculaPrecoExpedicao(ArrayList<Artigos> a, Dimenssao d){
        HashMap<Transportadora, ArrayList<Artigos>> dividida = divideEmHashMap(a);
        HashMap<Transportadora, Float> preco = calculaPrecoArtigosDeTransportadoraEncomenda(dividida);
        float precoExp = calculaCustosDeExpedicaoTransportadoraEncomenda(preco);

        if(d == Dimenssao.Pequeno) precoExp += Transportadora.valorExpedicaoPequena;
        else if(d == Dimenssao.Medio) precoExp += Transportadora.valorExpedicaoMedia;
        else if(d == Dimenssao.Grande) precoExp += Transportadora.getValorExpedicaoGrande;

        return precoExp;
    }

    public HashMap<Transportadora, ArrayList<Artigos>> divideEmHashMap(ArrayList<Artigos> artigos){
        HashMap<Transportadora, ArrayList<Artigos>> dividida = new HashMap<>();
        for(Artigos a : artigos){
            if(dividida.containsKey(a.getTransportadoraAssociada())){
                ArrayList<Artigos> art = dividida.get(a.getTransportadoraAssociada());
                art.add(a);
                dividida.put(a.getTransportadoraAssociada(), art);
            }else{
                ArrayList<Artigos> art1 = new ArrayList<>();
                art1.add(a);
                dividida.put(a.getTransportadoraAssociada(), art1);
            }
        }
        return dividida;
    }

    public HashMap<Transportadora, Float> calculaPrecoArtigosDeTransportadoraEncomenda(HashMap<Transportadora, ArrayList<Artigos>> artigos){
        float preco;
        HashMap<Transportadora, Float> precosTransportadoras = new HashMap<>();

        for(Transportadora trasporte : artigos.keySet()) {
            preco = calculaPrecos(artigos.get(trasporte));
            precosTransportadoras.put(trasporte, preco);
        }
        return precosTransportadoras;
    }

    public float calculaCustosDeExpedicaoTransportadoraEncomenda(HashMap<Transportadora, Float> custos){
        float somaPrecoExpedicao = 0.00f;
        for(Transportadora t : custos.keySet()){
            if(t instanceof TransportadoraPremium){
                TransportadoraPremium t1 = (TransportadoraPremium) t;
                somaPrecoExpedicao += t1.calculaFormulaPremium(t1, custos.get(t));
            }else if(t instanceof Transportadora){
                somaPrecoExpedicao += t.calculaFormula(t, custos.get(t));
            }
        }
        return somaPrecoExpedicao;
    }

    public float calculaPrecos(ArrayList<Artigos> artigos){
        float soma = 0.00f;
        for(Artigos a : artigos){
            soma += a.getPrice();
            if(a.getIsUsed()) soma+= 0.25f;
            else soma += 0.50f;
        }
        return soma;
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    @Override
    public String toString(){
        return "Encomenda de: " + this.comprador + ";\nCom os seguintes artigos: " + this.artigos + ";\nDimenssão: " + this.dimenssao
                + ";\nPreço final: " + this.precoProdutos + ";\nPreço de Expedição: " + custosExpedicao + ";\nEstado da encomenda: " + this.estado +
                ";\nData de criação: " + this.dataCriacao + ";\nData de entrega: " + this.dataEntrega;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        return this.comprador == ((Encomenda) o).comprador && this.artigos == ((Encomenda) o).artigos &&  this.dimenssao == ((Encomenda) o).dimenssao &&
                this.precoProdutos == ((Encomenda) o).precoProdutos && this.estado == ((Encomenda) o).estado && this.dataCriacao == ((Encomenda) o).dataCriacao
                && this.dataEntrega == ((Encomenda) o).dataEntrega;
    }
}