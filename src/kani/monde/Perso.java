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

package kani.monde;

import kani.utils.KaniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

/**
 * Classe personnage de jeu
 */
public class Perso extends Chose {

    Animation sprite;
    int pv;
    int mana;
    int dx;
    int dy;
    int vit;
    int height;
    int width;
    int level_width;
    int level_height;
    int mode;
    Polygon surface;


    public Perso()   {
        sprite = new Animation();
        pv=100;
        mana=100;
        vit = 0;

     }


    public void init()   {
        sprite = new Animation();
    }

    public void dessiner()   {
        sprite.draw(posx,posy);
    }

    public void dessiner(float X, float Y)   {
        sprite.draw(X,Y);
    }

    public void setX(float X)   {
    }

    public void setY(float Y)   {
    }

    public int getX()   {
        return posx;
    }

    public int getY()   {
        return posy;
    }

    public void setLevelWidth(int levelw)   {
        level_width = levelw ;
    }

    public void setLevelHeight(int levelh)   {
        {level_height = levelh; 
        
        }}

    public void setAnim(Animation anim)   {
        sprite = anim;
        height = anim.getHeight() ;
        width = anim.getWidth();
    }

    void set(float X, float Y)   {
    }
    
    void deplacer(float X, float Y)   {
    }

    public void update(KaniMap map) throws SlickException   {
    
    }

    public void setMap(KaniMap maptest)   {
         setLevelWidth(maptest.getMapWidth());
         setLevelHeight(maptest.getMapHeight());
         surface = maptest.get_surface();
     }
    
}

