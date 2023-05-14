package Artigos;

import Encomendas.Transportadora;

import java.util.Objects;

public class Sapatilhas extends Artigos{
    private int shoeSize;
    private boolean hasLaces;
    private String color;
    private int collectionYear;

    public Sapatilhas(boolean used, String desc, float price, float discount, Estado state, int numUsers, Transportadora t, int stock, int size, boolean laces, String colour, int year){
        super(used, desc, price, discount, state, numUsers, t, stock);
        this.shoeSize = size;
        this.hasLaces = laces;
        this.color = colour;
        this.collectionYear = year;
    }

    public Sapatilhas(Sapatilhas s){
        super(s);
        this.shoeSize = s.shoeSize;
        this.hasLaces = s.hasLaces;
        this.color = s.color;
        this.collectionYear = s.collectionYear;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    public boolean isHasLaces() {
        return hasLaces;
    }

    public void setHasLaces(boolean hasLaces) {
        this.hasLaces = hasLaces;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCollectionYear() {
        return collectionYear;
    }

    public void setCollectionYear(int collectionYear) {
        this.collectionYear = collectionYear;
    }

    public void atualizaPrecoDesconto(int currentYear){
        if(this.getIsUsed()){
            float newDiscount = (this.getDicountPrice() + (this.getDicountPrice()*0.05f*(currentYear-this.collectionYear)));
            if(newDiscount > this.getPrice()) System.out.println("As sapatilhas não podem ter mais disconto!");
            else this.setDicountPrice(newDiscount);
        }else System.out.println("O artigo não é usado logo não aumenta o desconto");
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), shoeSize, hasLaces, color, collectionYear);
    }
    @Override
    public Sapatilhas clone(){
        return new Sapatilhas(this);
    }
    @Override
    public String toString(){
        return super.toString() + "Sapatilha do ano de coleção: " + this.collectionYear + " --> ";
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        if(!super.equals(o)) return false;

        Sapatilhas s = (Sapatilhas) o;
        return this.shoeSize == s.shoeSize && this.hasLaces == s.hasLaces && this.color == s.color && this.collectionYear == s.collectionYear && super.equals(s);
    }
}
