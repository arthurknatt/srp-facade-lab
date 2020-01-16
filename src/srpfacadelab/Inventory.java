package srpfacadelab;

public class Inventory {

    private RpgPlayer rpgPlayer;

    public Inventory(RpgPlayer player) {
        rpgPlayer = player;
    }

    public boolean checkIfItemExistsInInventory(Item item) {
        for (Item i: rpgPlayer.getInventory()) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    public int calculateInventoryWeight() {
        int sum=0;
        for (Item i: rpgPlayer.getInventory()) {
            sum += i.getWeight();
        }
        return sum;
    }
}
