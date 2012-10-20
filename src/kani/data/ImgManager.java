/**
 * 
 *
 * @author WILEDION
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


