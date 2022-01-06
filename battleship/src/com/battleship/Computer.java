package com.battleship;

import java.util.concurrent.ThreadLocalRandom;

public class Computer extends Player {

    private String name;

    public Computer() {
        super();
        setName("Computer");
    }

    public void putRandomBomb(Player opponent) {
        boolean valid_bomb_position = false;
        while(!valid_bomb_position){
            // Gera coordenadas
            char row = (char) (ThreadLocalRandom.current().nextInt(0, 10) + 'A');
            int col = ThreadLocalRandom.current().nextInt(0, 10);

            // Checa se a posição é válida e atualiza o tabuleiro
            valid_bomb_position = this.putBomb(row, col, opponent);
        }
    }
}
