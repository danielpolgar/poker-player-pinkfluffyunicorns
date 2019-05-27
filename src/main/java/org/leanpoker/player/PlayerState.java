package org.leanpoker.player;

public class PlayerState {
    int id;
    String name;
    String status;
    int stack;
    int bet;
    Card[] hole_cards = new Card[2];
}
