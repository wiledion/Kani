/**
 * 
 *
 * @author WILEDION
 */

package kani.data;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;


public class OthManager {

    private static OthManager othmanager = new OthManager();

    private OthManager ()
    {super();}

    public static OthManager get()
    {return othmanager; }

    OthManager img = OthManager.get();

 public void load() throws SlickException
    {


    }

}
