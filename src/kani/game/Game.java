/**
 * 
 *
 * @author WILEDION
 */

package kani.game;

import kani.monde.Monde;


public class Game {

    Monde world;
    Scenario scenario;
    boolean isRunning;

    Game ()   {
    }

    void init() {  
        world.init();
        scenario.load();
    }

    void loadGame(){
    }

    void saveGame(){
    }

    public void newGame(){
        world=null;
        scenario=null;
        init();
    }

    void exitGame(){
        
    }

    void gameOver(){
        
    }

    public void update(){
        scenario.update();
        world.update();
    }

   public void render()  {
        world.render();
   }

   public boolean isrunning(){
   
       return true;
   }

}
