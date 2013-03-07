/**
 *
 *
 * @author WILEDION
 */
package kani.monde;

import java.util.ArrayList;
import kani.data.AnimManager;
import kani.data.MapManager;
import kani.utils.Block;
import kani.utils.KaniMap;
import kani.utils.Camera;
import kani.utils.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author WILEDION
 */
public class Heros extends Perso {

    public static final double DIR_UP = Math.PI / 2;
    public static final double DIR_DOWN = -Math.PI / 2;
    public static final double DIR_LEFT = -Math.PI;
    public static final double DIR_RIGHT = 0;
    public static final double DIR_UPR = Math.PI / 4;
    public static final double DIR_UPL = 3 * Math.PI / 4;
    public static final double DIR_DOWNL = -3 * Math.PI / 4;
    public static final double DIR_DOWNR = -Math.PI / 4;
    AnimManager anim = AnimManager.get();
    double dir;
    float vel;
    float velx;
    float vely;
    // Animations du Perso
    Animation move;
    Animation stand;
    MapManager map = MapManager.get();
    ArrayList<Fleche> all_arrow;
    Rectangle bandeh;
    Rectangle bandev;
    Camera view;
    Polygon poly;
    Timer clock;

    public Heros() {
        super();

        vel = (float) (4. / 30);
        pv = 100;
        dir = DIR_DOWN;


        all_arrow = new ArrayList<Fleche>();


        move = anim.hero_m;
        stand = anim.hero_s;

        sprite = stand;

        height = move.getHeight();
        width = move.getWidth();
        posx = 100;
        posy = 80;

        bandeh = new Rectangle(0, 100, 800, 250);
        bandev = new Rectangle(150, 0, 500, 450);

        midx = width / 2;
        midy = height / 2;

        update_poly();

        clock = new Timer();
        clock.Reset();


    }

    public void setCamera(Camera nview) {
        view = nview;
    }

    @Override
    public void dessiner() {

        for (int i = 0; i < all_arrow.size(); i++) {

            Fleche arrow = all_arrow.get(i);
            if (arrow.get_dir() == DIR_UP) {
                arrow.draw();
            }


        }

        sprite.draw(posx - view.getCamx(), posy - view.getCamy());

        for (int i = 0; i < all_arrow.size(); i++) {


            Fleche arrow = all_arrow.get(i);
            if (arrow.get_dir() != DIR_UP) {
                arrow.draw();
            }

        }


    }

    public void update_nothing(float timeloop) {
        sprite = stand;
        update_all(timeloop);
    }

    public void set_stand() {
        sprite = stand;

    }

    public void update_move(float timeloop) throws SlickException {

        float ox = posx;
        float oy = posy;
        velx = (float) (vel * Math.cos(dir));
        vely = -(float) (vel * Math.sin(dir));

        posx += velx * timeloop;
        posy += vely * timeloop;

        sprite = move;
        update_poly();

        if (isColl() || !inMap()) {
            posx = ox;
            posy = oy;
            sprite = stand;
            update_poly();
        }

        // Déplacement de la camera en fonction du deplacement

        if (oy > posy && view.getCamy() >= 0 && posy < level_height - 200) {
            view.iterateCamy(vely * timeloop);
        }

        if (oy < posy && view.getCamy() + view.getHeight() < level_height && posy > 200) {
            view.iterateCamy(+vely * timeloop);
        }

        if (ox > posx && view.getCamx() > 0 && posx < level_width - 250) {
            view.iterateCamx(velx * timeloop);
        }

        if (ox < posx && view.getCamx() + view.getWidth() < level_width && posx > 250) {
            view.iterateCamx(+velx * timeloop);
        }

        update_all(timeloop);
    }

    public void update_all(float looptime) {

        for (int i = 0; i < all_arrow.size(); i++) {

            Fleche arrow = all_arrow.get(i);
            arrow.update(looptime);
            if (!arrow.in_map(surface)) {
                all_arrow.remove(i);
                i--;
            }
        }
    }

    public void update_tir() {

        if (clock.getElapsedTime() > 300) {
            all_arrow.add(new Fleche(dir, posx + width / 2, posy + height / 2));

            if (dir == DIR_UP) {
                sprite = anim.hero_au;
            } else if (dir == DIR_DOWN) {
                sprite = anim.hero_ad;
            } else if (dir == DIR_LEFT) {
                sprite = anim.hero_al;
            } else if (dir == DIR_RIGHT) {
                sprite = anim.hero_ar;
            }

            clock.Reset();
        }


    }

    public void add_tir(double dir, float decx, float decy) {
        all_arrow.add(new Fleche(dir, posx + width / 2 + decx, posy + height / 2 + decy));


    }

    public boolean isBlocked() {
        return map.current.isStop(midx, midy);

    }

    public void update_poly() {
        poly = new Polygon(new float[]{
            posx, posy,
            posx + width, posy,
            posx + width, posy + height,
            posx, posy + height
        });



    }

    public Polygon get_poly() {
        return poly;
    }

    public void draw_poly(Graphics gr) {
        /*   KaniMap tmap = map.current;

         for (int i = 0; i < tmap.blocked.size(); i++) {
         Block entity = (Block) tmap.blocked.get(i);
  		
         Polygon polyt = entity.getPoly();
  		
  			
         entity.draw(gr);} 

         gr.draw(poly);*/


        for (int i = 0; i < all_arrow.size(); i++) {


            Fleche arrow = all_arrow.get(i);
            arrow.draw_poly(gr);

        }


        gr.setColor(Color.red);
        gr.draw(surface);


    }

    public void draw_inf(Graphics gr) {

        gr.setColor(Color.white);
        gr.drawString("PV : " + pv + "/100", 150, 452);
        gr.setColor(Color.green);
        gr.drawRect(150, 472, 100, 20);
        gr.fillRect(150, 472, pv, 20);


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

    public boolean inMap() {
        if (posx + width > level_width || posx < 0 || posy + height > level_height || posy < 0) {
            return false;
        }

        return true;


    }

    public void addTo_pv(int n) {
        pv += n;
    }

    public int get_pv() {
        return pv;
    }

    public ArrayList<Fleche> get_all_arrow() {
        return all_arrow;
    }

    public void remove_arrow(int i) {
        all_arrow.remove(i);

    }

    public void setDir(int vx, int vy) {

        if (vx == 1 && vy == 1) {
            dir = DIR_DOWNR;
        } else if (vx == 1 && vy == -1) {
            dir = DIR_UPR;
        } else if (vx == -1 && vy == -1) {
            dir = DIR_UPL;
        } else if (vx == -1 && vy == 1) {
            dir = DIR_DOWNL;
        } else if (vx == 1) {
            dir = DIR_RIGHT;
        } else if (vx == -1) {
            dir = DIR_LEFT;
        } else if (vy == 1) {
            dir = DIR_DOWN;
        } else if (vy == -1) {
            dir = DIR_UP;
        }
    }
}
