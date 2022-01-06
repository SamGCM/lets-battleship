package com.battleship;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private int score;
    private Board board;
    private String name;

    public Player(){
        this.score = 0;
        this.name = "Jogador";
        this.board = new Board();
    }

    public void showBoard(){
        board.showBoard();
    }

    public boolean putBomb(char row, int column, Player opponent) {
        // Checa se a posição da bomba é válida
        char token = this.board.getPositionValue(row, column);

        if (token == 'n' || token == '-' || token == '*' || token == 'X'){
            return false;
        } else {
            boolean hit = opponent.checkHit(row, column);
            if(hit){
                System.out.println(this.name + " acertou um navio inimigo!");
                switch(token){
                    case ' ':
                        this.board.setBoard(row, column, '*');
                        break;
                    case 'N':
                        this.board.setBoard(row, column, 'X');
                }
                this.score++;
            } else {
                System.out.println(this.name + " errou!");
                switch(token){
                    case ' ':
                        this.board.setBoard(row, column, '-');
                        break;
                    case 'N':
                        this.board.setBoard(row, column, 'n');
                }
            }
            return true;
        }
    }

    public boolean putSubmarine(char row, int column) throws Exception {
        // Checa se a posição do submarino é válida
        char token = this.board.getPositionValue(row, column);

        // Se já tiver um submarino na posição, return false
        if(token == 'N'){
            return false;
        } else if (token == ' ') {
            // Se não tiver, coloca o submarino e return true
            this.board.setBoard(row, column, 'N');
            return true;
        } else {
            // Exception em caso de erro
            throw new Exception("Token inválido no tabuleiro.");
        }
    }

    public void putRandomSubmarines() throws Exception{
        // Função de teste
        putRandomSubmarines(10);
    }

    public void putRandomSubmarines(int no_ships) throws Exception {
        // Repete para cada um dos submarinos
        for (int i = 0; i<no_ships; i++){
            boolean placed = false;
            while(!placed) {
                // Gera posição
                char row = (char) (ThreadLocalRandom.current().nextInt(0, 10) + 'A');
                int col = ThreadLocalRandom.current().nextInt(0, 10);
                // Checa se já existe um submarino na coordenada
                if(putSubmarine(row, col)){
                    // Se a posição estiver vazia, encerra o loop
                    placed = true;
                }
            }
        }

    }

    public boolean checkHit(char row, int column){
        // Se o oponente acertar um submarino return true, se não, return false
        char token = this.board.getPositionValue(row, column);
        if(token == 'N' || token == 'n'){
            return true;
        }
        return false;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
