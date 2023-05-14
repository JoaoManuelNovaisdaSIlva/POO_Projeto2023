package Utilizadores;

import java.io.Serializable;

public class Utilizador implements Serializable {
    private static int counter=0;
    private int id;
    private String email;
    private String nome;
    private String morada;
    private int nif;

    public Utilizador(){
        counter++;
        this.id = counter;
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.nif = 0;
    }

    public Utilizador(String email, String nome, String morada, int nif){
        counter++;
        this.id = counter;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
    }

    public Utilizador(Utilizador u){
        counter++;
        this.id = counter;
        this.email = u.email;
        this.nome = u.nome;
        this.morada = u.morada;
        this.nif = u.nif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public Utilizador clone(){
        return new Utilizador(this);
    }

    @Override
    public String toString(){
        return "Utilizador id: " + this.id + " ; " + "Nome: " + this.nome + " ; ";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;

        if(o == null || o.getClass() != this.getClass()) return false;

        return this.id == ((Utilizador) o).id && this.email == ((Utilizador) o).email && this.nome == ((Utilizador) o).nome
                && this.morada == ((Utilizador) o).morada && this.nif == ((Utilizador) o).nif;
    }
}
