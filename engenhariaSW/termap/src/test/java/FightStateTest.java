import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.termap.Enemy;
import com.termap.FightState;
import com.termap.GCharacter;
import com.termap.GameState;
import com.termap.Item;

public class FightStateTest {
    GCharacter player = new GCharacter();
    Enemy enemy = new Enemy();
    GameState gameState = new GameState(null);
    FightState fightState = new FightState(player, enemy);

    @Before
    public void beforeEach() {
        this.player = new GCharacter();
        this.enemy = new Enemy();
        this.fightState = new FightState(player, enemy);
    }

    @Test
    public void enemyDamage() {
        fightState.process("a", gameState);
        fightState.process("1", gameState);

        assertTrue(enemy.getHp() == 16);
        
        fightState.process("a", gameState);
        fightState.process("2", gameState);

        assertTrue(enemy.getHp() == 11);
    }

    @Test
    public void playerManaSpent() {
        player.setMana(6);

        fightState.process("s", gameState);
        fightState.process("1", gameState);

        Integer enemyHealth = enemy.getHp();

        System.out.println(player.getMana());

        assertTrue(player.getMana() == 2);

        fightState.process("s", gameState);
        fightState.process("1", gameState);

        assertTrue(player.getMana() == 2);
        assertTrue(enemy.getHp() == enemyHealth);
    }

    @Test
    public void playerItemSpent() {
        Item testItem = new Item("testItem", Item.Effect.DAMAGE, 3);
        player.addItem(testItem);

        fightState.process("i", gameState);
        fightState.process("1", gameState);
    
        assertTrue(player.getInventory().size() == 0);
        assertTrue(enemy.getHp() == 17);
    }

    @Test
    public void playerDamageTaken() {
        fightState.process("a", gameState);
        fightState.process("1", gameState);


        assertTrue(player.getHp() < 20);
    }

    @Test
    public void playerDeath() {
        player.takeDamage(19);
        enemy.takeDamage(-100);
        fightState.process("a", gameState);
        fightState.process("1", gameState);


        assertTrue(player.getHp() < 0);
        assertTrue(player.getIsDead());
    }
}
