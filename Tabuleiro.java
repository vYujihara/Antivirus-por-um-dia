import java.util.Random;

public class Tabuleiro {
    private Setor[][] setores ;

    public Tabuleiro(Setor[][] setores){
        setTabuleiro(setores);
        geraSetores(setores);
        criaPortas(setores);
    }

    public Setor[][] getTabuleiro() {
        return this.setores;
    }

    public Setor getUmSetor(int i, int j){
        return this.setores[i][j];
    }

    public void setTabuleiro(Setor[][] setores) {
        this.setores = setores;
    }
    
    /* Gera os tipos de setores(Normal, oculto, privado) */
    public void geraSetores(Setor[][] setores){
        int tipo;

        Random aleatorio = new Random();
        for(int i = 0 ; i < 5 ; i++){
            for (int j = 0; j < 5 ; j++){
                tipo = aleatorio.nextInt(3);
                setores[i][j] = new Setor(tipo);
                /* Setor central, não gera inimigos e está revelado */
                if(i == 2 && j == 2){
                    getUmSetor(i, j).setRevelado(true);
                    getUmSetor(i, j).setInimigosDerrotados(false);
                }
            }
        }
    }   

    /* Cria as portas dos setores */
    public void criaPortas(Setor[][] setores){
        setores[0][0].setPortas(false, true, true, false);
        setores[0][1].setPortas(false, false, true, true);
        setores[0][2].setPortas(false, false, true, false);
        setores[0][3].setPortas(false, true, true, false);
        setores[0][4].setPortas(false, false, false, true);
        setores[1][0].setPortas(true, false, true, false);
        setores[1][1].setPortas(true, false, true, false);
        setores[1][2].setPortas(true,true,true,true);
        setores[1][3].setPortas(true,false,true,true);
        setores[1][4].setPortas(false,false,true,false);
        setores[2][0].setPortas(true,false,true,false);
        setores[2][1].setPortas(true,true,true,false);
        setores[2][2].setPortas(true,true,true,true);
        setores[2][3].setPortas(true,true,false,true);
        setores[2][4].setPortas(true,false,true,true);
        setores[3][0].setPortas(true,true,true,false);
        setores[3][1].setPortas(true,false,true,true);
        setores[3][2].setPortas(true,true,true,false);
        setores[3][3].setPortas(false,false,true,true);
        setores[3][4].setPortas(true,false,true,false);
        setores[4][0].setPortas(true,false,false,false);
        setores[4][1].setPortas(true,true,false,false);
        setores[4][2].setPortas(true,true,false,true);
        setores[4][3].setPortas(true,true,false,true);
        setores[4][4].setPortas(true,false,false,true);
    }

    public void geraPosicaoInfeccao(){
        int posXInfeccao = 2;
        int posYInfeccao = 2;
        Setor setor;
        /* Randomiza o local da infeccao */
        Random aleatorio = new Random();

        while((posXInfeccao == 2) && (posYInfeccao == 2)){
            posXInfeccao = aleatorio.nextInt(4) ;
            posYInfeccao = aleatorio.nextInt(4) ;
        }
        setor = getUmSetor(posXInfeccao, posYInfeccao);
        setor.setFonteInfeccao(true);
        setor.setInimigosDerrotados(true);
    }   

}
