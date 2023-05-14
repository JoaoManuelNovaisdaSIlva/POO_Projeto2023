package Artigos;

import Encomendas.Dimenssao;
import Encomendas.Transportadora;

import java.util.Objects;

public class Malas extends Artigos{
    private Dimenssao dimensions; // 1 - pequena, 2 - media, 3 - grande
    private TexturaMala texture;
    private int collectionYear;

    public Malas(boolean used, String desc, float price, float discount, Estado state, int numUsers, Transportadora t, int stock, Dimenssao dimensions, TexturaMala texture, int collectionYear) {
        super(used, desc, price, discount, state, numUsers, t, stock);
        this.dimensions = dimensions;
        this.texture = texture;
        this.collectionYear = collectionYear;
    }

    public Malas(Malas a) {
        super(a);
        this.dimensions = a.dimensions;
        this.texture = a.texture;
        this.collectionYear = a.collectionYear;
    }

    public Dimenssao getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimenssao dimensions) {
        this.dimensions = dimensions;
    }

    public TexturaMala getTexture() {
        return texture;
    }

    public void setTexture(TexturaMala texture) {
        this.texture = texture;
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
        return Objects.hash(super.hashCode(),dimensions, texture, collectionYear);
    }
    @Override
    public Malas clone(){
        return new Malas(this);
    }
    @Override
    public String toString(){
        return super.toString() + "Mala do ano de coleção: " + this.collectionYear + " --> ";
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        if(!super.equals(o)) return false;
        Malas m = (Malas) o;
        return this.dimensions == m.dimensions && Objects.equals(this.texture, m.texture) && this.collectionYear == m.collectionYear && super.equals(m);
    }
}
