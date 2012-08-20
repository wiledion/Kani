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

import kani.Main;
import org.newdawn.slick.geom.Rectangle;

public class Camera {

    int camx;
    int camy;
    int height_window;
    int width_window;

    public Camera()   {
        camx = 0;
        camy = 0;
        height_window = Main.SCREEN_HEIGHT - 150;
        width_window = Main.SCREEN_WIDTH ;

    }

    public int getCamx()  {
    return camx;
    }

    public int getCamy()  {
    return camy;
    }

    public void iterateCamx(int newx)  {
    camx += newx;
    }

    public void iterateCamy(int newy)  {
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

