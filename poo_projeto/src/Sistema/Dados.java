package Sistema;

import Artigos.Artigos;
import Encomendas.Encomenda;
import Encomendas.Transportadora;
import Utilizadores.Comprador;
import Utilizadores.Utilizador;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dados implements Serializable {
    private HashMap<String, Transportadora> transportadoras; // key é uma string com o nome do objecto mais o numero (diferente do id +/-)
    private HashMap<String, Utilizador> utilizadores;
    private HashMap<String, Artigos> artigos;
    private HashMap<String, Encomenda> encomendas;
    private LocalDateTime dateTimeDoSistema;
    private HashMap<Integer, String> novasFormulas; // key é o id da transportadora
    private HashMap<Integer, Float> novasMargens; // key é o id da transportadora
    private List<Artigos> vendidos;
    private List<Encomenda> encomendasFinalizadas;
    private HashMap<Comprador, String> faturas;

    public Dados(){
        this.transportadoras = new HashMap<>();
        this.utilizadores = new HashMap<>();
        this.artigos = new HashMap<>();
        this.encomendas = new HashMap<>();
        this.dateTimeDoSistema = LocalDateTime.now();
        this.novasFormulas = new HashMap<>();
        this.novasMargens = new HashMap<>();
        this.vendidos = new ArrayList<>();
        this.encomendasFinalizadas = new ArrayList<>();
        this.faturas = new HashMap<>();
    }

    public Dados(HashMap<String, Transportadora> transportadoras, HashMap<String, Utilizador> utilizadores, HashMap<String, Artigos> artigos, HashMap<String, Encomenda> encomendas, LocalDateTime data, HashMap<Integer, String> formulas, HashMap<Integer, Float> margens, List<Artigos> vendidos, List<Encomenda> finalizadas, HashMap<Comprador, String> fatura){
        this.transportadoras = transportadoras;
        this.utilizadores = utilizadores;
        this.artigos = artigos;
        this.encomendas = encomendas;
        this.dateTimeDoSistema = data;
        this.novasFormulas = formulas;
        this.novasMargens = margens;
        this.vendidos = vendidos;
        this.encomendasFinalizadas = finalizadas;
        this.faturas = fatura;
    }

    public Dados(Dados d){
        this.transportadoras = d.transportadoras;
        this.utilizadores = d.utilizadores;
        this.artigos = d.artigos;
        this.encomendas = d.encomendas;
        this.dateTimeDoSistema = d.dateTimeDoSistema;
        this.novasFormulas = d.novasFormulas;
        this.novasMargens = d.novasMargens;
        this.vendidos = d.vendidos;
        this.encomendasFinalizadas = d.encomendasFinalizadas;
        this.faturas = d.faturas;
    }

    public HashMap<String, Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(HashMap<String, Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public HashMap<String, Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(HashMap<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public HashMap<String, Artigos> getArtigos() {
        return artigos;
    }

    public void setArtigos(HashMap<String, Artigos> artigos) {
        this.artigos = artigos;
    }

    public HashMap<String, Encomenda> getEncomendas() {
        return encomendas;
    }

    public void setEncomendas(HashMap<String, Encomenda> encomendas) {
        this.encomendas = encomendas;
    }

    public LocalDateTime getDateTimeDoSistema() {
        return dateTimeDoSistema;
    }

    public void setDateTimeDoSistema(LocalDateTime dateTimeDoSistema) {
        this.dateTimeDoSistema = dateTimeDoSistema;
    }

    public HashMap<Integer, String> getNovasFormulas() {
        return novasFormulas;
    }

    public void setNovasFormulas(HashMap<Integer, String> novasFormulas) {
        this.novasFormulas = novasFormulas;
    }

    public HashMap<Integer, Float> getNovasMargens() {
        return novasMargens;
    }

    public void setNovasMargens(HashMap<Integer, Float> novasMargens) {
        this.novasMargens = novasMargens;
    }

    public List<Artigos> getVendidos() {
        return vendidos;
    }

    public void setVendidos(List<Artigos> vendidos) {
        this.vendidos = vendidos;
    }

    public List<Encomenda> getEncomendasFinalizadas() {
        return encomendasFinalizadas;
    }

    public void setEncomendasFinalizadas(List<Encomenda> encomendasFinalizadas) {
        this.encomendasFinalizadas = encomendasFinalizadas;
    }

    public HashMap<Comprador, String> getFaturas() {
        return faturas;
    }

    public void setFaturas(HashMap<Comprador, String> faturas) {
        this.faturas = faturas;
    }

    public void avancaTempo(int dias){
        this.dateTimeDoSistema = dateTimeDoSistema.plusDays(dias);
    }

    /**public void atualizarComprador(Comprador c, String email, String nome, String morada, int nif){
        for(Utilizador compradores : this.utilizadores.values()){
            if(compradores instanceof Comprador && compradores.getId() == c.getId()){
                compradores = c;
            }
        }
    }**/

    /** Não é preciso
     * public Comprador getCompradorByEmail(String email){
        for(Utilizador u : this.utilizadores.values()){
            if(u instanceof Comprador && email.equals(u.getEmail())){
                return (Comprador) u;
            }
        }
        System.out.println("Comprador não encontrado!");
        return null;
    }**/

    public Dados clone(Dados d){
        return new Dados(this);
    }

    @Override
    public String toString(){
        return "Transportadoras: " + this.transportadoras + ";\nUtilizadores: " + this.utilizadores + ";\nArtigos: " + this.artigos + ";\nEncomendas: " + this.encomendas;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        return this.transportadoras == ((Dados) o).transportadoras && this.utilizadores == ((Dados) o).utilizadores && this.artigos == ((Dados) o).artigos && this.encomendas == ((Dados) o).encomendas;
    }
}
