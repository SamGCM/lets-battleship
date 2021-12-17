package com.battleship;

public class Player {

    private int hits;

    public Player(int hits){
        this.hits = hits;
    }

    public int doMove(int move) {
        return move;
    }

    public int putSubmarine(int position) {
        return position;
    }

    public int putRandom(int position) {
        return position;
    }

    public int getHits() {
        return hits;
    }

    public void addHit() {
        hits++;
    }
}
