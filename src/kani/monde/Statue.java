/**
 * 
 *
 * @author WILEDION
 */


package kani.monde;

import java.util.Random;
import kani.data.AnimManager;
import kani.data.MapManager;
import kani.utils.Block;
import kani.utils.KaniMap;
import kani.utils.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Statue extends Perso {

    AnimManager anim = AnimManager.get();

    Animation move ;
    Animation stand;
    MapManager map = MapManager.get();
    Polygon poly ;
    Timer clock;
    Timer clock_coll;
    Timer clock_cible;
    int desx;
    int desy;
    Random r;
    boolean ifwait;
    boolean cibled;

    public Statue() throws SlickException   {
        super();
        r = new Random();
        vit = 2;
        move = anim.stat;
        stand = anim.stat;
        sprite = stand;
        height = move.getHeight();
        width = move.getWidth();
        poly = new Polygon(new float[]{
			posx , posy,
			posx + width, posy,
			posx + width, posy + height,
			posx , posy + height
		});
      
        randompos();

    	clock = new Timer();
        clock_coll = new Timer();
        clock_cible = new Timer();
        clock_cible.Reset();
    	ifwait = false;   
        cibled = false;
        randomdes();
      
        midx = width/2;
        midy = height/2;

     }

    public void dessiner()   {
         sprite.draw(posx + map.maptest.getposx(), posy + map.maptest.getposy() );
    }
     
    public void dessiner(Graphics gr)   {
         sprite.draw(posx + map.maptest.getposx(), posy + map.maptest.getposy() );
         draw_poly(gr);
    }
     

    public void update_up() throws SlickException    {

     }

    public void update_down() throws SlickException   {
  
     }

    public void update_left() throws SlickException  {
  
        }

    public void update_right() throws SlickException   {
    
        
    }

    public void update_nothing() throws SlickException   {
	
        int ox = posx;
    	int oy = posy;
    	if ( ifwait == true )   {
            if ( clock.getElapsedTime() > 1000)   {
                ifwait = false;
                randomdes();
            }
    	}
    	else	{
            if ( Math.abs(posx - desx ) > vit)   {
                if(posx > desx )   {
                    posx -= vit;
                }
                else   {
    		posx += vit;
                }
            }

            if ( Math.abs(posy - desy ) > vit)    {
                if(posy > desy )  {
                    posy -= vit;
                }
                else   {
                    posy += vit;
                }
            }
    		
            update_poly();
    		
            if ( isColl()  || ( Math.abs(posx - desx) <= vit && Math.abs(posy - desy) <= vit ))
                {
                posx = ox;
    		posy = oy;
                randomdes();
    		update_poly();
                if ( clock_coll.getElapsedTime() < 1000)
                    {ifwait = false;}
                else{
                    ifwait = true;}
                
                if ( isColl())
                    {clock_coll.Reset();}
    		clock.Reset();
            }
    		
    	}
    	 
        if ( clock_cible.getElapsedTime() > 3000)  {
        cibled = false;
        vit = 2;
        clock_cible.Reset();
        }
        
    }

     
     void randomdes() throws SlickException
     {
    	 boolean founded  = false;
    	 while( !founded)   {
            desx = posx;
            desy=posy;
            int k = r.nextInt(2);
    		 
            if ( k==1)   {
                desx = r.nextInt(map.maptest.getMapWidth());
            }
            else   {
                desy = r.nextInt(map.maptest.getMapHeight());}
                if (!isColl(desx,desy))   {
                       founded = true;
                }	 
		 
    	 }
     clock.Reset();
     }
    	 

   
     void randompos() throws SlickException
     {
    	 
    	boolean founded  = false;
        while( !founded)   {
            int k = r.nextInt(2);
            posx = r.nextInt(map.maptest.getMapWidth());
            posy = r.nextInt(map.maptest.getMapHeight());
            update_poly();
            if (!isColl())
                {founded = true;}	 
		 
    	 }
     }
     

    public boolean isBlocked()   {
    return map.maptest.isStop(midx, midy);
    }
     
     public boolean isCibled()   {

         return cibled;
     }
     

     
    public void update_poly()
    {
        poly = new Polygon(new float[]{
 			posx , posy,
 			posx + width, posy,
 			posx + width, posy + height,
 			posx , posy + height
 		});
    }
     
    public void draw_poly(Graphics gr)   {
        KaniMap tmap = map.maptest;
        for (int i = 0; i < tmap.blocked.size(); i++) {
            Block entity = (Block) tmap.blocked.get(i);
            Polygon polyt = entity.getPoly();
            entity.draw(gr);} 
            gr.draw(poly);
    	 
     }
     
    public Shape get_poly()   {
        return poly;
    }
    

    public boolean isColl() throws SlickException  {
 		
        KaniMap tmap = map.maptest;
 	for (int i = 0; i < tmap.blocked.size(); i++) {
            Block entity1 = (Block) tmap.blocked.get(i);
            if (poly.intersects(entity1.poly)) {
		return true;
            }       
	}       
    return false;
    }
 	
    public boolean isColl(int tx, int ty) throws SlickException {
        return map.maptest.isBlocked(tx, ty);
	}
 	
    public int getdX() {
        return desx;
    }

    public int getdY()  {
        return desy;
    }
 	
    

    public void standing() throws SlickException  {
    
    }

    public void set_des(int hx, int hy) {
       desx = hx;
       desy = hy;
       ifwait = false;
       clock.Reset();
       cibled = true ;
       vit = 4;
       
    }

}
