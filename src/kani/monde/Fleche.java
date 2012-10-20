/**
 * 
 *
 * @author WILEDION
 */


package kani.monde;

import kani.data.ImgManager;
import kani.data.MapManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

public class Fleche extends Objet {

    Image isprite;
    int vit;
    Polygon poly;

    public static final int DIR_UP         = 0;
    public static final int DIR_DOWN       = 1;
    public static final int DIR_LEFT       = 2;
    public static final int DIR_RIGHT      = 3;

    MapManager map = MapManager.get();
    ImgManager img = ImgManager.get();
    int dir;
        
        
    Fleche(int ndir, int nx, int ny)   {
	
        super();
        vit = 8;
        dir = ndir;
        switch(dir)
        {
            case DIR_UP :
                isprite = img.arrow_u;
                break;
            case DIR_DOWN :
                isprite = img.arrow_d;
                break;
            case DIR_LEFT :
                isprite = img.arrow_l;
                break;
            case DIR_RIGHT :
                isprite = img.arrow_r;
                break;
        }
        posx = nx;
        posy = ny;
        update_poly();
    }

        
    public void draw()  {
    isprite.draw(posx + map.maptest.getposx(), posy + map.maptest.getposy());
    }   
        
    void update()   {

        switch(dir)
        {
            case DIR_UP :
                posy -= vit ;
                break;
            case DIR_DOWN :
                posy += vit ;
                break;
            case DIR_LEFT :
                posx -= vit ;
                break;
            case DIR_RIGHT :
                posx += vit ;
                break;
        }
        update_poly();
        
    }
    
    public int get_dir()  {
    
        return dir;
    }
        
    public boolean in_map(Polygon surface)  {

        return !surface.intersects(poly); 
    }

    private void update_poly()  {
        
        int width = isprite.getWidth();
        int height = isprite.getHeight();
        poly = new Polygon(new float[]{
			posx , posy,
			posx + width, posy,
			posx + width, posy + height,
			posx , posy + height
		});
    }

    public void draw_poly(Graphics gr)   {
     
        gr.draw(poly);
    	 
     }
     
     public Polygon get_poly()  {
        return poly; 
     }
     

}
