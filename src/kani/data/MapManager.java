/**
 * 
 *
 * @author WILEDION
 */

package kani.data;

import kani.utils.KaniMap;
import org.newdawn.slick.SlickException;


public class MapManager {

    private static MapManager mapmanager = new MapManager();
    public KaniMap maptest;


    private MapManager (){
        super();
    }

    public static MapManager get() {
        return mapmanager; 
    }


    public void load() throws SlickException  {
        maptest = new KaniMap("data/map/demo2.tmx");
    }

    public class view {

    }

}

