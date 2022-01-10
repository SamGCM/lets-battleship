package com.battleship;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean keepPlay = true;
        while(keepPlay) {
            boolean choosenPlay = true;
            boolean finishGame = true;

            //As linha abaixo são só para teste das funcionalidades.
            Player player = new Player();
            Computer computer = new Computer();

//        FASE DE POSICIONAMENTO DE NAVIOS
            System.out.println("Você deseja colocar os submarinos de que forma?");
            System.out.println("1 - Aleatória.\n2 - Escolher um por um.");
            while(choosenPlay) {
//            Verificar se o jogador escolheu o tipo de modo de colcoar os navios
                boolean typePlay = player.howSetSubs();
                if(typePlay) {
                    computer.putRandomSubmarines(10);
                    choosenPlay = false;
                }
            }

            System.out.println("LETS PLAY");
            while(finishGame){
                if(player.getScore() == 10){
                    player.showBoard();
                    computer.showBoard();
                    System.out.printf("====    VENCEDOR    ====\n" +
                                    "====    JOGADOR    ====\n" +
                                    "==================\n" +
                                    "====    PONTUAÇÃO    ====\n" +
                                    "====   COMPUTADOR: %d    ====\n" +
                                    "====    JOGADOR: %d    ===="
                            , player.getScore(), computer.getScore());
                    break;

                } { player.setBomb(computer); }

                if(computer.getScore() == 1) {
                    player.showBoard();
                    computer.showBoard();
                    System.out.printf(
                            "====        VENCEDOR      ====\n" +
                                    "====       COMPUTADOR     ====\n" +
                                    "==============================\n" +
                                    "====       PONTUAÇÃO      ====\n" +
                                    "====    COMPUTADOR: %d    ====\n" +
                                    "====       JOGADOR: %d    ====\n \n"
                            , player.getScore(), computer.getScore());
                    break;
                } {computer.putRandomBomb(player);}
            }

            keepPlay = player.tryAgain();
        }
    }
}
