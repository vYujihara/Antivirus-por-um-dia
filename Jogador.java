public abstract class Jogador {
    protected int atk;
    protected int def;
    protected int posX;
    protected int posY;
    protected boolean vivo;
    protected boolean emBatalha;
    
    public Jogador(int posX, int posY, boolean vivo){
        setPosX(posX);
        setPosY(posY);
        setVivo(vivo);
    }

    public abstract void setAtk(int atk);

    public abstract int getAtk();

    public abstract void setDef(int def);

    public abstract int getDef();

    public void setPosX(int posX){
        if(posX >= 5 || posX < 0){
            System.out.println("posX vai ultrapassar os limites do tabuleiro");
            return;
        }
        this.posX = posX;
    }

    public int getPosX(){
        return this.posX;
    }

    public void setPosY(int posY){
        if(posY >= 5 || posY < 0){
            System.out.println("posY vai ultrapassar os limites do tabuleiro");
            return;
        }
        this.posY = posY;
    }

    public int getPosY(){
        return this.posY;
    }

    public void setVivo(boolean vivo){
        this.vivo = vivo;
    }

    public boolean isVivo(){
        return this.vivo;
    }

    public void setEmBatalha(boolean emBatalha){
        this.emBatalha = emBatalha;
    }

    public boolean isEmBatalha(){
        return this.emBatalha;
    }
}