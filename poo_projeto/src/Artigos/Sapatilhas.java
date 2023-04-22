package Artigos;

import java.util.Objects;

public class Sapatilhas extends Artigos{
    private int shoeSize;
    private boolean hasLaces;
    private String color;
    private int collectionYear;

    public Sapatilhas(boolean used, String desc, float price, float discount, int state, int numUsers, int size, boolean laces, String colour, int year){
        super(used, desc, price, discount, state, numUsers);
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

    public void atualizaPrecoDesconto(Sapatilhas s, int currentYear){
        if(s.getIsUsed()){
            float newDiscount =  (s.getDicountPrice() + (s.getDicountPrice() + (s.getDicountPrice()*0.05f*(currentYear-s.collectionYear))));
            if(newDiscount > s.getPrice()) return;
            else s.setDicountPrice(newDiscount);
        } else if (s.getDicountPrice() > 0) s.setDicountPrice(0);
    }
    @Override
    public Sapatilhas clone(){
        return new Sapatilhas(this);
    }
    @Override
    public String toString(){
        return "Sapatilha de Tamanho: " + shoeSize + "; Com atacadores: " + hasLaces + "; Cor: " + color + "; Coleção do ano: " + collectionYear;
    }

    public boolean equals(Object o){
        if(this == o) return true;

        if(o == null || o.getClass() != this.getClass()) return false;

        return this.shoeSize == ((Sapatilhas) o).shoeSize && this.hasLaces == ((Sapatilhas) o).hasLaces
                && Objects.equals(this.color, ((Sapatilhas) o).color) && this.collectionYear == ((Sapatilhas) o).collectionYear;
    }
}
