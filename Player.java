import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;  // Import ImageIO for image loading
import java.io.IOException;    // Import IOException for handling errors
import java.awt.event.KeyEvent;


public class Player {

    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4, playerSprite;  // Player sprite images
    private int x, y;  // Player's position on the screen
    private int speed = 2;    // Movement speed
    public String direction = "down";
    private KeyInput input;         // Reference to KeyInput for handling inputs
    public int spriteCounter = 0;
    public int spriteNum = 0;


    public Player(TileMap map, KeyInput input) {
        loadPlayerSprites();         // Load the player sprite (replace the path with your sprite)


        this.input = input;

        if (playerSprite == null) {        // Verify if the player sprite loaded successfully
            System.out.println("Error: Failed to load player sprite!");
        } else {
            System.out.println("Player sprite loaded successfully.");
        }
        x = 50;  // Starting X position
        y = 50;  // Starting Y position
    }

    public void update() {
        // Sprint Speed
        if(input.isKeyDown(KeyEvent.VK_SHIFT)){
            speed = 2; 
        } else {
            speed = 1;
        }
        //increases spriteCounter only if WASD key event.
        if (input.isKeyDown(KeyEvent.VK_W) || input.isKeyDown(KeyEvent.VK_S) || 
            input.isKeyDown(KeyEvent.VK_A) || input.isKeyDown(KeyEvent.VK_D)){
            // Use KeyInput to check if keys are pressed      
            if (input.isKeyDown(KeyEvent.VK_W)) {
                y -= speed;
                direction = "up";
            }
            if (input.isKeyDown(KeyEvent.VK_S)) {
                y += speed;
                direction = "down";
            }
            if (input.isKeyDown(KeyEvent.VK_A)) {
                x -= speed;
                direction = "left";
            }
            if (input.isKeyDown(KeyEvent.VK_D)) {
                x += speed;
                direction = "right";
            }
            //Adds to sprite animation counter
            spriteCounter++;

            // change image after 10 units of time (based on game class calling update method)
            if(spriteCounter > 10) {
                spriteNum += 1;
                //Accounts for sprite image loop size 4 (4 images).
                if (spriteNum > 3) {
                    spriteNum = 0;
                }                
                //resets sprite loop timer
                spriteCounter = 0;
            }

        }
    }

    public void draw(Graphics g) {

        playerSprite = getPlayerSprite();
        if (playerSprite != null) {
            g.drawImage(playerSprite, x, y, null);  // Draw the player sprite at position (x, y)
        } 
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /* 
     * Used to return new player sprite image variable name based on variables 
     * (direction and spriteNum) derived from update class. 
     * 
    */
    private BufferedImage getPlayerSprite() {
        switch (direction) {
            case "up":
                switch(spriteNum) {
                    case 0:
                        return up1;
                    case 1:
                        return up2;
                    case 2:
                        return up3;
                    case 3:
                        return up4;
                }
            case "down":
                switch(spriteNum) {
                    case 0:
                        return down1;
                    case 1:
                        return down2;
                    case 2:
                        return down3;
                    case 3:
                        return down4;
                }
            case "right":
                switch(spriteNum) {
                    case 0:
                        return right1;
                    case 1:
                        return right2;
                    case 2:
                        return right3;
                    case 3:
                        return right4;
                }
            case "left":
                switch(spriteNum) {
                    case 0:
                        return left1;
                    case 1:
                        return left2;
                    case 2:
                        return left3;
                    case 3:
                        return left4;
                }
        } 
        return down1;
    }

    // Hardcoded palyer sprite default starting and directional animation images
    public void loadPlayerSprites() {
        playerSprite = loadImage("sprites/characters/hero/hero_00.png");
        left1 = loadImage("sprites/characters/hero/hero_57.png");
        left2 = loadImage("sprites/characters/hero/hero_58.png");
        left3 = loadImage("sprites/characters/hero/hero_59.png");
        left4 = loadImage("sprites/characters/hero/hero_60.png");
        right1 = loadImage("sprites/characters/hero/hero_06.png");
        right2 = loadImage("sprites/characters/hero/hero_07.png");
        right3 = loadImage("sprites/characters/hero/hero_08.png");
        right4 = loadImage("sprites/characters/hero/hero_09.png");
        down1 = loadImage("sprites/characters/hero/hero_01.png");
        down2 = loadImage("sprites/characters/hero/hero_02.png");
        down3 = loadImage("sprites/characters/hero/hero_03.png");
        down4 = loadImage("sprites/characters/hero/hero_04.png");
        up1 = loadImage("sprites/characters/hero/hero_12.png");
        up2 = loadImage("sprites/characters/hero/hero_13.png");
        up3 = loadImage("sprites/characters/hero/hero_14.png");
        up4 = loadImage("sprites/characters/hero/hero_15.png");
    }


}
