package Encomendas;

import Artigos.*;
import Utilizadores.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Encomenda {
    private Comprador comprador;
    private ArrayList<Artigos> artigos;
    private Dimenssao dimenssao; // <=1 artigos - pequena; >=2 & <=5 - media; >= 5 - grande
    private float precoFinal;
    private float custosExpedicao; // isto fica separado ou junto com o proço final???
    private EstadoEncomenda estado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEntrega;

    public Encomenda(){
        this.artigos = new ArrayList<>();
        this.dimenssao = Dimenssao.Pequeno;
        this.precoFinal = 0.00f;
        this.custosExpedicao = 0.00f;
        this.estado = EstadoEncomenda.Pendente;
        this.dataCriacao = LocalDateTime.now();
        this.dataEntrega = null;
    }

    public Encomenda(ArrayList<Artigos> artigos){
        this.artigos = new ArrayList<>(artigos);

        if(artigos.size() <= 1) this.dimenssao = Dimenssao.Pequeno;
        else if (artigos.size() <= 5) this.dimenssao = Dimenssao.Medio;
        else this.dimenssao = Dimenssao.Grande;

        this.precoFinal = calculaPrecoFinal(artigos);
        this.estado = EstadoEncomenda.Pendente;
        this.dataCriacao = LocalDateTime.now();
        this.dataEntrega = null;
    }

    public Encomenda(Encomenda e){
        this.artigos = new ArrayList<>(e.artigos);
        this.dimenssao = e.dimenssao;
        this.precoFinal = e.precoFinal;
        this.custosExpedicao = e.custosExpedicao;
        this.estado = e.estado;
        this.dataCriacao = e.dataCriacao;
        this.dataEntrega = null;
    }

    public float calculaPrecoFinal(ArrayList<Artigos> artigos){ // Falta somar aqui as taxas da entregadora e tb a taxa de satisfação
        float soma=0.00f;
        for(Artigos a : artigos){
            soma += a.getPrice();
        }
        return soma;
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

    public float getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(float precoFinal) {
        this.precoFinal = precoFinal;
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

    public void adicionaArtigoAEncomenda(Encomenda e, Artigos a){
        e.artigos.add(a);
        e.precoFinal = calculaPrecoFinal(e.artigos); // Atualizar o preço
    }

    public void removeArtigoAEncomenda(Encomenda e, Artigos a){
        this.artigos.remove(a);
        System.out.println("O artigo " + a + " foi removido com sucesso");
    }

    public void pagarEncomenda(){
        this.estado = EstadoEncomenda.Finalizada;
        comprador.adicionarComprados(this.artigos);
        System.out.println("A sua encomenda foi paga com sucesso!");
    }

    public void devolucao(Encomenda e){
        Duration duration = Duration.between(e.dataEntrega, LocalDateTime.now());
        long horasPassadas = duration.toHours();
        if(horasPassadas >= 48){
            System.out.println("Desculpe mas já passaram 48h desde a sua entrega, não pode devolver a encomenda!");
            return;
        }
        else{
            System.out.println("A sua encomenda será devolvida e o valor da venda será devolvido!");
            System.out.println("Valor devolvido: " + this.precoFinal);
            e.comprador.removerComprados(e.artigos);
        }
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    @Override
    public String toString(){
        return "Encomenda de: " + this.comprador + ";\nCom os seguintes artigos: " + this.artigos + ";\nDimenssão: " + this.dimenssao
                + ";\nPreço final: " + this.precoFinal + ";\nEstado da encomenda: " + this.estado + ";\nData de criação: " + this.dataCriacao
                + ";\nData de entrega: " + this.dataEntrega;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        return this.comprador == ((Encomenda) o).comprador && this.artigos == ((Encomenda) o).artigos &&  this.dimenssao == ((Encomenda) o).dimenssao &&
                this.precoFinal == ((Encomenda) o).precoFinal && this.estado == ((Encomenda) o).estado && this.dataCriacao == ((Encomenda) o).dataCriacao
                && this.dataEntrega == ((Encomenda) o).dataEntrega;
    }
}
