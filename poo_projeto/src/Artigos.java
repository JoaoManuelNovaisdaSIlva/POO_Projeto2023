import java.util.Objects;

public class Artigos {
    private boolean isUsed; // Sim, é possivel haver um Artigo que seja Novo mas tenha os valores de usedState e numOfUsers, mas não importa porque depois nao vao ser usados
    private int usedState; // 1 - Fraco estado, 2 - Medio estado, 3 - Bom estado
    private int numOfUseres;

    public Artigos(boolean used, int state, int numUsers){
        this.isUsed = used;
        this.usedState = state;
        this.numOfUseres = numUsers;
    }

    public Artigos(Artigos a){
        this.isUsed = a.isUsed;
        this.usedState = a.usedState;
        this.numOfUseres = a.numOfUseres;
    }

    public boolean getIsUsed(){
        return this.isUsed;
    }

    public void setUsed(boolean u){
        this.isUsed = u;
    }

    public int getUsedState(){
        return this.usedState;
    }

    public void setUsedState(int u){
        this.usedState = u;
    }

    public int getNumOfUseres(){
        return this.numOfUseres;
    }

    public void setNumOfUseres(int u){
        this.numOfUseres = u;
    }

    public Artigos clone(){
        return new Artigos(this);
    }

    public String toString(){
        return "Artigo é: " + isUsed + " Com o estado: " + usedState + " Com " + numOfUseres + " utilizadores";
    }

    public boolean equals(Object o){
        if(this == o) return true;

        if(o == null || o.getClass() != this.getClass()) return false;

        return this.isUsed == ((Artigos) o).isUsed && this.usedState == ((Artigos) o).usedState && this.numOfUseres == ((Artigos) o).numOfUseres;
    }
}
