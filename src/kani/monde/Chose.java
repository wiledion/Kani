/**
 * 
 *
 * @author WILEDION
 */

package kani.monde;


abstract public class
      
 Chose {

    int ID ;
    String nom;
    String description ;
    float posx;
    float posy;
    int midx;
    int midy;


    abstract void init();
    abstract void dessiner();
    abstract void dessiner(float X, float Y);
    abstract void setX(float X);
    abstract void setY(float Y);
    abstract float getX();
    abstract float getY();
    abstract void set(float X, float Y);
    abstract void deplacer(float X, float Y);



}
