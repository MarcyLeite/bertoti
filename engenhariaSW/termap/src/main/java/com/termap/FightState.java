package com.termap;
import java.util.ArrayList;

import com.termap.GameState.State;

public class FightState {
    private GCharacter gCharacter;
    private Enemy enemy;
    public FightState(GCharacter gCharacter, Enemy enemy) {
        this.gCharacter = gCharacter;
        this.enemy = enemy;
    }
    
    private String[] actionList = new String[]{
        "a - Attack",
        "s - Spell",
        "i - Item"
    };

    private enum Phase {
        ACTION, POST_ACTION, ENEMY
    };
    private enum ActionType {
        ATTACK, SPELL, ITEM
    }
    Phase phase = Phase.ACTION;
    ActionType actionType;
    public void render() {
        BasicHUD.showPlayerStats(enemy);
        System.out.println(gCharacter.name + " is fighting " + enemy.name);
        if(phase == Phase.ACTION) {
            for (String action: actionList) {
                System.out.println(action);
            }
        }
        if(phase == Phase.POST_ACTION) {
            Integer i = 1;
            if(actionType == ActionType.ATTACK) {
                for(Object[] attack: gCharacter.getAttackList()) {
                    System.out.println(i + ": " + attack[0]);
                    i++;
                }
            } else if (actionType == ActionType.SPELL) {
                for(Object[] spell: gCharacter.getSpellList()) {
                    System.out.println(i + ": " + spell[0]);
                    i++;
                }                
            }  else if (actionType == ActionType.ITEM) {
                for(Item item: gCharacter.getInventory()) {
                    System.out.println(i + ": " + item.getName());
                    i++;
                }                
            }
        }
    }
    public void process(String input, GameState gs) {
        if(phase == Phase.ACTION) {
            if(input.equals("a")) {
                phase = Phase.POST_ACTION;
                actionType = ActionType.ATTACK;
            }
            else if(input.equals("s")) {
                phase = Phase.POST_ACTION;
                actionType = ActionType.SPELL;
            }
            else if(input.equals("i")) {
                phase = Phase.POST_ACTION;
                actionType = ActionType.ITEM;
            }
        }
        if(phase == Phase.POST_ACTION) {
            if(input.equals("l")) {
                phase = Phase.ACTION;
                return;
            }
            try {
                Integer index = Integer.parseInt(input) - 1;
                String used = "";
                if(actionType == ActionType.ATTACK) {
                    Object[][] attackList = gCharacter.getAttackList();
                    if(index >= attackList.length) {
                        System.out.println("Out of index");
                        return;
                    }
                    Object[] attack = attackList[index];
                    enemy.takeDamage((int)attack[1]);
                    used = (String)attack[0];

                } else if (actionType == ActionType.SPELL) {
                    Object[][] spellList = gCharacter.getSpellList();
                    if(index >= spellList.length) {
                        System.out.println("Out of index");
                        return;
                    }
                    Object[] spell = spellList[index];
                    Integer mana = gCharacter.getMana();
                    if(mana - (int)spell[2] < 0) {
                        System.out.println("Not enough Mana");
                        return;
                    }

                    enemy.takeDamage((int)spell[1]);
                    gCharacter.setMana(mana - (int)spell[2]);

                    used = (String)spell[0];

                } else if (actionType == ActionType.ITEM) {
                    ArrayList<Item> itemList = gCharacter.getInventory();
                    if(index >= itemList.size()) {
                        System.out.println("Out of index");
                        return;
                    }
                    Item item = itemList.get(index);

                    item.apply(gCharacter, enemy);
                    itemList.remove(item);

                    used = item.getName();
                }

                System.out.println(gCharacter.name + " used " + used);
                phase = Phase.ENEMY;
            } catch(Exception e) {
                System.out.println("Should be Integer");
            }
        }
        if(phase == Phase.ENEMY) {
            Object[][] attackList = enemy.getAttackList();
            Integer attackIndex = (int) ((Math.random() * (attackList.length-1)));
            Object[] attack = attackList[attackIndex];

            System.out.println(enemy.name + " use " + attack[0]);
            phase = Phase.ACTION;
            gCharacter.takeDamage((int)attack[1]);
        }

        if(enemy.getIsDead()) {
            gs.state = State.MAP;
            return;
        }
    }
}
