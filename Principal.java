import java.util.Scanner;

/* Nome: Vinícius Yuji Hara   GRR:20211763  https://replit.com/@YujiHara/TrabalhoCI1062?v=1 */

public class Principal {
    public static final int TAMANHO = 5;
    public static final int TOTALCICLOS = 25;
    
    public static void main(String[] args)  {
        String movimento;
        boolean atualizouMovimentoP1,atualizouMovimentoP2;
        boolean vitoria = false;
        JogadorSimples p1 = new JogadorSimples();
        JogadorSuporte p2 = new JogadorSuporte();
        Setor[][] setores = new Setor[TAMANHO][TAMANHO];
        Tabuleiro tabu = new Tabuleiro(setores);

        /* Randomiza o local da infeccao */
        tabu.geraPosicaoInfeccao();

        Scanner sc = new Scanner(System.in); 
        Ciclo ciclo = new Ciclo(tabu,p1,p2);


          /* Comeco das rodadas */
        for(int i=0; i < TOTALCICLOS; i++){
            atualizouMovimentoP1 = false;
            atualizouMovimentoP2 = false;
            System.out.printf("CICLO %d\n",i+1);
            /* Se p1 nao está em batalha e p1 está vivo */
            if(!p1.isEmBatalha() && p1.isVivo()){
                /* Movimento P1 */
                while(!atualizouMovimentoP1 ){
                    ciclo.displayTabuleiroESetor();
                    ciclo.perguntaMovimento("P1");
                    movimento=sc.nextLine();
                    atualizouMovimentoP1=ciclo.movimentaPlayer(movimento, "P1");
                    if(ciclo.encontrouFonteInfeccao("P1")){
                        vitoria = true;
                    }
                }
                /* Se encontrou fonte de infecção, saia do loop de ciclos */
                if(vitoria){
                    break;
                }
                atualizouMovimentoP1=false;
                /* Chega se necessário criar inimigos */
                ciclo.checaInimigo("P1");
                /* Se tem inimigos no setor */
                if(tabu.getUmSetor(p1.getPosX(), p1.getPosY()).isInimigosNosetor()){
                    p1.setEmBatalha(true);
                }
            }
            if(!p2.isEmBatalha() && p2.isVivo()){
                 /* Movimento P2 */
                while(!atualizouMovimentoP2){
                    ciclo.displayTabuleiroESetor();
                    ciclo.perguntaMovimento("P2");
                    movimento=sc.nextLine();
                    atualizouMovimentoP2=ciclo.movimentaPlayer(movimento, "P2");
                    if(ciclo.encontrouFonteInfeccao("P2")){
                        vitoria = true;
                    }
                }
                if(vitoria){
                    break;
                }
                ciclo.checaInimigo("P2");
                /* Se tem inimigos no setor */
                if(tabu.getUmSetor(p2.getPosX(), p2.getPosY()).isInimigosNosetor()){
                    p2.setEmBatalha(true);
                }
            }
            
            /* Batalha */

            if(p1.isVivo()){
                if(p1.isEmBatalha()){
                    ciclo.combate("P1");
                }
                else{
                    ciclo.acaoSetorVazio("P1");
                }
            }
            
            if(p2.isVivo()){
                if(p2.isEmBatalha()){
                    ciclo.combate("P2");
                }
                else{
                    ciclo.acaoSetorVazio("P2");
                }
            }
            
            if(p1.isEmBatalha() || p2.isEmBatalha()){
                ciclo.inimigosAtacam(p1.isEmBatalha(),p2.isEmBatalha());
            }

            if(ciclo.isJogadoresDerrotados()){
                System.out.println("Jogadores foram derrotados");
                return;
            }
        }
        if(vitoria){
            System.out.println("A fonte de infecção foi achada, vitória!");
        }
        else{
            System.out.println("Limite máximo de ciclos alcançados, derrota!");
        }
                
    }
}
