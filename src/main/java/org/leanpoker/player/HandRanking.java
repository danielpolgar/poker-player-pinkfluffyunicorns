
package org.leanpoker.player;
public enum HandRanking {
    PAIR(10),
    TWOPAIR(15),
    DRILL(15),
    STRAIGHT(20),
    FLUSH(30),
    FULLHOUSE(35),
    FOUROFAKIND(40),
    STRAUGHTFLUSH(45),
    ROYALFLUSH(100);
    public int bet;

    HandRanking(int bet) {
        this.bet = bet;
    }
}

