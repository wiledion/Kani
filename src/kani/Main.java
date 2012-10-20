/**
 * Classe principale de lancement du jeu
 *
 * @author WILEDION
 */

package kani;

import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;



public class Main

{

    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 800;


    public static void main(String[] args)	throws SlickException, IOException
    {

         AppGameContainer appkani  =	new AppGameContainer(new AppKani());
         appkani.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
         appkani.setVSync(true);
         appkani.setShowFPS(false);
//         appkani.setMaximumLogicUpdateInterval(19);
//         appkani.setMinimumLogicUpdateInterval(21);
         appkani.setTargetFrameRate(30);
         appkani.setIcon("data/images/arc.png");

         appkani.start();
    }


}
