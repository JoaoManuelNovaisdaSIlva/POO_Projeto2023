package Utilizadores;

import Artigos.Artigos;

import java.util.ArrayList;

public class Comprador extends Utilizador{
    private ArrayList<Artigos> comprados;

    public Comprador(){
        super();
        this.comprados = new ArrayList<Artigos>();
    }

    public Comprador(String email, String nome, String morada, int nif) {
        super(email, nome, morada, nif);
        this.comprados = new ArrayList<>();
    }

    public Comprador(Utilizador u, ArrayList<Artigos> comprados){
        super(u);
        this.comprados = new ArrayList<>(comprados);
    }
    public Comprador(String email, String nome, String morada, int nif, ArrayList<Artigos> comprados) {
        super(email, nome, morada, nif);
        this.comprados = new ArrayList<>(comprados);
    }

    public Comprador(Comprador u) {
        super(u);
        this.comprados = new ArrayList<>(u.comprados);
    }

    public ArrayList<Artigos> getComprados() {
        return new ArrayList<>(comprados);
    }

    public void setComprados(ArrayList<Artigos> comprados) {
        this.comprados = new ArrayList<>(comprados);
    }

    public void adicionarComprados(ArrayList<Artigos> compras){
        this.comprados.addAll(compras);
    }

    public void removerComprados(ArrayList<Artigos> devolvidos){
        this.comprados.removeAll(devolvidos);
    }

    public Utilizador clone(){
        return new Comprador(this);
    }

    @Override
    public String toString(){
        return super.toString() + "Items comprados : " + comprados;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        if(!super.equals(o)) return false;

        Comprador v = (Comprador) o;
        return super.equals(v) && this.comprados == v.comprados;
    }
}
