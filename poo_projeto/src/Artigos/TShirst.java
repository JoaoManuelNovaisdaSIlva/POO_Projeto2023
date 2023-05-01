package Artigos;

import Encomendas.Transportadora;

import java.util.Objects;

public class TShirst extends Artigos{
    private TamanhoTShirt size;
    private PadraoTShirt pattern;
    // discout deve vir ja com o valor certo!!!!
    public TShirst(boolean used, String desc, float price, float discount, Estado state, int numUsers, Transportadora t, TamanhoTShirt size, PadraoTShirt pattern) {
        super(used, desc, price, discount, state, numUsers, t);
        this.size = size;
        this.pattern = pattern;
    }

    public TShirst(TShirst a) {
        super(a);
        this.size = a.size;
        this.pattern = a.pattern;
    }

    public TamanhoTShirt getSize() {
        return size;
    }

    public void setSize(TamanhoTShirt size) {
        this.size = size;
    }

    public PadraoTShirt getPattern() {
        return pattern;
    }

    public void setPattern(PadraoTShirt pattern) {
        this.pattern = pattern;
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), size, pattern);
    }
    @Override
    public TShirst clone(){
        return new TShirst(this);
    }
    @Override
    public String toString(){
        return super.toString() + "; T-shirt de tamanho: " + size + "; Padrão: " + pattern;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        if(!super.equals(o)) return false;
        TShirst t = (TShirst) o;

        return Objects.equals(this.size, t.size) && Objects.equals(this.pattern, t.pattern) && super.equals(t);
    }
}
