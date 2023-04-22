package Artigos;

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
}
