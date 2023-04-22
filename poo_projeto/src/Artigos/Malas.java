package Artigos;

public class Malas extends Artigos{
    private int dimensions; // 1 - pequena, 2 - media, 3 - grande
    private String texture;
    private int collectionYear;

    public Malas(boolean used, String desc, float price, float discount, int state, int numUsers, int dimensions, String texture, int collectionYear) {
        super(used, desc, price, discount, state, numUsers);
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

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public int getCollectionYear() {
        return collectionYear;
    }

    public void setCollectionYear(int collectionYear) {
        this.collectionYear = collectionYear;
    }
}
