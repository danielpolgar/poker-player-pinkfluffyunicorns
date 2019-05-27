package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.*;

public class Player {

    static final String VERSION = "0.3";

    public static int betRequest(JsonElement request) {
        Gson gson = new Gson();
        GameState gameState = gson.fromJson(request,GameState.class);
        int defaultBet = gameState.current_buy_in - gameState.players[gameState.in_action].bet;
        return getRaiseValue(gameState) + defaultBet;
    }

    public static void showdown(JsonElement game) {
    }

    private static int getRaiseValue(GameState gameState) {
        List<Card> relevantCards = new ArrayList<>();
        for (Card card:gameState.community_cards) {
            if (card != null) {
                relevantCards.add(card);
            }
        }
        relevantCards.addAll(Arrays.asList(gameState.players[gameState.in_action].hole_cards));
        if (relevantCards.size() == 2) {
            if (!((relevantCards.get(0).rank.equals(relevantCards.get(1).rank))
                || (relevantCards.get(0).suit.equals(relevantCards.get(1).suit))
                || (relevantCards.get(0).getValue() >= 10 && relevantCards.get(1).getValue() >= 10)))
            {
                return 0;
            }
        }
        Map<String, Integer> ranks = new HashMap<>();
        Map<String, Integer> suits = new HashMap<>();
        for (Card card : relevantCards) {
            ranks.put(card.rank, ranks.getOrDefault(card.rank, 0) + 1);
            suits.put(card.suit, ranks.getOrDefault(card.suit, 0) + 1);
        }
        boolean flag = false;
        for (Integer count : ranks.values()) {
            if (count > 1) flag = true;
        }
        for (Integer count : suits.values()) {
            if (count >= 5) flag = true;
        }
        if (flag) return 50; // getBetValue(relevantCards, ranks, suits);
        if (relevantCards.size() == 5) return 0;
        return 0;
    }

    private static int getBetValue(List<Card> relevantCards, Map<String, Integer> ranks, Map<String, Integer> suits) {
        boolean hasTwo = false;
        boolean hasThree = false;
        boolean hasTwoPairs = false;
        for (Integer count : ranks.values()) {
            if (count == 4) return HandRanking.FOUROFAKIND.bet;
            if (count == 3) return 0;
        }
        for (Integer count : suits.values()) {
            if (count >= 5) return HandRanking.FLUSH.bet;
        }
        return 0;
    }
}
