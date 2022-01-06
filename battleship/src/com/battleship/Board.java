package com.battleship;

import java.util.Arrays;

public class Board {

    private char[][] board = new char[10][10];

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
        System.out.println("                  JOGADOR                    ");
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
