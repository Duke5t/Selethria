import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private final int MAX_SLOTS = 16;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (items.size() < MAX_SLOTS) {
            items.add(item);
        } else {
            System.out.println("Inventory is full!");
        }
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public void openInventory() {
        // Logic to open and display inventory UI, navigate through items with WASD
    }
}
