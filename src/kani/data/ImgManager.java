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

package kani.data;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class ImgManager {

    private static ImgManager imgmanager = new ImgManager();

    private ImgManager ()
    {super();}

    public static ImgManager get()
    {return imgmanager; }


            // Images

    public Image intro_splash1;
    public Image intro_splash2;
    public Image intro_splash3;
    public Image logo;

    public Image menu_menu;
    public Image menu_jeu1;
    public Image menu_jeu2;
    public Image menu_quit1;
    public Image menu_quit2;

    public Image hero_w1;
    public Image hero_w2;
    public Image hero_w3;
    public Image hero_s;
    public Image hero_au1;
    public Image hero_au2;
    public Image hero_al1;
    public Image hero_al2;
    public Image hero_ad1;
    public Image hero_ad2;
    public Image hero_ar1;
    public Image hero_ar2;
    public Image perso1;
    public Image perso2;
    
    public Image arrow_u;
    public Image arrow_d;
    public Image arrow_l;
    public Image arrow_r;
    
    public Image statue;

    public Image tablo;
   
 public void load() throws SlickException
    {

    intro_splash1 = new Image("data/images/java.jpg");
    intro_splash2 = new Image("data/images/slick.png");
    intro_splash3 = new Image ("data/images/wiledion.png");
    logo = new Image ("data/images/minilogo.png");

    menu_menu = new Image("data/images/menu.png");
    menu_jeu1 = new Image("data/images/bttjeu1.png");
    menu_jeu2 = new Image("data/images/bttjeu2.png");
    menu_quit1 = new Image("data/images/bttquit1.png");
    menu_quit2 = new Image("data/images/bttquit2.png");

    hero_w1 = new Image("data/images/perso/hero/herom1.png");
    hero_w2 = new Image("data/images/perso/hero/herom2.png");
    hero_w3 = new Image("data/images/perso/hero/herom3.png");
    hero_au1 = new Image("data/images/perso/hero/heroah1.png");
    hero_au2 = new Image("data/images/perso/hero/heroah2.png");
    hero_ad1 = new Image("data/images/perso/hero/heroab1.png");
    hero_ad2 = new Image("data/images/perso/hero/heroab2.png");
    hero_al1 = new Image("data/images/perso/hero/heroag1.png");
    hero_al2 = new Image("data/images/perso/hero/heroag2.png");
    hero_ar1 = new Image("data/images/perso/hero/heroad1.png");
    hero_ar2 = new Image("data/images/perso/hero/heroad2.png");
    hero_s = new Image("data/images/perso/hero/heros.png");
    
    arrow_u = new Image("data/images/obj/arrow_u.png");
    arrow_d = new Image("data/images/obj/arrow_d.png");
    arrow_l = new Image("data/images/obj/arrow_l.png");
    arrow_r = new Image("data/images/obj/arrow_r.png");
    
    statue = new Image("data/images/perso/hero/stat.png");

    tablo = new Image("data/images/tablo.png");

    }

}


