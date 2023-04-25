package Encomendas;

import Artigos.Artigos;

import java.util.ArrayList;
import java.util.Random;

public class Transportadora {
    static float valorExpedicaoPequena = 2.00f;
    static float valorExpedicaoMedia = 4.20f;
    static float getValorExpedicaoGrande = 6.00f;
    static float imposto = 0.23f;

    private static int counter=0;

    private int id;
    private String nome;
    private int nif;
    private String email;
    private ArrayList<Artigos> artigosAssociados;
    private float precoExpedicao;
    private float margemLucro;

    public Transportadora(){
        counter++;
        this.id = counter;
        this.nome = "";
        this.nif = 0;
        this.email = "";
        this.artigosAssociados = new ArrayList<>();
        this.precoExpedicao = 0.00f;

        Random rand = new Random();
        this.margemLucro = 1.01f + rand.nextFloat() * (1.50f-1.01f); // numero aleatorio entre 1.01 e 1.50
    }

    public Transportadora(String nome, int nif, String email, ArrayList<Artigos> artigos, float preco){
        counter++;
        this.id = counter;
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.artigosAssociados = new ArrayList<>(artigos);
        this.precoExpedicao = preco;
    }

    public Transportadora(Transportadora t){
        counter++;
        this.id = counter;
        this.nome = t.nome;
        this.nif = t.nif;
        this.email = t.email;
        this.artigosAssociados = new ArrayList<>(t.artigosAssociados);
        this.precoExpedicao = t.precoExpedicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Artigos> getArtigosAssociados() {
        return new ArrayList<>(artigosAssociados);
    }

    public void setArtigosAssociados(ArrayList<Artigos> artigosAssociados) {
        this.artigosAssociados = new ArrayList<>(artigosAssociados);
    }

    public float getPrecoExpedicao() {
        return precoExpedicao;
    }

    public void setPrecoExpedicao(float precoExpedicao) {
        this.precoExpedicao = precoExpedicao;
    }

    public float getMargemLucro(){
        return this.margemLucro;
    }

    public void setMargemLucro(float margem){
        this.margemLucro = margem;
    }

    public void calculaPrecoExpedicao(Transportadora t, Encomenda e){
        this.precoExpedicao = (e.getPrecoFinal()*t.margemLucro*(1+imposto));
        e.setCustosExpedicao(this.precoExpedicao);
    }
}
