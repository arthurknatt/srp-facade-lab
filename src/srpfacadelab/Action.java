package srpfacadelab;

import java.util.List;

public class Action {
    private RpgPlayer rpgPlayer;
    private Inventory inventory;
    private Stats stats;

    public Action(RpgPlayer player, Inventory inv, Stats s) {
        rpgPlayer = player;
        inventory = inv;
        stats = s;
    }

    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = rpgPlayer.gameEngine.getEnemiesNear(rpgPlayer);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(Item item) {
        int weight = inventory.calculateInventoryWeight();
        if (weight + item.getWeight() > rpgPlayer.getCarryingCapacity())
            return false;

        if (item.isUnique() && inventory.checkIfItemExistsInInventory(item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            rpgPlayer.setHealth(rpgPlayer.getHealth() + item.getHeal());

            if (rpgPlayer.getHealth() > rpgPlayer.getMaxHealth())
                rpgPlayer.setHealth(rpgPlayer.getMaxHealth());

            if (item.getHeal() > 500) {
                rpgPlayer.gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
        {
            if (item.isUnique())
                rpgPlayer.gameEngine.playSpecialEffect("blue_swirly");
            else
                rpgPlayer.gameEngine.playSpecialEffect("cool_swirly_particles");
        }
        rpgPlayer.addInventory(item);

        stats.calculateStats();

        return true;
    }
}
