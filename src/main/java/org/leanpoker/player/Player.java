package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Map;

public class Player {

    static final String VERSION = "0.2";

    public static int betRequest(JsonElement request) {
        Gson gson = new Gson();
        GameState gameState = gson.fromJson(request,GameState.class);
        return gameState.current_buy_in - gameState.players[gameState.in_action].bet + gameState.minimum_raise + 10;
    }

    public static void showdown(JsonElement game) {
    }
}
