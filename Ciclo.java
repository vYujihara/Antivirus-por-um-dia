import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ciclo {
    private Tabuleiro tabu;
    private JogadorSimples p1;
    private JogadorSuporte p2;

    public Ciclo(Tabuleiro tabu, JogadorSimples p1, JogadorSuporte p2) {
        setTabuleiro(tabu);
        setP1(p1);;
        setP2(p2);
    }

    public Tabuleiro getTabuleiro() {
        return this.tabu;
    }

    public void setTabuleiro(Tabuleiro tabu) {
        this.tabu = tabu;
    }

    public JogadorSimples getP1(){
        return this.p1;
    }

    public void setP1(JogadorSimples p1){
        this.p1 = p1;
    }

    public JogadorSuporte getP2(){
        return this.p2;
    }

    public void setP2(JogadorSuporte p2){
        this.p2 = p2;
    }


    /* Analizar vizinhos auxilia na impressao da tabela */
    public boolean analisaVizinhoBaixo(int i, int j){
        if( getTabuleiro().getUmSetor(i + 1 , j ).isPortaCima() && getTabuleiro().getUmSetor(i  +1 , j).isRevelado()){
            return true;
        }
        return false;
    }

    public boolean analisaVizinhoDireita(int i, int j){
        if( getTabuleiro().getUmSetor(i , j + 1).isPortaEsq() && getTabuleiro().getUmSetor(i  , j + 1).isRevelado()){
            return true;
        }
        return false;
    }

    /* Printa tabuleiro e setor */
    public void displayTabuleiroESetor(){
        displayTabuleiro();
        displaySetor();
        return;
    }

    /* Coloca a infeccao, players, centro e portas se as salas estão reveladas */
    public void displayTabuleiro(){
        int i,j,k;
        for (i = 0 ; i < 28; i++){
            System.out.print("-");
        }
        System.out.println();
        System.out.println("|   Antivírus por um dia   |");
        for (i = 0 ; i < 28; i++){
            System.out.print("-");
        }
        System.out.println();   
        System.out.print("    "); 
        for (i = 0 ; i < 5 ; i++){
            System.out.printf("%d   "  ,i+1);
        }
        System.out.println();
        System.out.print("  |");
        for (i = 0 ; i < 5 ; i++){
            System.out.print("---|");
        }
        System.out.println();
        for (i = 0 ; i < 5 ; i++){
            System.out.printf("%d |",i+1);
            for (j = 0 ; j < 5 ; j++){
                if((i == 2) && (j == 2)){
                    System.out.print(" C ");
                }
                else if(getTabuleiro().getUmSetor(i, j).isFonteInfeccao()){
                    System.out.print(" X ");
                }
                else {
                    if((getP1().getPosX() == i) && (getP1().getPosY() == j) && (getP2().getPosX() == i) && (getP2().getPosY() == j)){
                        System.out.print("P12");
                    }
                    else{
                        if((getP2().getPosX() == i) && (getP2().getPosY() == j)){
                            System.out.print("P2 ");
                        }
                        else if ((getP1().getPosX() == i) && (getP1().getPosY() == j)){
                            System.out.print("P1 ");
                        }
                        else{
                            System.out.print("   ");
                        }
                    }

                }
                
                if((getTabuleiro().getUmSetor(i, j).isRevelado() && getTabuleiro().getUmSetor(i, j).isPortaDir())){
                    System.out.print("*");
                }
                
                else if( j < 4){
                    if(analisaVizinhoDireita(i, j)){

                        System.out.print("*");
                    }
                    else{
                        System.out.print("|");
                    }
                }
                else{
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.print("  |");
            for (k = 0 ; k < 5 ; k++){
                if((getTabuleiro().getUmSetor(i, k).isRevelado()  && getTabuleiro().getUmSetor(i, k).isPortaBaixo())){
                    System.out.print("-*-|");
                }
                
                else if (i < 4){
                    if(analisaVizinhoBaixo(i,k)){
                        System.out.print("-*-|");
                    }
                    else{
                        System.out.print("---|");
                    }
                }
                
                else{
                    System.out.print("---|");
                }
            }
            System.out.println();
            
        }
        System.out.println();
    } 




    /* Mostra único setor se os 2 jogadores presente */
    public void displaySetorJuntos(boolean juntos){
        int i;
        int posX = getP1().getPosX();
        int posY = getP1().getPosY();

        int qntVirus; 
        /* Se foi gerado inimigos e não foram derrotados, vai mostrar os virus no display */
        if(getTabuleiro().getUmSetor(posX, posY).isInimigosNosetor() && !getTabuleiro().getUmSetor(posX, posY).isInimigosDerrotados()){
            qntVirus= getTabuleiro().getUmSetor(posX, posY).getArrVirus().size();
        }
        else{
            qntVirus = 0;
        }
        
        

        if(juntos == true){
            System.out.printf("  Setor [%d,%d]\n",posX +1,posY +1);
            System.out.println();
            if(getTabuleiro().getUmSetor(posX, posY).isPortaCima()){
                System.out.print("|------*------|\n");
            }
            else{
                System.out.print("|-------------|\n");
            }
            System.out.print("|");
            /* Se tem virus, itera no arrayList de virus */
            if (qntVirus != 0){
                for(Virus virus: getTabuleiro().getUmSetor(posX, posY).getArrVirus() ){
                    System.out.printf(" %d/%d",virus.getAtk(),virus.getDef());
                }
            
                if(qntVirus == 1){
                    System.out.print("         |\n");
                }
                if(qntVirus == 2){
                    System.out.print("     |\n");
                }
                if(qntVirus == 3){
                    System.out.print(" |\n");
                } 
            }
            else {
                System.out.print("             |\n");
            }
            System.out.print("|             |\n");
            if(getTabuleiro().getUmSetor(posX, posY).isPortaEsq()){
                System.out.print("*");
            }
            else {
                System.out.print("|");
            }
            if(getTabuleiro().getUmSetor(posX, posY).isPortaDir()){
                System.out.print("             *\n");
            }
            else {
                System.out.print("             |\n");
            }
            System.out.print("|  P1     P2  |\n");
            System.out.printf("|  %d/%d   %d/%d  |\n",getP1().getAtk(),getP1().getDef(),getP2().getAtk(),getP2().getDef());
            if(getTabuleiro().getUmSetor(posX, posY).isPortaBaixo()){
                System.out.print("|------*------|\n");
            }
            else{
                System.out.print("|-------------|\n");
            }


        }     
    }

    /* Mostra setor de cada jogador */
    public void displaySetorSeparado(){
        int i;
        int p1PosX = getP1().getPosX();
        int p1PosY = getP1().getPosY();
        int p2PosX = getP2().getPosX();
        int p2PosY = getP2().getPosY();
        int p1QntVirus, p2QntVirus; 

         /* Se foi gerado inimigos e não foram derrotados, vai mostrar os virus no display */
        if(getTabuleiro().getUmSetor(p1PosX, p1PosY).isInimigosNosetor() && !getTabuleiro().getUmSetor(p1PosX, p1PosY).isInimigosDerrotados()){
            p1QntVirus= getTabuleiro().getUmSetor(p1PosX, p1PosY).getArrVirus().size();
        }
        else{
            p1QntVirus = 0;
        }
        if(getTabuleiro().getUmSetor(p2PosX, p2PosY).isInimigosNosetor() && !getTabuleiro().getUmSetor(p2PosX, p2PosY).isInimigosDerrotados()){
            p2QntVirus= getTabuleiro().getUmSetor(p2PosX, p2PosY).getArrVirus().size();
        }
        else{
            p2QntVirus = 0;
        }
        System.out.printf("  Setor [%d,%d]    ",p1PosX + 1,p1PosY +1);
        System.out.printf("  Setor [%d,%d]\n",p2PosX +1 ,p2PosY +1);
        System.out.println();
        if(getTabuleiro().getUmSetor(p1PosX, p1PosY).isPortaCima()){
            System.out.print("|------*------|");
        }
        else{
            System.out.print("|-------------|");
        }
        if(getTabuleiro().getUmSetor(p2PosX, p2PosY).isPortaCima()){
            System.out.print("  |------*------|\n");
        }
        else{
            System.out.print("  |-------------|\n");
        }
        System.out.print("|");
        if (p1QntVirus != 0){
            for(Virus virus: getTabuleiro().getUmSetor(p1PosX, p1PosY).getArrVirus() ){
                System.out.printf(" %d/%d",virus.getAtk(),virus.getDef());
            }
            
            if(p1QntVirus == 1){
                System.out.print("         |");
            }
            if(p1QntVirus == 2){
                System.out.print("     |");
            }
            if(p1QntVirus == 3){
                System.out.print(" |");
            } 
        }
        else {
            System.out.print("             |");
        }
        System.out.print("  |");
        if (p2QntVirus != 0){
            for(Virus virus: getTabuleiro().getUmSetor(p2PosX, p2PosY).getArrVirus() ){
                System.out.printf(" %d/%d",virus.getAtk(),virus.getDef());
            }
        
            if(p2QntVirus == 1){
                System.out.print("         |\n");
            }
            if(p2QntVirus == 2){
                System.out.print("     |\n");
            }
            if(p2QntVirus == 3){
                System.out.print(" |\n");
            } 
        }
        else {
            System.out.print("             |\n");
        }
        System.out.print("|             |  ");
        System.out.print("|             |\n");
        if(getTabuleiro().getUmSetor(p1PosX, p1PosY).isPortaEsq()){
            System.out.print("*");
        }
        else {
            System.out.print("|");
        }
        if(getTabuleiro().getUmSetor(p1PosX, p1PosY).isPortaDir()){
            System.out.print("             *  ");
        }
        else {
            System.out.print("             |  ");
        }
        if(getTabuleiro().getUmSetor(p2PosX, p2PosY).isPortaEsq()){
            System.out.print("*");
        }
        else {
            System.out.print("|");
        }
        if(getTabuleiro().getUmSetor(p2PosX, p2PosY).isPortaDir()){
            System.out.print("             *\n");
        }
        else {
            System.out.print("             |\n");
        }
        System.out.print("|  P1         |  ");
        System.out.print("|  P2         |\n");
        System.out.printf("|  %d/%d        |  ",getP1().getAtk(),getP1().getDef());
        System.out.printf("|  %d/%d        |\n",getP2().getAtk(),getP2().getDef());
        if(getTabuleiro().getUmSetor(p1PosX, p1PosY).isPortaBaixo()){
            System.out.print("|------*------|  ");
        }
        else{
            System.out.print("|-------------|  ");
        }
        if(getTabuleiro().getUmSetor(p2PosX, p2PosY).isPortaBaixo()){
            System.out.print("|------*------|  \n");
        }
        else{
            System.out.print("|-------------|  \n");
        }

    }

    /* Funcao que retorna true se P1 e P2 estão na mesma posição X e Y */
    public boolean isJunto(){
        if( (getP1().getPosX() == getP2().getPosX()) && (getP1().getPosY() == getP2().getPosY()) ){
            return true;
        }
        return false;
    }

    /* Display o setor dependendo da funcao isJunto */
    public void displaySetor(){
        boolean juntos = false;

        if( isJunto() ){
            juntos = true;
            displaySetorJuntos(juntos);
        }
        else{
            displaySetorSeparado();
        }
    }

    public void perguntaMovimento(String player){
        
        if(player == "P1"){
            System.out.println("Where to go PLAYER 1 (P1)?");
        }
        else{
            System.out.println("Where to go PLAYER 2 (P2)?");
        }
        System.out.println("   U- Up");
        System.out.println("   D- Down");
        System.out.println("   L- Left");
        System.out.println("   R- Right");

    }


    /* Movimenta o player de acordo com um input, se realizado com sucesso, retorna true,
       se porta fechada ou parede ou input errado, retorna falso */

    public boolean movimentaPlayer(String movimento,String player){
        int novaPosicao;
        boolean atualizou =false;
        int posX;
        int posY;
        if(player == "P1"){
            posX = getP1().getPosX();
            posY = getP1().getPosY();
        }
        else{
            posX = getP2().getPosX();
            posY = getP2().getPosY();
        }
        
        switch(movimento) {

            case "L":
                if(getTabuleiro().getUmSetor(posX, posY).isPortaEsq()){
                    novaPosicao = posY - 1;
                    if(player == "P1"){
                        getP1().setPosY(novaPosicao);
                    }
                    else{
                        getP2().setPosY(novaPosicao);
                    }
                    atualizou = true;
                    getTabuleiro().getUmSetor(posX, novaPosicao).setRevelado(atualizou);
                }
                break;
            case "R":
                if(getTabuleiro().getUmSetor(posX, posY).isPortaDir()){
                    novaPosicao = posY + 1;
                    if(player == "P1"){
                        getP1().setPosY(novaPosicao);
                    }
                    else{
                        getP2().setPosY(novaPosicao);
                    }
                    
                    atualizou = true;
                    getTabuleiro().getUmSetor(posX, novaPosicao).setRevelado(atualizou);
                }
               
                break;
            case "U":
                if(getTabuleiro().getUmSetor(posX, posY).isPortaCima()){
                    novaPosicao = posX - 1;
                    if(player == "P1"){
                        getP1().setPosX(novaPosicao);
                    }
                    else{
                        getP2().setPosX(novaPosicao);
                    }
                    atualizou = true;
                    getTabuleiro().getUmSetor(novaPosicao, posY).setRevelado(atualizou);
                }
                break;
            case "D":
                if(getTabuleiro().getUmSetor(posX, posY).isPortaBaixo()){
                    novaPosicao = posX + 1;
                    if(player == "P1"){
                        getP1().setPosX(novaPosicao);
                    }
                    else {
                        getP2().setPosX(novaPosicao);
                    }
                    atualizou = true;
                    getTabuleiro().getUmSetor(novaPosicao, posY).setRevelado(atualizou);
                }
                break;
            default:
                System.out.println("Letra incorreta, tente novamente");
                return atualizou;
        }
        if(!atualizou){
            System.out.println("Não é possível realizar a ação, tente outro movimento");
        }
        return atualizou;
    }


    /* Funcao que cria virus de 1 a 3e guarda em um ArrayList */
    public void criaInimigo(int posX,int posY){
        int qntVirus,i, virusAtributo;
        Virus virus;


        Random aleatorio = new Random();
        qntVirus = aleatorio.nextInt(3) + 1;
        ArrayList<Virus> arrVirus = new ArrayList<Virus>(qntVirus);
        for (i = 0; i < qntVirus ; i++){
            virusAtributo = aleatorio.nextInt(3) + 1;
            virus = new Virus(virusAtributo,virusAtributo);
            arrVirus.add(virus);

        }
        getTabuleiro().getUmSetor(posX,posY).setArrVirus(arrVirus);

    }

    /* Funcao para checar se é necessário criar inimigos */
    public void checaInimigo(String player){
        int posX;
        int posY;

        if(player == "P1"){
            posX = getP1().getPosX();
            posY = getP1().getPosY();
        }
        else{
            posX = getP2().getPosX();
            posY = getP2().getPosY();

        }
        /* Se nao tem inimigos no setor e também nao tem inimigos derrotado, cria */
        if(!getTabuleiro().getUmSetor(posX, posY).isInimigosNosetor() && !getTabuleiro().getUmSetor(posX, posY).isInimigosDerrotados()){
            if(!(posX == 2 && posY == 2)){
                getTabuleiro().getUmSetor(posX, posY).setInimigosNoSetor(true);
                criaInimigo(posX, posY);
            }
        }

    }

    /* Funcao que realiza o atk de player em virus */
    public void realizaAtaque(Virus virus, String player){
        int defVirus;
        if(player == "P1"){
            defVirus = virus.getDef() - getP1().getAtk();
            virus.setDef(defVirus);
        }
        else{
            defVirus = virus.getDef() - getP2().getAtk();
            virus.setDef(defVirus);
        }
    }
    /* Funcao para que o player ganhe Def */
    public void ganhaDef(String player,int vida){
        int defesaAtual;
        if(player == "P1"){
            defesaAtual = getP1().getDef() + vida;
            if(defesaAtual > 6){
                getP1().setDef(6);
            }
            else{
                getP1().setDef(defesaAtual);
            }
        }
        else{
            defesaAtual = getP2().getDef() + vida;
            if(defesaAtual > 7){
                getP2().setDef(7);
            }
            else{
                getP2().setDef(defesaAtual);
            }
        }
    }

    /* funcaoque todos os inimigos perderam vida após a ação Procurar */
    public void inimigosPerdemVida(String player){
        int posX ,posY,novaDef; 
        if(player == "P1"){
            posX = getP1().getPosX();
            posY = getP1().getPosY();
        }
        else{
            posX = getP2().getPosX();
            posY = getP2().getPosY();
        }
        ArrayList<Virus> arrVirus = getTabuleiro().getUmSetor(posX, posY).getArrVirus();
        for(Virus virus: arrVirus){
            if(!virus.isMorto()){
                novaDef = virus.getDef() - 1;
                virus.setDef(novaDef);
                /* Se virus morreu */
                if(virus.getDef() == 0){
                    virus.setMorto(true);
                }
            }
        }
    }

    /* Funçao que P2 cura player */
    public void curaJogador(int player){
        int defAtual;
        if(player  == 1){
            if(!getP1().isVivo()){
                System.out.println("Não pode curar o P1");
            }
            defAtual=getP1().getDef();
            if ( defAtual == 6){
                System.out.println("Vida do jogador já P1 está cheia");
                return;
            }
            /* Se defesa 5, cura 1 */
            if(defAtual >= 4){
                getP1().setDef(6);
            }
            else{
                defAtual+= 2;
                getP1().setDef(defAtual);
            }
            System.out.println("Curou jogador P1");
            return;
        }

        defAtual = getP2().getDef();
        if(defAtual == 7){
            System.out.println("Vida do jogador já P2 está cheia");
            return;
        }
         /* Se defesa 6, cura 1 */
         if(defAtual >= 5){
            getP2().setDef(7);
        }
        else{
            defAtual+= 2;
            getP2().setDef(defAtual);
        }
        System.out.println("Curou jogador P2");
        return;
    }
    
    /* Verifica se todos os virus estäo mortos no setor */
    public boolean isVirusMortos(ArrayList<Virus> arrVirus){
        
        for(Virus virus: arrVirus){
            if (!virus.isMorto()){
                return false;
            }
        }
        return true;
    }

    /* Funcao que implementa a logica do Combate */
    public void combate(String player){
        int i,j,escolhaAcao,escolhaAtk,tipoSetor;
        int acoes = 2;
        int posX,posY, maxIni,chanceAtk,procurar,curarPlayer;
        Virus virus;
        Scanner sc = new Scanner(System.in); 
        Random aleatorio = new Random();
        ArrayList<Virus> arrVirus;
        Setor setor;

        if(player == "P1"){
            /* Se o jogador estiver morto, retorna */
            if(!getP1().isVivo()){
                return;
            }
            posX = getP1().getPosX();
            posY = getP1().getPosY();
        }
        /* Se jogador P2 */
        else{
            if(!getP2().isVivo()){
                return;
            }
            posX = getP2().getPosX();
            posY = getP2().getPosY();

        }
        setor = getTabuleiro().getUmSetor(posX, posY);
        arrVirus = setor.getArrVirus();
        tipoSetor = setor.getTipo();
        maxIni = arrVirus.size() ;

        for (i = 0; i < acoes ; i++){
            /* Checa se todos os inimigos estão mortos, se estão limpa setor e saia do combate */
            if(isVirusMortos(arrVirus)){
                setor.setInimigosDerrotados(true);
                setor.setInimigosNoSetor(false);
                if(isJunto()){
                    getP1().setEmBatalha(false);
                    getP2().setEmBatalha(false);
                }
                else if(player == "P1"){
                    getP1().setEmBatalha(false);
                }
                else{
                    getP2().setEmBatalha(false);
                }
                return;
            }
            displayTabuleiroESetor();
            /* Opcoes de acoes */
            if(player == "P1"){
                System.out.printf("Ação %d do jogador P1\n",i+1);
            }
            else{
                System.out.printf("Ação %d do jogador P2\n",i+1);
            }
            System.out.println("Escolha um número de acordo com a ação que deseja");
            System.out.println("1 - Atacar");
            if(tipoSetor != 3){
                System.out.println("2 - Procurar");
            }
            else{
                System.out.println("2 - Procurar(Inválido - Setor privado)");
            }
            if(player == "P2"){
                System.out.println("3 - Recuperar Defesa");
            }

            escolhaAcao = sc.nextInt();
            /* Trata as ações  */
            switch(escolhaAcao){
                /* Atacar */
                case 1:
                    System.out.printf("Escolha um número de 1 até %d inimigos para atacar\n",maxIni);
                    escolhaAtk = sc.nextInt() - 1;
                    /* Se escolher um número maior que a quantidade de virus */
                    if(escolhaAtk + 1 > maxIni){
                        System.out.println("Número invalido");
                        /* volta pra acao inicial */
                        i--;
                        break;
                    }
                   
                    virus = arrVirus.get(escolhaAtk);
                    if(!virus.isMorto()){
                        /* se setor protected testa a chance de acerto */
                        if(tipoSetor == 2){
                            chanceAtk = aleatorio.nextInt(100);
                            /* Chance de 60% de acertar */
                            if(chanceAtk > 40){
                                realizaAtaque(virus, player);

                            }
                            else{
                                System.out.println("Errou o ataque (Setor oculto)");
                            }

                        }
                        else{
                            realizaAtaque(virus, player);
                        }
                        /* Trata se virus chegar com Def <= 0 */
                        if(virus.getDef() <= 0){
                            virus.setMorto(true);
                            virus.setDef(0);
                        }
                    }
                    else{
                        System.out.println("Virus ja está morto");
                    }
                    break;
                /* Açao de Procurar */
                case 2:
                    if(tipoSetor == 3){
                        System.out.println("Não pode procurar em setor privado");
                        i--;
                        break;
                    }
                    procurar = aleatorio.nextInt(6) + 1;
                    if (procurar <= 3){
                        System.out.println("Nada encontrado");
                    }
                    if ( procurar == 4){
                        ganhaDef(player, 1);
                        System.out.println("Jogador sorteou o número 4 e ganhou 1 DEF(Não ultrapassa vida máxima)");
                    }
                    if ( procurar == 5){
                        ganhaDef(player, 2);
                        System.out.println("Jogador sorteou o número 5 e ganhou 2 DEF(Não ultrapassa vida máxima)");
                    }
                    if ( procurar == 6){
                        inimigosPerdemVida(player);
                        System.out.println("Jogador sorteou o número 6 e deu 1 de dano em todos os inimigos");
                    }
                    break;
                /* Ação curar de P2 */
                case 3:
                    if(player == "P1"){
                        System.out.println("Esse ação é inválida, tente outra");
                        i--;
                        break;
                    }
                    if(isJunto()){
                        System.out.println("Escolha um player para recuperar defesa");
                        System.out.println("1 - P1");
                        System.out.println("2 - P2");
                        curarPlayer = sc.nextInt();
                        curaJogador(curarPlayer);
                    
                    }
                    /* Jogador P2 sozinho */
                    else{
                        curaJogador(2);
                    }
                    break;
                /* Se digitar numero errado */
                default:
                    System.out.println("Comando inválido");
                    i--;
            }
        }
        /* Checa se os inimigos estao mortos depois das 2 ações  */
        if(isVirusMortos(arrVirus)){
            setor.setInimigosDerrotados(true);
            setor.setInimigosNoSetor(false);
            if(isJunto()){
                getP1().setEmBatalha(false);
                getP2().setEmBatalha(false);
            }
            else if(player == "P1"){
                getP1().setEmBatalha(false);
            }
            else{
                getP2().setEmBatalha(false);
            }
            return;
        }
    }


    /* Faz o ataque do virus, se o jogador chegar a 0, retira o jogador do ciclo */
    public void ataqueVirus(String player,Virus virus){
        int virusAtk = virus.getAtk();
        int defPlayer;
        if (player == "P1"){
            defPlayer = getP1().getDef() - virusAtk;
            getP1().setDef(defPlayer);
            if(getP1().getDef() <= 0){
                getP1().setDef(0);
                getP1().setVivo(false);
                getP1().setEmBatalha(false);
            }
        }
        /* P2 */
        else {
            defPlayer = getP2().getDef() - virusAtk;
            getP2().setDef(defPlayer);
            if(getP2().getDef() <= 0){
                getP2().setDef(0);
                getP2().setVivo(false);
                getP2().setEmBatalha(false);
            }
        }
    }

    /* Funcao que implementa a logica dos ataques dos virus */
    public void inimigosAtacam(boolean p1EmBatalha, boolean p2EmBatalha){
        ArrayList<Virus> arrVirus1;
        ArrayList<Virus> arrVirus2;
        Random aleatorio = new Random();
        int numeroSorteado;

        if(p1EmBatalha){
            /* Pega o arr de virus de P1 */
            arrVirus1 = getTabuleiro().getUmSetor(getP1().getPosX(), getP1().getPosY()).getArrVirus();

            for(Virus virus: arrVirus1){
                /* Se o virus estiver morto, vai para o próximo */
                if(virus.isMorto()){
                    continue;
                }
                numeroSorteado = aleatorio.nextInt(6) + 1;
                if(getP1().isVivo()){
                    /* Se par, virus ataca */
                    if(numeroSorteado % 2 == 0){
                        ataqueVirus("P1",virus);
                        System.out.printf("Virus %d atacou P1\n",arrVirus1.indexOf(virus) + 1);
                    }
                    else{
                        System.out.printf("Virus %d errou no P1\n",arrVirus1.indexOf(virus) + 1);
                    }
                }
                /* Se P2 estiver junto com P1, ataca P2  */
                if(getP2().isVivo() && isJunto()){
                    numeroSorteado = aleatorio.nextInt(6) + 1;
                    if(numeroSorteado % 2 == 0){
                        ataqueVirus("P2",virus);
                        System.out.printf("Virus %d atacou P2\n",arrVirus1.indexOf(virus) + 1);
                    }
                    else{
                        System.out.printf("Virus %d errou no P2\n",arrVirus1.indexOf(virus) + 1);
                    }
                }
            }
        }
        /* Se não, ataca somente P2 */
        if(p2EmBatalha && !isJunto()){
            arrVirus2 = getTabuleiro().getUmSetor(getP2().getPosX(), getP2().getPosY()).getArrVirus();
            
            for(Virus virus: arrVirus2){

                if(virus.isMorto()){
                    continue;
                }
                numeroSorteado = aleatorio.nextInt(6) + 1;
                if(getP2().isVivo()){
                    if(numeroSorteado % 2 == 0){
                        ataqueVirus("P2",virus);
                        System.out.printf("Virus %d atacou P2\n",arrVirus2.indexOf(virus) + 1);
                    }
                    else{
                        System.out.printf("Virus %d errou no P2\n",arrVirus2.indexOf(virus) + 1);
                    }
                }
            }
        }
    }
    /* Testa se P1 e P2 foram derrotados, se sim retorna true */
    public boolean isJogadoresDerrotados(){
        return (!getP1().isVivo() && !getP2().isVivo());
    }

    /* Retorna true se player achou fonte de infecção */
    public boolean encontrouFonteInfeccao(String player){
        int posX,posY;
        Setor setor;
        if(player == "P1"){
            posX = getP1().getPosX();
            posY = getP1().getPosY();
        }
        else{
            posX = getP2().getPosX();
            posY = getP2().getPosY();
        }
        setor = getTabuleiro().getUmSetor(posX, posY);
        if(setor.isFonteInfeccao()){
            return true;

        }
        return false;
    }

    /* Realiza a ação se o setor não tem inimigo */
    public void acaoSetorVazio(String player){
        int posX,posY, i, escolhaAcao, procurar;
        Setor setor;
        int acoes = 2;
        Random aleatorio = new Random();
        Scanner sc = new Scanner(System.in);
        if(player == "P1"){
            posX = getP1().getPosX();
            posY = getP1().getPosY();
        }
        else{
            posX = getP2().getPosX();
            posY = getP2().getPosY();
        }
        for (i = 0; i < acoes ; i++){
            displayTabuleiroESetor();
            /* Opcoes de acoes */
            if(player == "P1"){
                System.out.printf("Ação %d do jogador P1\n",i+1);
            }
            else{
                System.out.printf("Ação %d do jogador P2\n",i+1);
            }
            System.out.println("Escolha um número de acordo com a ação que deseja");
            System.out.println("1 - Procurar");
            if(player == "P2"){
                System.out.println("2 - Recuperar defesa"); 
            }
            escolhaAcao = sc.nextInt();
            /* trata ação escolhida */
            switch(escolhaAcao){
                case 1:
                    procurar = aleatorio.nextInt(6) + 1;
                    if (procurar <= 3 || procurar == 6){
                        System.out.println("Nada encontrado");
                    }
                    if ( procurar == 4){
                        ganhaDef(player, 1);
                        System.out.println("Jogador sorteou o número 4 e ganhou 1 DEF(Não ultrapassa vida máxima)");
                    }
                    if ( procurar == 5){
                        ganhaDef(player, 2);
                        System.out.println("Jogador sorteou o número 5 e ganhou 2 DEF(Não ultrapassa vida máxima)");
                    }
                    break;
                case 2:
                    if(player == "P1"){
                        System.out.println("Esse ação é inválida, tente outra");
                        i--;
                        break;
                    }
                    curaJogador(2);
                    break;
                default:
                    System.out.println("Comando inválido");
                    i--;
            }
        }

    }
}
