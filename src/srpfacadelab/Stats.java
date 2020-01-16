package srpfacadelab;

public class Stats {

    private RpgPlayer rpgPlayer;
    private Inventory inventory;

    public Stats(RpgPlayer player, Inventory inv) {
        rpgPlayer = player;
        inventory = inv;
    }

    public void calculateStats() {
        for (Item i: rpgPlayer.getInventory()) {
            rpgPlayer.setArmour(rpgPlayer.getArmour() + i.getArmour());
        }
    }

    public void takeDamage(int damage) {
        if (inventory.calculateInventoryWeight() < (0.5 * rpgPlayer.getCarryingCapacity()))
            damage = (int) (0.75 * damage);

        if (damage < rpgPlayer.getArmour()) {
            rpgPlayer.gameEngine.playSpecialEffect("parry");
        }

        int damageToDeal = damage - rpgPlayer.getArmour();
        rpgPlayer.setHealth(rpgPlayer.getHealth() - damageToDeal);

        rpgPlayer.gameEngine.playSpecialEffect("lots_of_gore");
    }
}
