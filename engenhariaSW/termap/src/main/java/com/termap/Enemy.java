package com.termap;

public class Enemy extends GCharacter {
    private Object[][] attackList = new Object[][] {
        { "Headbutt", 4 }, { "Cut", 5 },
    };
    public Object[][] getAttackList() {
        return attackList;
    }
    public Enemy() {
        super();
        this.name = "Enemy";
    }
    public String getSymbol() {
        return "E";
    }
}
