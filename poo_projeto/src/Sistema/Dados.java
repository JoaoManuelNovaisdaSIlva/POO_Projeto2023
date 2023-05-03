package Sistema;

import Artigos.Artigos;
import Encomendas.Encomenda;
import Encomendas.Transportadora;
import Utilizadores.Utilizador;

import java.io.Serializable;
import java.util.HashMap;

public class Dados implements Serializable {
    private HashMap<String, Transportadora> transportadoras;
    private HashMap<String, Utilizador> utilizadores;
    private HashMap<String, Artigos> artigos;
    private HashMap<String, Encomenda> encomendas;

    public Dados(HashMap<String, Transportadora> transportadoras, HashMap<String, Utilizador> utilizadores, HashMap<String, Artigos> artigos, HashMap<String, Encomenda> encomendas){
        this.transportadoras = transportadoras;
        this.utilizadores = utilizadores;
        this.artigos = artigos;
        this.encomendas = encomendas;
    }

    public Dados(Dados d){
        this.transportadoras = d.transportadoras;
        this.utilizadores = d.utilizadores;
        this.artigos = d.artigos;
        this.encomendas = d.encomendas;
    }

    public HashMap<String, Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(HashMap<String, Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public HashMap<String, Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(HashMap<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public HashMap<String, Artigos> getArtigos() {
        return artigos;
    }

    public void setArtigos(HashMap<String, Artigos> artigos) {
        this.artigos = artigos;
    }

    public HashMap<String, Encomenda> getEncomendas() {
        return encomendas;
    }

    public void setEncomendas(HashMap<String, Encomenda> encomendas) {
        this.encomendas = encomendas;
    }

    public Dados clone(Dados d){
        return new Dados(this);
    }

    @Override
    public String toString(){
        return "Transportadoras: " + this.transportadoras + ";\nUtilizadores: " + this.utilizadores + ";\nArtigos: " + this.artigos + ";\nEncomendas: " + this.encomendas;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        return this.transportadoras == ((Dados) o).transportadoras && this.utilizadores == ((Dados) o).utilizadores && this.artigos == ((Dados) o).artigos && this.encomendas == ((Dados) o).encomendas;
    }
}
