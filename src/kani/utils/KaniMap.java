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

package kani.utils;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import org.newdawn.slick.geom.Polygon;

public class KaniMap extends TiledMap implements TileBasedMap {

    private int size ;
    int nbTileWidth, nbTileHeight, nbLayer;
    int mapWidth;
    int  mapHeight;
    int posx;
    int posy;
    Polygon surface;
    private int square[] = {1,1,15,1,15,15,1,15}; 
    public static ArrayList<Object> aqua;
    public static ArrayList<Object> blocked;

    public KaniMap(String string) throws SlickException {

        super(string);
        blocked = new ArrayList<Object>();
	aqua = new ArrayList<Object>();
	mapWidth = getWidth() * getTileWidth();
	mapHeight = getHeight() * getTileHeight();
	nbLayer = getLayerCount();
		
	for ( int k = 0; k < nbLayer;k++)   {
            for (int x = 0; x < getWidth(); x++)   {
		for (int y = 0; y < getHeight(); y++)   {
                    int tileID = getTileId(x, y, k);
                    String value = getTileProperty(tileID, "blocked", "false");
                    if ("true".equals(value))   {
                        blocked.add( new Block(x * 16, y * 16, square, "square")  );
                    }
                    value = getTileProperty(tileID, "aqua", "false");
                    if ("true".equals(value))   {
                        aqua.add( new Block(x * 16, y * 16, square, "square")  );
                    }				
                }
            }
	}
                
	
        size = getTileHeight();
        surface = new Polygon(new float[]{
		2 , 2,
		mapWidth -2 , 2,
		mapWidth - 2, mapHeight - 2,
		2 ,mapHeight - 2
		});
        blocked.addAll(aqua);
        posx=0;
        posy=0;
    }
 
    public boolean isBlocked(float x, float y)   {

	for (int i = 0; i < blocked.size(); i++) {
            Block entity1 = (Block) blocked.get(i);
            if (entity1.poly.contains(x,y)) {
		return true;
            }       
	}
        
        for (int i = 0; i < aqua.size(); i++) {
            Block entity1 = (Block) aqua.get(i);
		if (entity1.poly.contains(x,y)) {
                    return true;
		}       
	}       
        
	return false;
}

    public boolean isAqua(float x, float y)   {

	for (int i = 0; i < aqua.size(); i++) {
            Block entity1 = (Block) aqua.get(i);
            if (entity1.poly.contains(x,y)) {
		return true;
            }       
	}       
	return false;
    }

    public boolean isStop(float x, float y)
    {
        if ((isBlocked(x,y) || isAqua(x,y)))
            {return true;}
        else
            return false;
}   

    @Override
    public int getWidthInTiles() {

        return tileWidth;
    }

    @Override
    public int getHeightInTiles() {
            return tileHeight;

    }

    @Override
    public void pathFinderVisited(int x, int y) {

    }

    @Override
    public boolean blocked(PathFindingContext context, int tx, int ty) {

        if (isBlocked(tx*30, ty*30))
        { return true;}

    return false;

    }

    @Override
    public float getCost(PathFindingContext context, int tx, int ty) {
    
        return 1;
    }

    public void setposx(int newx)
    {posx = newx;}

    public void setposy(int newy)
    {posy = newy; }

    public int getposx()
    {return posx;}

    public int getposy()
    {return posy;}
    
    public Polygon get_surface()
    {return surface; }        
    
    public void render(Camera view)   {
    render(- view.getCamx(), -view.getCamy());
    }

    public void render(Camera view, int layer)   {
    render(- view.getCamx(), -view.getCamy(), layer);
    }
    
    public void iterateposx(int newx)   {
        posx += newx;}

    public void iterateposy(int newy)   {
        posy += newy;}

    public void update(Camera view) {
        posx = - view.getCamx();
        posy = - view.getCamy();
    }

    public int getMapHeight()   {
        return tileHeight * height ;
    }

    public int getMapWidth()   {
        return tileWidth * width ;
    }

}
