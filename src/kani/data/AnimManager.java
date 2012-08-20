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
