/**
 * 
 *
 * @author WILEDION
 */

package kani.monde;

import kani.utils.KaniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/**
 * Classe personnage de jeu
 */
public abstract class Monst extends Perso {

    int layer;

    public Monst()   {
        sprite = new Animation();
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

    public float getX()   {
        return posx;
    }

    public float getY()   {
        return posy;
    }
    
    public int getLayer() {
        return layer ;
    }

    public void setLevelWidth(int levelw)   {
        level_width = levelw ;
    }

    public void setLevelHeight(int levelh)   {
        level_height = levelh; 
        
    }

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

    abstract public Shape get_poly();
    
    abstract public void dessiner(Graphics gr) ;

    abstract public boolean isCibled() ;

    abstract public void set_cibled(float hx, float hy)  throws SlickException ;

    abstract public void update_hit() ;

    abstract public boolean is_dead() ;

    abstract public void update_nothing(float looptime)  throws SlickException ;
    
}
