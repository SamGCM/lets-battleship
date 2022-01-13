package com.battleship;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class Board {

    private String playerName;

    private char[][] board = new char[10][10];

    public Board(String name){
        this.playerName = name.toLowerCase();
        this.buildBoard();
    }

    public Board() {
        this.buildBoard();
    }

    public void buildBoard(){
        for(int i = 0; i < board.length; i++){
            Arrays.fill(board[i], ' ');
        }
    }

    public void showBoard(){
        char letters = 'A';

        System.out.println("---------------------------------------------");
        if(Objects.equals(this.playerName, "jogador")){
            System.out.println("                  JOGADOR                    ");
        }else{
            System.out.println("                 COMPUTADOR                  ");
        }
        System.out.println("---------------------------------------------");
        System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        for (int i = 0; i < board.length; i++){
            System.out.printf("| %c |", letters);
            letters++;
            for(int j = 0; j < board[i].length; j++){
                System.out.printf(" %c |", board[i][j]);
            }
            System.out.println("");
        }
        System.out.println("---------------------------------------------");
    }

    public void showScore(Player player, Player opponent){
        System.out.printf("  JOGADOR: %d                 COMPUTADOR: %d  %n", player.getScore(), opponent.getScore());
        System.out.println("---------------------------------------------");
        System.out.println(" ");
    }

    public void setBoard(char row, int collumn, char value){
        int rowInt = transformLetterInNumber(row);
        board[rowInt][collumn] = value;
    }

    public char getPositionValue(char row, int collumn){
        int rowInt = transformLetterInNumber(row);
        return board[rowInt][collumn];
    }

    public int transformLetterInNumber(char row){
        //Gambiarra que transforma um char em numero.
        return Character.toLowerCase(row) - 'a';
    }

}
