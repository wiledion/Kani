/*
 * Kani - Code source
 *
 * Par Wiledion
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

