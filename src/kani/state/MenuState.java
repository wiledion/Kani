/*
 * Copyright (c) 2012, Bonzi Wilédio
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 *    - Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 *    - Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *    - Neither the name of the copyright holder nor the names of its contributors 
 * may be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF 
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package kani.state;


import kani.data.ImgManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class MenuState extends BasicGameState {

    int stateID = -1;
    public enum Modemenu { MENUG, JEUSLCT, QUITSLCT};
    public Modemenu modemenu = null ;
    Input input;
    ImgManager imgmanager = ImgManager.get();


    public  MenuState( int stateID )   {
        this.stateID = stateID;
    }

    public int getID()  {
        return stateID;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException  {
        input = gc.getInput();
        modemenu = Modemenu.MENUG;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
        imgmanager.menu_menu.draw();

        switch(modemenu)   {
            case MENUG:
                imgmanager.menu_jeu1.draw(100,432);
                imgmanager.menu_quit1.draw(356,432);
                break;
                
            case JEUSLCT:
                imgmanager.menu_jeu2.draw(100,432);
                imgmanager.menu_quit1.draw(356,432);
                break;
                
            case QUITSLCT:
                imgmanager.menu_jeu1.draw(100,432);
                imgmanager.menu_quit2.draw(356,432);
                break;
        }

        imgmanager.logo.draw(700,500);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        modemenu = Modemenu.MENUG;
        // Vérifications boutton Jeu
        if (input.getMouseX() > 100 && input.getMouseX() < 200 && input.getMouseY() > 432 && input.getMouseY() < 560)   {
            modemenu = Modemenu.JEUSLCT;
            if (input.isMousePressed(0)==true)   {
                sbg.enterState(3);
            }
        }

        // Vérification boutton Quitter
        if (input.getMouseX() > 356 && input.getMouseX() < 484 && input.getMouseY() > 432 && input.getMouseY() < 560 )   {
            modemenu = Modemenu.QUITSLCT;
            if (input.isMousePressed(0)==true)   {
                gc.exit();
            }
        }

    }

}
