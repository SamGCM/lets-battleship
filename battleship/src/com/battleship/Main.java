package com.battleship;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean keepPlay = true;
        int playerScoreWinner = 1; // Set 1 for test.
        int computerScoreWinner = 1; // Set 1 for test.

        while(keepPlay) {
            boolean choosenPlay = true;
            boolean finishGame = true;

            //As linha abaixo são só para teste das funcionalidades.
            Player player = new Player("Jogador");
            Computer computer = new Computer("Computador");

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
            Beep.main();
            while(finishGame){

                player.setBomb(computer);

                if(player.getScore() == playerScoreWinner){
                    player.showBoard();
                    computer.showBoard();
                    System.out.printf(
                                      "====        VENCEDOR      ====\n" +
                                      "====        JOGADOR       ====\n" +
                                      "==============================\n" +
                                      "====       PONTUAÇÃO      ====\n" +
                                      "====    COMPUTADOR: %d    ====\n" +
                                      "====      JOGADOR: %d      ====\n \n"
                            , player.getScore(), computer.getScore());
                    break;
                }else{
                    computer.putRandomBomb(player);
                }

                if(computer.getScore() == computerScoreWinner) {
                    player.showBoard();
                    computer.showBoard();
                    System.out.printf(
                                    "====        VENCEDOR      ====\n" +
                                    "====       COMPUTADOR     ====\n" +
                                    "==============================\n" +
                                    "====       PONTUAÇÃO      ====\n" +
                                    "====    COMPUTADOR: %d   ====\n" +
                                    "====       JOGADOR: %d    ====\n \n"
                            , player.getScore(), computer.getScore());
                    break;
                }
            }

            keepPlay = player.tryAgain();
        }
    }
}
