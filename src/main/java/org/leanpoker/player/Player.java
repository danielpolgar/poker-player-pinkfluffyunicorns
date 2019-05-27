package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.*;

public class Player {

    static final String VERSION = "0.2";

    public static int betRequest(JsonElement request) {
        Gson gson = new Gson();
        GameState gameState = gson.fromJson(request,GameState.class);
        int defaultBet = gameState.current_buy_in - gameState.players[gameState.in_action].bet + gameState.minimum_raise;
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
                return -1;
            }
        }
        Map<String, Integer> ranks = new HashMap<>();
        for (Card card : relevantCards) {
            ranks.put(card.rank, ranks.getOrDefault(card.rank, 0) + 1);
        }
        for (Integer count : ranks.values()) {
            if (count > 1) return 50;
        }
        if (relevantCards.size() == 5) return -1;
        return 0;
    }
}
