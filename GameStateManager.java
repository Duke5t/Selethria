import java.awt.Graphics;  // Add this import to fix the missing Graphics type

public class GameStateManager {

    private ExplorationState explorationState;

    public GameStateManager(TileMap map, KeyInput keyInput) {
        explorationState = new ExplorationState(map, keyInput);
    }

    public void update() {
        explorationState.update();
    }

    public void draw(Graphics g) {
        explorationState.render(g);  // Call the render method in ExplorationState
    }
    
}
