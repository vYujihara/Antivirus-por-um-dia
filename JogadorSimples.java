public class JogadorSimples extends Jogador{
    
    public JogadorSimples(){
        super(2,2,true);
        setAtk(2);
        setDef(6);
    }

    public void setAtk(int atk){
        if(atk == 2){
            this.atk = atk;
        }
        else{
            System.out.println("Ataque do jogador simples deve ser 2");
        }
    }

    public int getAtk(){
        return this.atk;
    }

    public void setDef(int def){
        if(def > 6){
            System.out.println("Defesa máxima é 6");
        }
        else{
            this.def = def;
        }
    
    }

    public int getDef(){
        return this.def;
    }

    
}
