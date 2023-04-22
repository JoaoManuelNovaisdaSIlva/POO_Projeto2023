package Artigos;

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
}
