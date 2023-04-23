package Artigos;

import java.util.Objects;

public class TShirst extends Artigos{
    private String size;
    private String pattern;
    // discout deve vir ja com o valor certo!!!!
    public TShirst(boolean used, String desc, float price, float discount, int state, int numUsers, String size, String pattern) {
        super(used, desc, price, discount, state, numUsers);
        this.size = size;
        this.pattern = pattern;
    }

    public TShirst(TShirst a) {
        super(a);
        this.size = a.size;
        this.pattern = a.pattern;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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
