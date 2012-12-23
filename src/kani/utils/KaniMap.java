/**
 * 
 *
 * @author WILEDION
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
    float posx;
    float posy;
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

    public float getposx()
    {return posx;}

    public float getposy()
    {return posy;}
    
    public Polygon get_surface()
    {return surface; }        
    
    public void render(Camera view)   {
    render( (int)- view.getCamx(), (int)-view.getCamy());
    }

    public void render(Camera view, int layer)   {
    render((int) - view.getCamx(), (int)-view.getCamy(), layer);
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
