package Artigos;

import Encomendas.Transportadora;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Artigos implements Serializable {
    private boolean isUsed; // Sim, é possivel haver um Artigo que seja Novo mas tenha os valores de usedState e numOfUsers, mas não importa porque depois nao vao ser usados
    private String description;
    private String barCode;
    private float price;
    private float discountPrice;
    private Estado usedState;
    private int numOfUsers;
    private Transportadora transportadoraAssociada;

    public Artigos(boolean used, String desc, float price, float discount, Estado state, int numUsers, Transportadora t){
        this.isUsed = used;
        this.description = desc;
        this.barCode = Integer.toString(this.hashCode()); // Gerar uma string unica a partir do hascode do objeto
        this.price = price;
        this.discountPrice = discount;

        //if(!used) this.discountPrice = 0;
        //else this.discountPrice = price - (price/(numUsers*state));

        this.usedState = state;
        this.numOfUsers = numUsers;
        this.transportadoraAssociada = t;
    }

    public Artigos(Artigos a){
        this.isUsed = a.isUsed;
        this.description = a.description;
        this.barCode = a.barCode;
        this.price = a.price;
        this.discountPrice = a.discountPrice;
        this.usedState = a.usedState;
        this.numOfUsers = a.numOfUsers;
        this.transportadoraAssociada = a.transportadoraAssociada;
    }

    public boolean getIsUsed(){
        return this.isUsed;
    }

    public void setUsed(boolean u){
        this.isUsed = u;
    }

    public Estado getUsedState(){
        return this.usedState;
    }

    public void setUsedState(Estado u){
        this.usedState = u;
    }

    public int getNumOfUseres(){
        return this.numOfUsers;
    }

    public void setNumOfUseres(int u){
        this.numOfUsers = u;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDicountPrice() {
        return discountPrice;
    }

    public void setDicountPrice(float dicountPrice) {
        this.discountPrice = dicountPrice;
    }

    public Transportadora getTransportadoraAssociada(){
        return this.transportadoraAssociada;
    }

    public void setTransportadoraAssociada(Transportadora t){
        this.transportadoraAssociada = t;
    }

    @Override
    public int hashCode(){
        return Objects.hash(isUsed, description, barCode, price, discountPrice, usedState, numOfUsers, transportadoraAssociada);
    }
    @Override
    public Artigos clone(){
        return new Artigos(this);
    }
    @Override
    public String toString(){
        return "{Artigo é usado: " + isUsed + ";\nDescrição: " + description + ";\nCódigo de barras: " + barCode + ";\nPreço: " + price + ";\nPreço com desconto: " + discountPrice + ";\nEstado: " + usedState + ";\nNúmero de Utilizadores:  " + numOfUsers + ";\nTransportadora: " + transportadoraAssociada + "}";
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;

        if(o == null || o.getClass() != this.getClass()) return false;

        return this.isUsed == ((Artigos) o).isUsed && Objects.equals(this.barCode, ((Artigos) o).barCode) && this.price == ((Artigos) o).price
                && this.discountPrice == ((Artigos) o).discountPrice && this.usedState == ((Artigos) o).usedState
                && this.numOfUsers == ((Artigos) o).numOfUsers;
    }
}
