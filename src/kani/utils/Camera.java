/**
 * 
 *
 * @author WILEDION
 */

package kani.utils;

import kani.Main;
import org.newdawn.slick.geom.Rectangle;

public class Camera {

    float camx;
    float camy;
    int height_window;
    int width_window;

    public Camera()   {
        camx = 0;
        camy = 0;
        height_window = Main.SCREEN_HEIGHT - 150;
        width_window = Main.SCREEN_WIDTH ;

    }

    public float getCamx()  {
    return camx;
    }

    public float getCamy()  {
    return camy;
    }

    public void iterateCamx(float newx)  {
    camx += newx;
    }

    public void iterateCamy(float newy)  {
    camy += newy;
    }

    public void setCamx(int newx)  {
        camx = newx;
    }

    public void setCamy(int newy)  {
        camy = newy;
    }
    
    public void setCam(int newx, int newy)  {
        camx = newx ;
        camy = newy;
        }

    public int getHeight()  {
        return height_window;
    }

    public int getWidth()  {
        return width_window;
    }

}

