public class JogadorSuporte extends Jogador {
    

    public JogadorSuporte(){
        super(2,2,true);
        setAtk(1);
        setDef(7);
    }

    public void setAtk(int atk){
        if(atk == 1){
            this.atk = atk;
        }
        else{
            System.out.println("Ataque do jogador suporte deve ser 1");
        }
    }

    public int getAtk(){
        return this.atk;
    }

    public void setDef(int def){
        if(def > 7){
            System.out.println("Defesa máxima é 7");
        }
        else{
            this.def = def;
        }
    
    }

    public int getDef(){
        return this.def;
    }
}
