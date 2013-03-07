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
    public KaniMap current;
    
    
    private MapManager (){
        super();
    }

    public static MapManager get() {
        return mapmanager; 
    }


    public void load() throws SlickException  {
    

        
     //   level3 = new KaniMap("data/map/level3.tmx");
      //  level4 = new KaniMap("data/map/level4.tmx");
    }

    public class view {

    }
    

            
        
    
    

    
    public void setMap(String path_map) throws SlickException{

        current = new KaniMap(path_map);
        }
    
}

