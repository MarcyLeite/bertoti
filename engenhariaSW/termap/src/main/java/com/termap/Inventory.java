package com.termap;

import java.util.ArrayList;

import com.termap.GameState.State;

public class Inventory {
    public static void render(GCharacter gCharacter) {
        ArrayList<Item> itemList = gCharacter.getInventory();
        System.out.println("Money: " + gCharacter.getMoney());

        for(Item item: itemList) {
            System.out.println(item.getName() + ": " + item.getValue());
        }

    }
    public static void process(String input, GameState gs) {
        if(input.equals("l")) {
            gs.gotoState(State.MAP);
        }

    }
}
