package com.battleship;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private int score;
    private Board board;
    private String name;
    private int numSubSet;

    public Player(String name){
        this.score = 0;
        this.name = "Jogador";
        this.board = new Board(name);
        this.numSubSet = 0;
    }

    Scanner sc = new Scanner(System.in);

    public boolean howSetSubs() {
        String mode = sc.nextLine();
            try {
                switch (mode) {
                    case "1":
                        putRandomSubmarines(10);
                        return true;
                    case "2":
                        while (getNumSubsPositioned() < 10) {
                            setSub();
                            System.out.printf("Você já posicionou: %d.\n" ,getNumSubsPositioned());
                            System.out.println();
                        }
                        return true;
                    default:
                        System.out.println("Valor escolhido é inválido!\n1 - Aleatória.\n2 - Escolher um por um.");
                        return false;
                }
            } catch (Exception e) {
                System.out.println("Valor inválido. Tente outro.");
                return false;
            }
    }

    public boolean setBomb(Player opponent) {
//        Função exclusiva para player, onde pega os dados inseridos no console.
        this.board.showBoard();
        this.board.showScore(this, opponent);
        boolean error = true;
        char row = 0;
        int col = 0;
        System.out.println("Digite uma posição de A-J e 0-9:");

        String positionInput = sc.nextLine();


        while(error){
            try{
                String input = positionInput.replaceAll("\\s","");
                System.out.println(positionInput);
                row = getRowOfInput(input);
                col = getColOfInput(input);
                if(positionInput.matches("^[K-Zk-z]*+[K-Zk-z]*$") || positionInput.length() < 2 || positionInput.length() > 3){
                    throw new Throwable();
                } { error = false;}

            } catch (Throwable e){
                System.out.println("Uma posição válida só tem 1 letra e 1 número, por exemplo: a1 ou 1a");
                error = true;
                positionInput = sc.next();
            }
        }

        return putBomb(row,col, opponent);
    }

    public boolean setSub() throws Exception {
//        Função exclusiva para player, onde pega os dados inseridos no console.
        boolean error = true;
        char row = 0;
        int col = 0;
        System.out.println("Digite uma posição de A-J e 0-9:");
        sc.nextLine();
        String positionInput = sc.nextLine();
        while(error){
            try{
                String input = positionInput.replaceAll("\\s","");
                System.out.println(positionInput);
                row = getRowOfInput(input);
                col = getColOfInput(input);
                if(positionInput.matches("^[K-Zk-z]*+[K-Zk-z]*$") || positionInput.length() < 2 || positionInput.length() > 3){
                    throw new Throwable();
                } { error = false;}

            } catch (Throwable e){
                System.out.println("Uma posição válida só tem 1 letra e 1 número, por exemplo: a1 ou 1a");
                System.out.println("Digite uma posição de A-J e 0-9:");
                error = true;
                positionInput = sc.next();
            }
        }

        return putSubmarine(row,col);
    }


    private char getRowOfInput(String position) {
        String letter = position.replaceAll("[0-9]", "");
        char row = letter.charAt(0);
        return row;
    }

    private int getColOfInput(String position) {
        int column = Integer.parseInt(position.replaceAll("[\\D]", ""));
        return column;
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
                this.waitTwoSecPlease();
                switch(token){
                    case ' ':
                        this.board.setBoard(row, column, '*');
                        this.waitTwoSecPlease();
                        break;
                    case 'N':
                        this.board.setBoard(row, column, 'X');
                        this.waitTwoSecPlease();
                }
                this.score++;
            } else {
                System.out.println(this.name + " errou!");
                this.waitTwoSecPlease();
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
            System.out.printf("A posição %c%d está ocupada.\n", row,column);
            return false;
        } else if (token == ' ') {
            // Se não tiver, coloca o submarino e return true
            this.board.setBoard(row, column, 'N');
            numSubSet++;
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

    private void waitTwoSecPlease(){
        long start = System.currentTimeMillis();
        long end = start + 2*1000;
        while (System.currentTimeMillis() < end) {
//            System.out.println(start);
//            System.out.println(end);
        }
    }

    public int getNumSubsPositioned() {
        return this.numSubSet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean tryAgain (){
        System.out.println("Deseja jogar novamente?\n 1 - Óbvio! \n 2- Não aguento mais, quero ir embora.");
        String response = sc.next();
        switch (response) {
            case "1":
                return true;
            case "2":
                return false;
            default:
                System.out.println("Escolheu uma opção que não existe né... vai de novo.");
                return true;
        }

    }
}
