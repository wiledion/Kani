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
