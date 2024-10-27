import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final boolean[] keys = new boolean[256];  // Array to store key states

    // Update the state of the keys
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    // Method to check if a key is currently pressed
    public boolean isKeyDown(int keyCode) {
        return keys[keyCode];
    }
}