/*
 * Kani - Code source
 *
 * Par Wiledion
 * 
 */

package kani.monde;

import java.util.ArrayList;
import kani.data.AnimManager;
import kani.data.MapManager;
import kani.utils.Block;
import kani.utils.KaniMap;
import kani.utils.Camera;
import kani.utils.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author WILEDION
 */
public class Heros extends Perso {

    
    public static final int DIR_UP         = 0;
    public static final int DIR_DOWN       = 1;
    public static final int DIR_LEFT       = 2;
    public static final int DIR_RIGHT      = 3;
    
    AnimManager anim = AnimManager.get();
    
    int dir ;
    
    // Animations du Perso

    Animation move ;
    Animation stand;

    MapManager map = MapManager.get();
    
    ArrayList<Fleche> all_arrow;   

    Rectangle bandeh;
    Rectangle bandev;
    Camera view;
    Polygon poly ;
    Timer clock;

     public Heros()
     {super();

      vit = 4;
      pv = 100;
      dir = DIR_DOWN;


      all_arrow = new ArrayList<Fleche>();
      

      
      move = anim.hero_m;
      stand = anim.hero_s;

            sprite = stand;
      
      height = move.getHeight();
      width = move.getWidth();
      posx = 100;
      posy = 100;

      bandeh = new Rectangle(0,100,800,250);
      bandev = new Rectangle(150,0,500, 450);

      midx = width/2;
      midy = height/2;
      
      update_poly();
        
        clock  = new Timer() ;
        clock.Reset();
        
        
     }

     public void setCamera(Camera nview)
    {view = nview; }


    @Override
     public void dessiner()
    {

                     	for (int i = 0; i < all_arrow.size(); i++) {
 		
    	Fleche arrow = all_arrow.get(i);
        if ( arrow.get_dir() == DIR_UP)
        {arrow.draw();}
        

    }
        
         sprite.draw(posx -view.getCamx(), posy -view.getCamy() );
         
             	for (int i = 0; i < all_arrow.size(); i++) {
 		
		
    	Fleche arrow = all_arrow.get(i);
        if ( arrow.get_dir() != DIR_UP)
        {arrow.draw();}

    }
    
    
    }
     
     public void update_up() throws SlickException
    {
        int oy = posy;
        
         if (posy > 0)
         {

                 posy -= vit;
                 sprite = move;  
                 dir = DIR_UP;
                 update_poly();
                 
                 if ( isColl() )
                 {posy += vit;
                 sprite = stand ;
                 update_poly();}
                 
                 if ( oy > posy && view.getCamy() >= 0 && posy < level_height - 200 )
                 { view.iterateCamy(-vit); }
                 
                 
                 }

                 update_all();
         }
                	 



    

     public void update_down() throws SlickException
    {
        int oy = posy ;
        
            if (posy < level_height - height )
            {
         

                posy += vit;
                dir = DIR_DOWN;
                sprite = move;
                update_poly();
    
                if (isColl())
                {
                    posy -= vit;
                    sprite = stand;
                    update_poly();
                }
                    
                    if (oy < posy && view.getCamy() + view.getHeight() < level_height && posy > 200)
                    {   view.iterateCamy(+vit);}
                	
            }
            
            update_all();
     }

        public void update_left() throws SlickException
    {
        int ox = posx;
        
         if (posx > 0)
         {

                 posx -= vit;
                 sprite = move;
                 dir = DIR_LEFT;
                 update_poly();
                 
                 if ( isColl() )
                 {posx += vit;
                 sprite = stand ;
                 update_poly();}
                 
                 if ( ox > posx && view.getCamx() > 0 && posx < level_width - 250)
                 { view.iterateCamx(-vit); }
                 
                 
                 }

         update_all();
         }  

     
     public void update_right() throws SlickException
    {
        int ox = posx ;
        
            if (posx < level_width - width )
            {
         
                posx += vit;
                sprite = move;
                dir = DIR_RIGHT;
                update_poly();
    
                if (isColl())
                {
                    posx -= vit;
                    sprite = stand;
                    update_poly();
                }
                    
                    if (ox < posx && view.getCamx() + view.getWidth() < level_width && posx > 250)
                    {   view.iterateCamx(+vit);}
                	
            }
            
            update_all();
     }

    

     public void update_nothing()

    {sprite = stand;
    update_all();
    }
     
     public void update()
     {
     }
     
          public void update_all()
     {
         
             	for (int i = 0; i < all_arrow.size(); i++) {
 		
    	Fleche arrow = all_arrow.get(i);
    	arrow.update();
        
if (!arrow.in_map(surface))
{ 
    all_arrow.remove(i);
    i--;
}
        
    }


     }
     
     public void update_tir()
     {
     
            if ( clock.getElapsedTime() > 400)
            {
                all_arrow.add(new Fleche(dir, posx + width/2, posy + height/2));
                
                switch(dir)
                {
                    case  DIR_UP :
                            sprite = anim.hero_au;
                        break ;
                    case  DIR_DOWN :
                            sprite = anim.hero_ad;
                        break ;
                    case  DIR_LEFT :
                            sprite = anim.hero_al;
                        break ;
                    case  DIR_RIGHT :
                            sprite = anim.hero_ar;
                        break ;
                
                
                
                
                
                }
                    
            clock.Reset();
            }
     
     
     }
     

     public boolean isBlocked()
    {
     return map.maptest.isStop(midx, midy);

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
     
     public Polygon get_poly()
     {
         return poly ;
     }
     

     
     public void draw_poly(Graphics gr)
     {
    	 KaniMap tmap = map.maptest;
    		
 /*	for (int i = 0; i < tmap.blocked.size(); i++) {
  			Block entity = (Block) tmap.blocked.get(i);
  		
  			Polygon polyt = entity.getPoly();
  		
  			
  		entity.draw(gr);} */
	 
 		gr.draw(poly);
                
                
                             	for (int i = 0; i < all_arrow.size(); i++) {
 		
		
    Fleche arrow = all_arrow.get(i);
    arrow.draw_poly(gr);

    }
                           
                                
         gr.setColor(Color.red);
    	 gr.draw(surface);

         
     }
    
     
     public void draw_inf(Graphics gr )
     {
     
         gr.setColor(Color.white);
         gr.drawString("PV : " + pv + "/100" , 150, 480);
         gr.setColor(Color.green);
         gr.drawRect(150,500,100,20);
	 gr.fillRect(150,500,pv,20);


     }
     

 	public boolean isColl() throws SlickException {
 		
 		KaniMap tmap = map.maptest;
 		
		for (int i = 0; i < tmap.blocked.size(); i++) {
			Block entity1 = (Block) tmap.blocked.get(i);
			if (poly.intersects(entity1.poly)) {
				return true;
			}       
		}       
		return false;
	}

            public void addTo_pv(int n)
            {pv += n;}
            
            public int get_pv()
            {return pv;}
            
            public ArrayList<Fleche> get_all_arrow()
            {
            return all_arrow;
            }
            
            public void remove_arrow(int i)
                    
            {
            all_arrow.remove(i);
            
            }


     }

