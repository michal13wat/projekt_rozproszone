
package game.pacman;

// Od teraz, w tej klasie są wszystkie "klawiaturowe" sprawy GameObject.

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl {
    KeyboardControl(Game game) {
        this.game = game;
    }
    
    public void keyboardInit() {
        // Dodaje listenera dla klawiszy:
        
        game.getGameWindow().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                keyChar = e.getKeyChar();
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_LEFT): leftPressed = true;
                        break;
                    case (KeyEvent.VK_RIGHT): rightPressed = true;
                        break;
                    case (KeyEvent.VK_UP): upPressed = true;
                        break;
                    case (KeyEvent.VK_DOWN): downPressed = true;
                        break;
                    case (KeyEvent.VK_ESCAPE): escapePressed = true;
                        break;
                    case (KeyEvent.VK_ENTER): enterPressed = true;
                        break;
                    case (KeyEvent.VK_Q): qPressed = true; 
                        break;
                    case (KeyEvent.VK_BACK_SPACE): backspacePressed = true; 
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (keyChar == e.getKeyChar()) keyChar = 0;
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_LEFT): leftPressed = false; 
                        break;
                    case (KeyEvent.VK_RIGHT): rightPressed = false; 
                        break;
                    case (KeyEvent.VK_UP): upPressed = false;
                        break;
                    case (KeyEvent.VK_DOWN): downPressed = false;
                        break;
                    case (KeyEvent.VK_ESCAPE): escapePressed = false;
                        break;
                    case (KeyEvent.VK_ENTER): enterPressed = false;
                        break;
                    case (KeyEvent.VK_Q): qPressed = false;
                        break;
                    case (KeyEvent.VK_BACK_SPACE): backspacePressed = false; 
                        break;
                }
            }
        });
    }
    
    void keyboardSetHold() {
        if ((!backspaceHold) && (backspacePressed))  backspaceHoldCounter = 20;
        else {
            if (backspaceHoldCounter > 0) backspaceHoldCounter--;
        }
        
        leftHold = leftPressed;
        rightHold = rightPressed;
        upHold = upPressed;
        downHold = downPressed;
        escapeHold = escapePressed;
        enterHold = enterPressed;
        qHold = qPressed;
        backspaceHold = backspacePressed;
        
        prevKeyChar = keyChar;
    }
    
    boolean keyboardCheck(String key){
        // Czy klawisz jest wciśnięty?
        switch (key){
            case "left": return leftPressed;
            case "right": return rightPressed;
            case "up": return upPressed;
            case "down": return downPressed;
            case "escape": return escapePressed;
            case "enter": return enterPressed;
            case "q": return qPressed;
            case "backspace": return backspacePressed;
        }
        
        return false;
    }
    
    boolean keyboardHoldCheck(String key){
        // Czy klawisz był wciśnięty ostatnio?
        switch (key) {
            case "left": return leftHold;
            case "right": return rightHold;
            case "up": return upHold;
            case "down": return downHold;
            case "escape": return escapeHold;
            case "enter": return enterHold;
            case "q": return qHold;
            case "backspace": return backspaceHold && (backspaceHoldCounter > 0);
        }
        return false;
    }
    
    char keyboardCharCheck() {
        if (prevKeyChar == keyChar) return 0;
        return keyChar;
    }
    
    String checkPressedKeys(){
        String pressed = "";
        if (leftPressed) pressed += "l";       // left arrow
        if (rightPressed) pressed += "r";      // right arrow
        if (upPressed)  pressed += "u";        // up arrow
        if (downPressed) pressed += "d";       // down arrow
        if (escapePressed)  pressed += "x";    // EXIT
        if (enterPressed) pressed += "e";      // eneter
        if (qPressed) pressed += "q";          // q letter
        if (backspacePressed) pressed += "b";  // backspace
        return pressed;
    }
    
    private Game game;
   
    boolean leftPressed;
    boolean rightPressed;
    boolean upPressed;
    boolean downPressed;
    boolean escapePressed;
    boolean enterPressed;
    boolean qPressed;
    boolean backspacePressed;

    private boolean leftHold;
    private boolean rightHold;
    private boolean upHold;
    private boolean downHold;
    private boolean escapeHold;
    private boolean enterHold;
    private boolean qHold;
    private boolean backspaceHold;
    private int backspaceHoldCounter;
    
    private char prevKeyChar;
    private char keyChar;
}
