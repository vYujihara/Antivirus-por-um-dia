import java.util.ArrayList;

public  class Setor {
    protected boolean portaCima,portaDir,portaBaixo,portaEsq;
    protected boolean revelado, fonteInfeccao, inimigosNoSetor, inimigosDerrotados;
    protected int tipo,numInimigos;
    protected ArrayList<Virus> arrVirus;
    

    public Setor(int tipo){
        setTipo(tipo);
    }

    public void setPortas(boolean portaCima,boolean portaDir,boolean portaBaixo,boolean portaEsq){
        this.portaCima = portaCima;
        this.portaDir = portaDir;
        this.portaBaixo = portaBaixo;
        this.portaEsq = portaEsq;
    }

    public boolean isPortaCima(){
        return this.portaCima;
    }

    public boolean isPortaDir(){
        return this.portaDir;
    }

    public boolean isPortaBaixo(){
        return this.portaBaixo;
    }

    public boolean isPortaEsq(){
        return this.portaEsq;
    }

    public void setRevelado(boolean revelado){
        this.revelado = revelado;
    }

    public boolean isRevelado(){
        return this.revelado;
    }

    public void setTipo(int tipo ){
        this.tipo = tipo;
    }

    public int getTipo(){
        return this.tipo;
    }

    public void setFonteInfeccao(boolean infeccao){
        this.fonteInfeccao = infeccao;
    }

    public boolean isFonteInfeccao(){
        return this.fonteInfeccao;
    }

    public void setInimigosNoSetor(boolean inimigosNoSetor){
        this.inimigosNoSetor = inimigosNoSetor;
    }

    public boolean isInimigosNosetor(){
        return this.inimigosNoSetor;
    }

    public void setInimigosDerrotados(boolean inimigosDerrotados){
        this.inimigosDerrotados = inimigosDerrotados;
    }

    public boolean isInimigosDerrotados(){
        return this.inimigosDerrotados;
    }

    public void setArrVirus(ArrayList<Virus> arrVirus){
        this.arrVirus = arrVirus;
    }
    public ArrayList<Virus> getArrVirus(){
        return this.arrVirus;
    }
}
