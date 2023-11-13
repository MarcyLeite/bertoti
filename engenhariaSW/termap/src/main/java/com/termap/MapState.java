package com.termap;

import com.termap.GameState.State;

public class MapState {
    private static Integer size = 5;
    private void renderBr() {
        for(Integer j = 0; j < size*2; j++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    private void renderLine(Integer i, GCharacter gCharacter, Enemy enemy, Store store) {
        renderBr();
        Integer[] playerCoords = gCharacter.getCoords();
        Integer[] enemyCoords = enemy.getCoords();
        Integer[] storeCoords = store.getCoords();
        for(Integer j = 0; j < size; j++) {
            System.out.print("|");
            
            if(playerCoords[0] == j && playerCoords[1] == i) {
                System.out.print(gCharacter.getSymbol());
            } else if(enemyCoords[0] == j && enemyCoords[1] == i)  {
                System.out.print(enemy.getSymbol());
            } else if(storeCoords[0] == j && storeCoords[1] == i)  {
                System.out.print(store.getSymbol());
            }else {
                System.out.print(" ");
            }
        }
        System.out.println("|");
    }

    public void render(GameState gs) {
        for(Integer i = 0; i < size; i++) {
            renderLine(i, gs.getgCharacter(), gs.getEnemy(), gs.getStore());
        }
        renderBr();
    }
    public static Integer getSize() {
        return size;
    }

    public void process(String input, GameState gs) {
        GCharacter gCharacter = gs.getgCharacter();
        Store store = gs.getStore();
        Enemy enemy = gs.getEnemy();

        if(input.equals("d")) {
            gCharacter.move(1);
        }
        if(input.equals("w")) {
            gCharacter.move(2);
        }
        if(input.equals("a")) {
            gCharacter.move(3);
        }
        if(input.equals("s")) {
            gCharacter.move(4);
        }
        if(input.equals("i")) {
            gs.gotoState(State.SHOW_INVENTORY);
        }
        
        Integer[] storeCoords = store.getCoords();
        Integer[] playerCoords = gCharacter.getCoords();
        Integer[] enemyCoords = enemy.getCoords();
        if (storeCoords[0] == playerCoords[0] && storeCoords[1] == playerCoords[1]) {
            gs.gotoState(State.STORE);
        }        
        if (enemyCoords[0] == playerCoords[0] && enemyCoords[1] == playerCoords[1]) {
            gs.setFightState(new FightState(gCharacter, enemy));
            gs.gotoState(State.FIGHT);
        }
    }
}