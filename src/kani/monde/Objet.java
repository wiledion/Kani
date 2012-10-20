/**
 * 
 *
 * @author WILEDION
 */


package kani.monde;

import org.newdawn.slick.Animation;


public class Objet extends Chose {
    
    Animation sprite;
    int nbr_objet;

public Objet()     { 
    sprite = new Animation();
    posx = 0;
    posy = 0;
}

void init()   {
}

void dessiner()   {
}

void dessiner(float X, float Y)   {
}
 void setX(float X)   {
}
 void setY(float Y)   {
}
 void set(float X, float Y)   {
}
 void deplacer(float X, float Y)   {
}

 int getX()   {
return posx;
 }

 int getY()   {
return posy;
 }


}
