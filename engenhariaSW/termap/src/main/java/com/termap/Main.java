package com.termap;
import java.util.Scanner;

public class Main {
    private static Integer clsRange = 1;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        GameState gameState = new GameState(scan);

        while(true) {
            if (gameState.gCharacter.getIsDead()) {
                System.out.println("GAME OVER, TRY AGAIN!");
                gameState = new GameState(scan);
            }
            for(Integer i = 0; i < clsRange; i++) {
                System.out.println("");
            }
            gameState.render();
            String input = scan.next();
            input = input.toLowerCase();
            if(input.equals("q")) {
                break;
            }
            gameState.process(input);
            
        }
        scan.close();
    }
}