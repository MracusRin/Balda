package org.mracus.corp;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Dictionary dictionary = new Dictionary();
        board.state(6, dictionary.getWord(), "A, D", "D");
    }
}