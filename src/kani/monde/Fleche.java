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
