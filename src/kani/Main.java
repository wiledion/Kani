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

package kani;

import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Classe principale de lancement du jeu
 *
 * @author WILEDION
 */

public class Main

{

    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 800;


    public static void main(String[] args)	throws SlickException, IOException
    {

         AppGameContainer appkani  =	new AppGameContainer(new AppKani());
         appkani.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
         appkani.setVSync(true);
         appkani.setShowFPS(false);
//         appkani.setMaximumLogicUpdateInterval(19);
//         appkani.setMinimumLogicUpdateInterval(21);
         appkani.setTargetFrameRate(30);
         appkani.setIcon("data/images/arc.png");

         appkani.start();
    }


}
