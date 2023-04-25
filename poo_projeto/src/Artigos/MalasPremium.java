package Artigos;

import Encomendas.Dimenssao;

import java.util.Objects;

public class MalasPremium extends Malas{
    private String author;

    public MalasPremium(boolean used, String desc, float price, float discount, int state, int numUsers, Dimenssao dimensions, String texture, int collectionYear, String author) {
        super(used, desc, price, discount, state, numUsers, dimensions, texture, collectionYear);
        this.author = author;
    }

    public MalasPremium(MalasPremium a) {
        super(a);
        this.author = a.author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void atualizaPrecoMalasPremium(MalasPremium m, int currentYear){
        float newPrice = m.getPrice();
        if(m.getDimensions() == Dimenssao.Pequeno){
            newPrice = (m.getPrice() + (m.getPrice()*0.05f*(currentYear- m.getCollectionYear())));
        }else if (m.getDimensions() == Dimenssao.Medio){
            newPrice = (m.getPrice() + (m.getPrice()*0.10f*(currentYear- m.getCollectionYear())));
        }else if (m.getDimensions() == Dimenssao.Grande){
            newPrice = (m.getPrice() + (m.getPrice()*0.20f*(currentYear- m.getCollectionYear())));
        }
        m.setPrice(newPrice);
    }
    @Override
    public MalasPremium clone(){
        return new MalasPremium(this);
    }
    @Override
    public String toString(){
        return super.toString() + "; Mala premium com autor: " + author;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        if(!super.equals(o)) return false;

        MalasPremium m = (MalasPremium) o;
        return Objects.equals(this.author, m.author) && super.equals(m);
    }
}
