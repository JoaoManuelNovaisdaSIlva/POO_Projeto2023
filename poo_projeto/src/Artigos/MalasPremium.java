package Artigos;

public class MalasPremium extends Malas{
    private String author;

    public MalasPremium(boolean used, String desc, float price, float discount, int state, int numUsers, int dimensions, String texture, int collectionYear, String author) {
        super(used, desc, price, discount, state, numUsers, dimensions, texture, collectionYear);
        this.author = author;
    }

    public MalasPremium(MalasPremium a) {
        super(a);
        this.author = a.author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void atualizaPrecoMalasPremium(MalasPremium m, int currentYear){
        float newPrice = m.getPrice();
        if(m.getDimensions() == 1){
            newPrice = (m.getPrice() + (m.getPrice()*0.05f*(currentYear- m.getCollectionYear())));
        }else if (m.getDimensions() == 2){
            newPrice = (m.getPrice() + (m.getPrice()*0.10f*(currentYear- m.getCollectionYear())));
        }else if (m.getDimensions() == 3){
            newPrice = (m.getPrice() + (m.getPrice()*0.20f*(currentYear- m.getCollectionYear())));
        }
        m.setPrice(newPrice);
    }
}
