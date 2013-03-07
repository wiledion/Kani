/**
 * 
 *
 * @author WILEDION
 */

package kani;

import kani.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class AppKani extends StateBasedGame

{
        public static final int LOADSTATE          = 0;
        public static final int INTROSTATE         = 1;
        public static final int MENUSTATE          = 2;
        public static final int PLAYSTATE          = 3;

     public AppKani()
    {
        super("Kani");
        this.addState(new LoadState(LOADSTATE));
        this.addState(new IntroState(INTROSTATE));
        this.addState(new MenuState(MENUSTATE));
        this.addState(new PlayState(PLAYSTATE));
        this.enterState(PLAYSTATE);

    }

    @Override
     public void initStatesList(GameContainer gameContainer) throws SlickException {

         this.getState(LOADSTATE).init(gameContainer, this);
         this.getState(INTROSTATE).init(gameContainer, this);
         this.getState(MENUSTATE).init(gameContainer, this);
         this.getState(PLAYSTATE).init(gameContainer, this);

    }

}
