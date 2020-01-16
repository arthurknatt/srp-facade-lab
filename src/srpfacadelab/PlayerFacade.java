package srpfacadelab;

public class PlayerFacade {
    private RpgPlayer rpgPlayer;
    public Action action;
    public Inventory inventory;
    public Stats stats;

    public PlayerFacade(IGameEngine gameEngine) {
        rpgPlayer = new RpgPlayer(gameEngine);
        inventory = new Inventory(rpgPlayer);
        stats = new Stats(rpgPlayer, inventory);
        action = new Action(rpgPlayer, inventory, stats);

    }

    public void useItem(Item item) {
        this.action.useItem(item);
    }

    public boolean pickUpItem(Item item) {
        return this.action.pickUpItem(item);
    }

    public boolean checkIfItemExistsInInventory(Item item) {
        return this.inventory.checkIfItemExistsInInventory(item);
    }

    public int calculateInventoryWeight() {
        return this.inventory.calculateInventoryWeight();
    }

    public void calculateStats() {
        this.stats.calculateStats();
    }

    public void takeDamage(int damage) {
        this.stats.takeDamage(damage);
    }

}
