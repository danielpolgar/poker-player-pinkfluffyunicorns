package org.leanpoker.player;

public class GameState {
    int round;
    int bet_index;
    int small_blind;
    int current_buy_in;
    int pot;
    int minimum_raise;
    int dealer;
    int orbits;
    int in_action;
    PlayerState[] players = new PlayerState[6];
    Card[] community_cards = new Card[5];

    GameState() {
    }
}
