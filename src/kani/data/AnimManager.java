/**
 * 
 *
 * @author WILEDION
 */

package kani.data;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;


public class AnimManager {

    private static AnimManager animmanager = new AnimManager();

    private AnimManager ()
    {super();}

    public static AnimManager get()
    {return animmanager; }

    ImgManager img = ImgManager.get();

    public Animation hero_m;
    public Animation hero_s;
    public Animation hero_au;
    public Animation hero_ad;
    public Animation hero_al;
    public Animation hero_ar;
    public Animation stat;

 public void load() throws SlickException
    {

     hero_m = new Animation();
     hero_s = new Animation();
     stat = new Animation();
     
     hero_au = new Animation();
     hero_ad = new Animation();
     hero_al = new Animation();
     hero_ar = new Animation();

     hero_m.addFrame(img.hero_w1, 200);
     hero_m.addFrame(img.hero_w2, 200);
     hero_m.addFrame(img.hero_w3, 200);

     hero_au.addFrame(img.hero_au1, 200);
     hero_au.addFrame(img.hero_au2, 200);
     hero_ad.addFrame(img.hero_ad1, 200);
     hero_ad.addFrame(img.hero_ad1, 200);
     hero_al.addFrame(img.hero_al1, 200);
     hero_al.addFrame(img.hero_al1, 200);
     hero_ar.addFrame(img.hero_ar1, 200);
     hero_ar.addFrame(img.hero_ar1, 200);
     
     hero_s.addFrame(img.hero_s, 200);
     
     stat.addFrame(img.statue, 200);

    }

}
