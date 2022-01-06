package com.battleship;

public class Main {

    public static void main(String[] args) throws Exception {

        //As linha abaixo são só para teste das funcionalidades.
        Player player = new Player();
        Computer computer = new Computer();
        player.showBoard();
        player.putRandomSubmarines(99);
        player.showBoard();
        computer.putRandomSubmarines(1);
        computer.showBoard();
        System.out.println("");
        System.out.println("LETS PLAY");
        player.putBomb('E', 5, computer);
        computer.putRandomBomb(player);
    }

}
