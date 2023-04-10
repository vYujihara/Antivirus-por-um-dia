public class Virus {
    private int atk;
    private int def;
    private boolean morto;

    public Virus(int atk, int def){
        setAtk(atk);
        setDef(def);
    }

    public void setAtk(int atk){
        this.atk = atk;
    }

    public int getAtk(){
        return this.atk;
    }

    public void setDef(int def){
        this.def = def;
    }

    public int getDef(){
        return this.def;
    }

    public void setMorto(boolean morto){
        this.morto = morto;
    }

    public boolean isMorto(){
        return this.morto;
    }
    
}
