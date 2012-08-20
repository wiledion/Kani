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

import java.util.ArrayList;
import kani.monde.Fleche;
import kani.monde.Heros;
import kani.monde.Statue;
import kani.utils.Block;
import kani.utils.KaniMap;
import kani.utils.Camera;
import kani.utils.Timer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;


public class PlayState   extends LevelState {

    Heros hkani ;


    public    PlayState( int stateID )   {
   
       super();
       this.stateID = stateID;
    }


    public int getID() {
        return stateID;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {


    input = gc.getInput();
    cam = new Camera();
    level_reset();
    
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
         
        for ( int layer = 0; layer<= 2;layer++)   {
        
            map.maptest.render(cam, layer);
            if ( layer == 1)
            {   
                
            for (int i = 0; i < ennemis.size(); i++)   {
     
                Statue kmonst = ennemis.get(i);
                kmonst.dessiner();

            }
        
            hkani.dessiner();
        }

        img.tablo.draw(0,450);
        img.logo.draw(701,500);
        hkani.draw_inf(gr);
        KaniMap tmap = map.maptest;
        Block entity1 = (Block) tmap.blocked.get(6);
        entity1.draw(gr);
                
        }

    if (paused)   {
    gr.drawString("PAUSE", 350,500);}
    
    is_dead(sbg);
     
    if ( victory)   {
        gr.drawString("Niveau test complété", 350,500);
    }
    int u = ennemis.size();
    gr.setColor(Color.white);
    gr.drawString( "Monstres restants : " +  (ennemis.size()) + "",150,520 );
    gr.drawString(" Niveau - test " , 350,550);
    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))   {
        sbg.enterState(2);           
        level_reset();
        running = false;
    }  
    }
                                
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    
        if ( !victory)  {
        
            if ( !paused)  {
                if (input.isKeyDown(Input.KEY_UP))  {
                      hkani.update_up(); 
                }

                if (input.isKeyDown(Input.KEY_DOWN))  {
                      hkani.update_down();
                }
                  
                if (input.isKeyDown(Input.KEY_LEFT))  {
                     hkani.update_left();
                }
                 
                if (input.isKeyDown(Input.KEY_RIGHT))  {
                     hkani.update_right();
                }
            
                if (!input.isKeyDown(Input.KEY_UP) 
            		 && !input.isKeyDown(Input.KEY_RIGHT) 
            		 && !input.isKeyDown(Input.KEY_DOWN) 
            		 && !input.isKeyDown(Input.KEY_LEFT) )  {
                    hkani.update_nothing();
                }

                if (input.isKeyDown(Input.KEY_Q))  {
                    hkani.update_tir();
                }   
                
                if (input.isKeyDown(Input.KEY_P))  {
                    if ( clock_pause.getElapsedTime() > 1000)
                    {
                        paused = true ;
                        clock_pause.Reset();
                    }
                }             
                heroColl_update();
                monst_update();
                map.maptest.update(cam);
            }
            else   if (input.isKeyDown(Input.KEY_P))  {
                if ( clock_pause.getElapsedTime() > 1000)
                    { paused = false ;
                    clock_pause.Reset();
                }
            }   
              
        }
        
        if ( ennemis.size() == 0)  {
            victory = true ;
            hkani.update_nothing();
        }
  
    }

    private String String(boolean band) {
        if (band = true)
            {return "true";}
        else
            return "false";

    }

    private void heroColl_update()
    {
        Polygon hero = hkani.get_poly();
            for (int i = 0; i < ennemis.size(); i++) {
                Statue kmonst = ennemis.get(i);
                if ( hero.intersects(kmonst.get_poly()) )
                    {hkani.addTo_pv(-1);
                    break;
                }
            }
    }
        
    private void is_dead(StateBasedGame sbg) throws SlickException  {
    
        if ( hkani.get_pv() <= 0 )
            {
                sbg.enterState(2);           
                level_reset();
                running = false;
            }
    }
        
      private void level_reset() throws SlickException
      {
      
        hkani = new Heros();
        running = true;
        paused = false;
        victory = false;
        ennemis = new ArrayList<Statue>();
        for (int i = 0; i < 20; i++) {
            ennemis.add(new Statue());
        }
        cam.setCam(0,0);
        hkani.setMap(map.maptest);
        hkani.setCamera(cam);

        for (int i = 0; i < ennemis.size(); i++) {
 		
            Statue kmonst = ennemis.get(i);
            kmonst.setMap(map.maptest);
            clock_pause = new Timer();
            clock_pause.Reset();
        
        }     
    }

    private void monst_update() throws SlickException {
        
        for (int i = 0; i < ennemis.size(); i++) {
            Statue kmonst = ennemis.get(i);
            kmonst.update_nothing();
            int hx = hkani.getX();
            int hy = hkani.getY();
            if (( Math.abs(kmonst.getX() - hx) < 500) && (Math.abs( kmonst.getY() - hy) < 500) && !kmonst.isCibled())
                {kmonst.set_des(hx,hy);}

        }
                        
        ArrayList<Fleche> all_arrow = hkani.get_all_arrow();
        for ( int i =0; i < ennemis.size(); i++)  {
            for ( int j =0; j < all_arrow.size(); j++)  {
                Statue kmonst = ennemis.get(i);
                Fleche karrow = all_arrow.get(j);
                Shape enn_poly = kmonst.get_poly();
                Shape arr_poly = karrow.get_poly();
          
                if ( enn_poly.intersects(arr_poly))  {
                    ennemis.remove(i);
                    hkani.remove_arrow(j);   
                    if( i>0)
                        i--; 
                    if( j>0)
                        j--;
                }
            }
        }
          
    }
          
    public void set_running()
    {
    running = true;
    }
      
      
}
