package com.battleship;

public class Main {

    public static void main(String[] args) {

        //As linha abaixo são só para teste das funcionalidades.
        Board player = new Board();
        player.buildBoard();
        player.showBoard();
        player.setBoard('e', 5, '-');
        player.showBoard();
        System.out.println(player.getPositionValue('e', 5));
    }

}
