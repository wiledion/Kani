/**
 * 
 *
 * @author WILEDION
 */

package kani.state;


import kani.data.ImgManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.loading.LoadingList;


public class IntroState extends BasicGameState {

    int stateID = -1;
    Image splash = null;
    boolean fx = true;
    int i = 0;
    int img=0;

    ImgManager imgmanager = ImgManager.get();


    public  IntroState( int stateID )   {
        this.stateID = stateID;
   }


    public int getID() {
        return stateID;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException  {
        LoadingList.setDeferredLoading(true);
        splash = imgmanager.intro_splash1;
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {

    splash.setAlpha((float)i/255);
    splash.draw( (800 - splash.getWidth())/2  , (600 - splash.getHeight())/2);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException  {

                    // Affichage splash
        if (fx)
        {
            if (i<370)
                i+=7;
                else
                fx = false;

        }

        else
        {i-=7;
         if (i==0)
            {fx=true;
             img++;
            }
         }

        switch(img)
        {case 0:
             splash = imgmanager.intro_splash1;
             break;
        case 1:
             splash = imgmanager.intro_splash2;
             break;
        case 2:
             splash = imgmanager.intro_splash3;
             break;
        case 3:
             sbg.enterState(2);
             break;

        }

    }


}
