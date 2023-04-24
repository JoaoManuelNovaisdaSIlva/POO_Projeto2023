package Utilizadores;

import Artigos.Artigos;

import java.util.ArrayList;

public class Vendedor extends Utilizador{
    private ArrayList<Artigos> emVenda;
    private ArrayList<Artigos> vendidos;

    public Vendedor(String email, String nome, String morada, int nif){
        super(email, nome, morada, nif);
        this.emVenda = new ArrayList<>();
        this.vendidos = new ArrayList<>();
    }

    public Vendedor(String email, String nome, String morada, int nif, ArrayList<Artigos> venda, ArrayList<Artigos> vendidos) {
        super(email, nome, morada, nif);
        this.emVenda = new ArrayList<>(venda);
        this.vendidos = new ArrayList<>(vendidos);
    }

    public Vendedor(Vendedor u) {
        super(u);
        this.emVenda = new ArrayList<>(u.emVenda);
        this.vendidos = new ArrayList<>(u.vendidos);
    }

    public ArrayList<Artigos> getEmVenda() {
        return new ArrayList<>(emVenda);
    }

    public void setEmVenda(ArrayList<Artigos> emVenda) {
        this.emVenda = new ArrayList<>(emVenda);
    }

    public ArrayList<Artigos> getVendidos() {
        return new ArrayList<>(vendidos);
    }

    public void setVendidos(ArrayList<Artigos> vendidos) {
        this.vendidos = new ArrayList<>(vendidos);
    }

    public Utilizador clone(){
        return new Vendedor(this);
    }

    @Override
    public String toString(){
        return super.toString() + "Items em vande: " + emVenda + ";\nItems vendidos: " + vendidos;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        if(!super.equals(o)) return false;

        Vendedor v = (Vendedor) o;
        return super.equals(v) && this.emVenda == v.emVenda && this.vendidos == v.vendidos;
    }
}
