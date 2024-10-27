import java.awt.Graphics;  // Ensure this is imported in the class

public class ExplorationState {

    private Player player;
    private TileMap map;

    public ExplorationState(TileMap map, KeyInput keyInput) {
        this.map = map;
        this.player = new Player(map, keyInput);
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g) {
        map.draw(g);
        player.draw(g);

    }
    
}
