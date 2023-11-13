package com.termap;
import java.util.Scanner;

public class GameState {
    MapState mapState;
    FightState fightState;
    public void setFightState(FightState fightState) {
        this.fightState = fightState;
    }
    GCharacter gCharacter;
    public GCharacter getgCharacter() {
        return gCharacter;
    }
    Enemy enemy;
    public Enemy getEnemy() {
        return enemy;
    }
    Store store;
    public Store getStore() {
        return store;
    }
    Scanner scan;
    
    enum State {
        MAP, STORE, FIGHT, SHOW_INVENTORY
    }
    State state;

    public GameState(Scanner scan) {
        this.state = State.MAP;
        this.mapState = new MapState();
        this.gCharacter = new GCharacter();
        spawnStore();
        spawnEnemy();
    }
    
    public void spawnEnemy() {
        Enemy enemy = new Enemy();
        enemy.randomizePosition();
        Integer[] storeCoords = store.getCoords();
        Integer[] enemyCoords = enemy.getCoords();
        Integer[] playerCoords = gCharacter.getCoords();
        this.enemy = enemy;
        if ((enemyCoords[0] == playerCoords[0] && enemyCoords[1] == playerCoords[1]) ||
        (enemyCoords[0] == storeCoords[0] && enemyCoords[1] == storeCoords[1])) {
            spawnEnemy();
        }
    }    
    public void spawnStore() {
        Store store = new Store();
        store.randomizePosition();
        Integer[] storeCoords = store.getCoords();
        Integer[] playerCoords = gCharacter.getCoords();
        this.store = store;
        if (storeCoords[0] == playerCoords[0] && storeCoords[1] == playerCoords[1]) {
            spawnStore();
        }
    }

    public void render() {
        BasicHUD.showPlayerStats(gCharacter);
        if(state == State.MAP) {
            mapState.render(this);
        } else if(state == State.STORE) {
            store.render();
        } else if(state == State.SHOW_INVENTORY) {
            Inventory.render(gCharacter);
        } else if(state == State.FIGHT) { 
            fightState.render();
        }
    }
    public void process(String input) {
        if(enemy == null || enemy.getIsDead()) {
            spawnEnemy();
        }
        if(state == State.MAP) {
            mapState.process(input, this);
        } else if(state == State.STORE) {
            store.process(input, this);
        } else if(state == State.SHOW_INVENTORY) {
            Inventory.process(input, this);
        } else if(state == State.FIGHT) { 
            fightState.process(input, this);
        }
    }
    public void gotoState(State newState) {
        this.state = newState;
    }
}
