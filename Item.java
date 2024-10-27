public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void inspect() {
        System.out.println("Item: " + name);
    }
}
