

package UI;

import java.awt.Graphics2D;

import game.pacman.ClientGame;

// Ta klasa służy do rysowania podanych jej danych na temat
// podłączonych do serwera graczy - tzn. ich numery, nicki i postacie.

public class PlayerDisplayObject extends TextObject {
    
    @Override
    public void drawEvent(Graphics2D graphics) {
        if (!visible)
        {return;}
        ClientGame clientGame = null;
        int playersLockedIn = game.getPlayerIds().size();
        if (game instanceof ClientGame)
        {
            clientGame = (ClientGame)game;
            if (clientGame.isReady()) playersLockedIn++;
        }
        
        int k = 1;
        x = xstart;
        y = ystart;
        
        if (!allConnected)
            ourText = "Players (" + playersLockedIn + "/" + game.getMaxPlayers() + "):";
        else
            ourText = "Players:";
        
        drawMyText(graphics);
        
        if ((clientGame != null) && (clientGame.isReady())) {
            // Rozpoczynając od siebie...
            y = ystart + k*fontHeight;

            ourText = k + " - " + game.playerName.value + " " + (char)(201+game.chosenCharacter.value);
            drawMyText(graphics);

            k++;
        }
        
        try {
            for (Integer id : game.getPlayerIds()) {
                
                // Rysujemy wpis danego gracza na odpowiedniej pozycji.
                int character = game.getPlayerCharacter(id);
                String nick = game.getPlayerName(id);

                y = ystart + k*fontHeight;

                ourText = k + " - " + nick + " " + (char)(201+character);
                drawMyText(graphics);

                k++;
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    
    public void setAllConnected() {
        allConnected = true;
    }
    
    private boolean allConnected = false;
}
