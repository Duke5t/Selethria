import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileReader;
import java.io.BufferedReader;


public class TileMap {
    private int[][] map;
    private BufferedImage[] tiles;
    private final int tileSize = 16;

    public TileMap(String mapPath, String tilesetPath) {
        loadMap(mapPath);         // Load the map layout
        loadTiles(tilesetPath);    // Load tileset and slice into tiles
    }

    public void draw(Graphics g) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int tileId = map[row][col];
                if (tileId >= 0 && tileId < tiles.length) {
                    g.drawImage(tiles[tileId], col * tileSize, row * tileSize, null);
                }
            }
        }
    }

    private void loadMap(String path) {
        if (path == null) {
            // Use default hardcoded map if no file path is provided
            map = new int[][] {
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
            };
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                // Read the file to determine the number of rows and columns
                String line;
                int rowCount = 0;
                int colCount = 0;
                while ((line = br.readLine()) != null) {
                    if (map == null) {
                        // Determine columns from the first row
                        String[] values = line.split(",");
                        colCount = values.length;
                        // Assuming a max of 100 rows for demonstration; adjust as needed
                        map = new int[100][colCount];
                    }
    
                    // Split line by commas to get individual values
                    String[] values = line.split(",");
                    for (int col = 0; col < values.length; col++) {
                        map[rowCount][col] = Integer.parseInt(values[col]);
                    }
                    rowCount++;
                }
                // Trim the map array to the exact number of rows
                int[][] resizedMap = new int[rowCount][colCount];
                System.arraycopy(map, 0, resizedMap, 0, rowCount);
                map = resizedMap;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading map from file.");
            }
        }
    }

    private void loadTiles(String path) {
        try {
            // Line 1 below. Temporarily swapped lines 1 and 2. Line 2 is most correct. 
            BufferedImage tileset = ImageIO.read(getClass().getResource(path));
            // Line 2 below. Temporarily swapped lines 1 and 2. Line 2 is most correct. 
            // BufferedImage tileset = ImageIO.read(new File(path));
            int tilesetWidth = tileset.getWidth() / tileSize;
            int tilesetHeight = tileset.getHeight() / tileSize;
            tiles = new BufferedImage[tilesetWidth * tilesetHeight];

            for (int y = 0; y < tilesetHeight; y++) {
                for (int x = 0; x < tilesetWidth; x++) {
                    tiles[y * tilesetWidth + x] = tileset.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading tileset image.");
        }
    }

    public boolean isBlocked(int x, int y) {
        // Example collision detection for certain tile types (e.g., trees, water)
        int col = x / tileSize;
        int row = y / tileSize;
        if (col >= 0 && col < map[0].length && row >= 0 && row < map.length) {
            int tileId = map[row][col];
            return tileId == 1; // Assuming '1' is a blocking tile
        }
        return false;
    }
}
