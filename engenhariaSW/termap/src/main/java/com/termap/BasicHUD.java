package com.termap;

public class BasicHUD {
    public static void showPlayerStats(GCharacter gCharacter) {
        System.out.println(gCharacter.name + "- HP: " + gCharacter.getHp() + " - MP: " + gCharacter.getMana());
        System.out.println("");
    }
}
