package Artigos;

import java.util.Objects;

public class SapatilhasPremium extends Sapatilhas{
    private String authors;

    public SapatilhasPremium(boolean used, String desc, float price, float discount, int state, int numUsers, int size, boolean laces, String colour, int year, String authors) {
        super(used, desc, price, discount, state, numUsers, size, laces, colour, year);
        this.authors = authors;
    }

    public SapatilhasPremium(SapatilhasPremium s) {
        super(s);
        this.authors = s.authors;
    }

    public String getAuthors(){
        return this.authors;
    }

    public void setAuthors(String au){
        this.authors = au;
    }

    public void atualizarPrecoSapatilhasPremium(SapatilhasPremium s, int currentYear){
        float newPrice = (s.getPrice() + (s.getPrice()*0.10f*(currentYear- s.getCollectionYear())));
        s.setPrice(newPrice);
    }
    @Override
    public SapatilhasPremium clone(){
        return new SapatilhasPremium(this);
    }
    @Override
    public String toString(){
        return super.toString() + "; Sapatilha premium com autor: " + authors;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        if(!super.equals(o)) return false;

        SapatilhasPremium s = (SapatilhasPremium) o;
        return Objects.equals(this.authors, s.authors) && super.equals(s);
    }
}
