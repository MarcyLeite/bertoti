package com.termap;

public class Item {
    public enum Effect {
        HEAL, DAMAGE, MANA
    }
    private String name;
    public String getName() {
        return name;
    }
    private Integer value;
    public Integer getValue() {
        return value;
    }
    private Effect effect;
    public Effect getEffect() {
        return effect;
    }
    public Item(String name, Effect effect, int value) {
        this.name = name;
        this.effect = effect;
        this.value = value;
    }
    public void apply(GCharacter character, Enemy enemy) {
        if(effect == Effect.HEAL) {
            character.takeDamage(-this.value);
        } else if (effect == Effect.DAMAGE) {
            enemy.takeDamage(value);
        } else {
            Integer mana = character.getMana();
            character.setMana(mana +this.value);
        }
    }
}
