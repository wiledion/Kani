/*
 * Kani - Code source
 *
 * Par Wiledion
 * 
 */
package kani.state;

import java.util.ArrayList;
import kani.data.ImgManager;
import kani.data.MapManager;
import kani.monde.Statue;
import kani.utils.Camera;
import kani.utils.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class LevelState extends BasicGameState {
    
    int stateID = -1;
    boolean running;
    boolean paused;
    boolean victory;
    MapManager map = MapManager.get();
    ImgManager img = ImgManager.get();
    Input input;
    Camera cam;
    public  ArrayList<Statue> ennemis;
    Timer clock_pause;

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
      
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
       
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
       
    }
    
}
