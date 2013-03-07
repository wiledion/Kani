/**
 *
 *
 * @author WILEDION
 */
package kani.monde;

import java.util.Random;
import kani.data.AnimManager;
import kani.data.MapManager;
import kani.utils.Block;
import kani.utils.KaniMap;
import kani.utils.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Statue extends Perso {

    AnimManager anim = AnimManager.get();
    Animation move;
    Animation stand;
    MapManager map = MapManager.get();
    Polygon poly;
    Timer clock;
    Timer clock_coll;
    Timer clock_cible;
    float desx;
    float desy;
    float vel;
    float velx;
    float vely;
    double dir;
    Random r;
    boolean ifwait;
    boolean cibled;

    public Statue() throws SlickException {
        super();
        r = new Random();
        vel = (float) (2. / 30);
        pv=20 ;
        move = anim.stat;
        stand = anim.stat;
        sprite = stand;
        height = move.getHeight();
        width = move.getWidth();
        poly = new Polygon(new float[]{
                    posx, posy,
                    posx + width, posy,
                    posx + width, posy + height,
                    posx, posy + height
                });

        randompos();

        clock = new Timer();
        clock_coll = new Timer();
        clock_cible = new Timer();
        clock_cible.Reset();
        ifwait = false;
        cibled = false;
        randomdes();

        midx = width / 2;
        midy = height / 2;

    }

    public void dessiner() {
        sprite.draw(posx + map.current.getposx(), posy + map.current.getposy());
    }

    public void dessiner(Graphics gr) {
        sprite.draw(posx + map.current.getposx(), posy + map.current.getposy());

    }

    public void update_up() throws SlickException {
    }

    public void update_down() throws SlickException {
    }

    public void update_left() throws SlickException {
    }

    public void update_right() throws SlickException {
    }

    public void update_nothing(float looptime) throws SlickException {

        float ox = posx;
        float oy = posy;

        velx = (float) (vel * Math.cos(dir));
        vely = (float) (vel * Math.sin(dir));

        dx = velx * looptime;
        dy = vely * looptime;


        if (ifwait == true) {
            if (clock.getElapsedTime() > 1000) {
                ifwait = false;
                randomdes();
            }
        } else {
            posx += velx * looptime;
            posy += vely * looptime;

            update_poly();

            if (isColl() || dist_rest() <= vel * looptime) {
                posx = ox;
                posy = oy;
                randomdes();
                update_poly();
                if (clock_coll.getElapsedTime() > 1000) {
                    ifwait = false;
                }


                if (isColl()) {
                    clock_coll.Reset();
                }
                clock.Reset();
            }

        }

        if (cibled && clock_cible.getElapsedTime() > 3000) {
            cibled = false;
            vel = (float) (2. / 30);
        }

    }
    
    public void update_hit(){
    
    pv-=10;
    if (pv <= 0) {
        is_dead = true ;
    }
        
    
    }
    
    public boolean is_dead(){
        
        return is_dead;
    }
    

    void randomdes() throws SlickException {
        boolean founded = false;
        while (!founded) {
            desx = posx;
            desy = posy;
            int k = r.nextInt(2);

            if (k == 1) {
                desx = r.nextInt(map.current.getMapWidth());
            } else {
                desy = r.nextInt(map.current.getMapHeight());
            }
            if (!isColl(desx, desy)) {
                founded = true;
            }

        }

        dir = (Math.atan2(desy - posy, desx - posx));
        clock.Reset();
    }

    void randompos() throws SlickException {

        boolean founded = false;
        while (!founded) {
            int k = r.nextInt(2);
            posx = r.nextInt(map.current.getMapWidth());
            posy = r.nextInt(map.current.getMapHeight());
            update_poly();
            if (!isColl()) {
                founded = true;
            }

        }
    }

    public boolean isBlocked() {
        return map.current.isStop(midx, midy);
    }

    public boolean isCibled() {

        return cibled;
    }

    public void update_poly() {
        poly = new Polygon(new float[]{
                    posx, posy,
                    posx + width, posy,
                    posx + width, posy + height,
                    posx, posy + height
                });
    }

    public void draw_poly(Graphics gr) {
        KaniMap tmap = map.current;
        for (int i = 0; i < tmap.blocked.size(); i++) {
            Block entity = (Block) tmap.blocked.get(i);
            Polygon polyt = entity.getPoly();
            entity.draw(gr);
        }
        gr.draw(poly);

    }

    public Shape get_poly() {
        return poly;
    }

    public boolean isColl() throws SlickException {

        KaniMap tmap = map.current;
        for (int i = 0; i < tmap.blocked.size(); i++) {
            Block entity1 = (Block) tmap.blocked.get(i);
            if (poly.intersects(entity1.poly)) {
                return true;
            }
        }
        return false;
    }

    public boolean isColl(float tx, float ty) throws SlickException {
        return map.current.isBlocked(tx, ty);
    }

    public float getdX() {
        return desx;
    }

    public float getdY() {
        return desy;
    }

    public void standing() throws SlickException {
    }

    public void set_cibled(float hx, float hy) {
        desx = hx;
        desy = hy;
        update_dir();
        ifwait = false;
        clock_cible.Reset();
        cibled = true;
        vel = (float) (4. / 30);

    }

    public double dist_rest() {
        return Math.hypot(desx - posx, desy - posy);
    }
    
    public void update_dir() {
        dir = (Math.atan2(desy - posy, desx - posx));
    }
    
}
