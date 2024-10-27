import javax.swing.JFrame;  // For JFrame
import java.awt.Dimension;  // For Dimension
import java.awt.Canvas;  // For Canvas
import java.awt.Graphics;  // For Graphics
import java.awt.image.BufferStrategy;  // For BufferStrtegy
import java.awt.image.BufferedImage;  // For BufferedImage
import java.io.IOException;
import javax.imageio.ImageIO;  // For loading images

public class Game extends Canvas implements Runnable {
    private boolean isRunning = false;
    private Thread thread;
    private GameStateManager gsm;
    private KeyInput keyInput;
    private TileMap tileMap;

    public static final int WIDTH = 389; // 24 x 16px tiles  * scale
    public static final int HEIGHT = 237; // 14 x 16px tiles * scale
    public static final int SCALE = 3;

    public synchronized void start() {
        if (isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public Game() {
        keyInput = new KeyInput();

        addKeyListener(keyInput);  // Attach key listener
        setFocusable(true);        // Set focus to capture key events
    }

    public void init() {
        keyInput = new KeyInput();
        tileMap = new TileMap("C:/Code/Java/Selethria/testTile4.csv", "/sprites/tilesets/plains.png"); //Hardcode: TileMap ( CSV Map File input , TileSheet PNG )
        if (tileMap == null) {
            System.out.println("Failed to load TileMap");
        } else {
            System.out.println("TileMap loaded successfully!");
        }
        gsm = new GameStateManager(tileMap, keyInput);  // Pass tileMap and keyInput to the GameStateManager
        addKeyListener(keyInput);  // Register KeyInput as the key listener
        setFocusable(true);  // Ensure the Canvas is focused to capture keys
    }

    @Override
    public void run() {
        init();
        
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;  // 60 ticks per second
        double delta = 0;
        
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
    
            while (delta >= 1) {
                tick();   // Update game logic
                delta--;
            }
            
            render();  // Render the game
        }
    }
    

    public void tick() {
        gsm.update();  // Update game state
        // Any other game logic goes here
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();  // Get the buffer strategy
        if (bs == null) {
            this.createBufferStrategy(3);  // Create a triple buffer strategy if not already created
            return;
        }
    
        Graphics g = bs.getDrawGraphics();  // Get the graphics object from the buffer strategy
    
        // Clear the screen (fill it with black or any background color)
        g.clearRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
    
        // Call the GameStateManager's draw method to render the game content
        gsm.draw(g);
    
        g.dispose();  // Dispose of the graphics object to free resources
        bs.show();    // Show the next buffer in the BufferStrategy
    }
    

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Selethria");
        frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();  // Start the game loop
    }
}
