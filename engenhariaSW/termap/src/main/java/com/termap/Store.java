package com.termap;

import com.termap.GameState.State;
import com.termap.Item.Effect;

public class Store extends GCharacter {
    private Object[][] itemPool = new Object[][]{
        {"Vida menor", Effect.HEAL, 4, 2},
        {"Vida maior", Effect.HEAL, 8, 7},
        {"Boom!", Effect.DAMAGE, 3, 4},
        {"BOOM!!!!!", Effect.DAMAGE, 9, 10},
        {"Mana menor", Effect.MANA, 4, 4},
        {"Mana maior", Effect.MANA, 8, 8},
    };
    public String getSymbol() {
        return "L";
    }
    public void getCatalog() {
        Integer i = 1;
        for(Object[] itemData: itemPool) {
            System.out.println(i.toString() + "-> " + itemData[0] + " : " + itemData[3]);
            i++;
        }
    }
    public void render() {
        System.out.println("Escolha um item: ");
        System.out.println("");
        getCatalog();
    }
    private void close(GameState gs) {
        gs.spawnStore();
        gs.gotoState(State.MAP);
    }
    public Boolean buy(Integer index, GCharacter gCharacter) {
        Object[] toBuy = itemPool[index];
        if(gCharacter.getMoney() - ((Integer)toBuy[3]) < 0) {
            System.out.println("Not enough money");
            return false;
        }
        Item item = new Item((String)toBuy[0], (Effect)toBuy[1], (Integer)toBuy[2]);
        gCharacter.addItem(item);
        return true;
    }
    public void process(String input, GameState gs) {
        if(input.equals("l")) {
            close(gs);
            return;
        }
        try {
            Integer index = Integer.parseInt(input) - 1;
            if(index >= itemPool.length) {
                System.out.println("Out of range");
                return;
            }
            buy(index, gs.getgCharacter());
            close(gs);

        } catch (Exception e) {
            System.out.println("Can only recieve INT");
        }
    }
}
