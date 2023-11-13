package com.termap;
import java.util.ArrayList;

public class GCharacter {
    public GCharacter() {}
    public Integer xPos = 0;
    public Integer yPos = 0;
    private Boolean isDead = false;
    
    public String name = "Player";
    private Integer hp = 20;
    private Integer mana = 10;
    private Integer money = 10;

    private Object[][] attackList = new Object[][] {
        { "Punch", 4 }, { "Kick", 5 },
    };
    private Object[][] spellList = new Object[][] {
        { "Fireball:", 10, 4 }, { "Iceball", 14, 6 }
    };
    public Object[][] getSpellList() {
        return spellList;
    }
    public Object[][] getAttackList() {
        return attackList;
    }
    private ArrayList<Item> inventory = new ArrayList<>();
    public void addItem(Item item) {
        inventory.add(item);
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public Integer getHp() {
        return this.hp;
    }
    public Integer getMana() {
        return mana;
    }
    public void setMana(Integer mana) {
        this.mana = mana;
    }
    public Integer getMoney() {
        return money;
    }
    public void setMoney(Integer money) {
        this.money = money;
    }
    private Integer getRandom() {
        return(int) ((Math.random() * (MapState.getSize()-1)));
    }
    public void randomizePosition() {
        this.xPos = getRandom();
        this.yPos = getRandom();
    }
    public Boolean getIsDead() {
        return this.isDead;
    }
    public void takeDamage(Integer damage) {
        this.hp -= damage;
        if(hp <= 0) {
            isDead = true;
        }
    }
    public Integer[] getCoords() {
        return new Integer[]{this.xPos, this.yPos};
    }
    public String getSymbol() {
        return "P";
    }
    public void move(Integer direction) {
        Integer nextXPos = xPos;
        Integer nextYPos = yPos;
        if(direction == 1) {
            nextXPos += 1;
        } else if (direction == 2) {
            nextYPos -= 1;
        } else if (direction == 3) {
            nextXPos -= 1;
        } else if (direction == 4) {
            nextYPos += 1;
        }
        if (
            nextXPos < 0 || nextXPos >= MapState.getSize() ||
            nextYPos < 0 || nextYPos >= MapState.getSize() 
        ) {
            return;
        }
        xPos = nextXPos;
        yPos = nextYPos;
    }
}
