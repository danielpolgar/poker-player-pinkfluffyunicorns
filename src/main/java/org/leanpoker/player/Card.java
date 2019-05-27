package org.leanpoker.player;

public class Card {
    String rank;
    String suit;

    public int getValue() {
        int value = 0;
        if (Utils.isInteger(rank)) {
            value = Integer.parseInt(rank);
        } else {
            switch (rank) {
                case "J":
                    value =  11;
                    break;
                case "D":
                    value =  12;
                    break;
                case "K":
                    value =  13;
                    break;
                case "A":
                    value =  14;
                    break;
            }
        }
        return value;
    }
}
