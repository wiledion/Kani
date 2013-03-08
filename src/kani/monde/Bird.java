/**
 *
 *
 * @author WILEDION
 */
package kani.monde;

import java.util.ArrayList;
import java.util.Random;
import kani.data.AnimManager;
import kani.data.ImgManager;
import kani.data.MapManager;
import kani.utils.Block;
import kani.utils.KaniMap;
import kani.utils.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Bird extends Monst {

    class Dest {

        public float x, y;
        int nb;

        Dest(float nx, float ny) {
            x = nx;
            y = ny;
        }
    }
    AnimManager anim = AnimManager.get();
    Animation move_up;
    Animation move_down;
    Animation move_left;
    Animation move_right;
    Animation stand_up;
    Animation stand_down;
    Animation stand_left;
    Animation stand_right;
    Animation stand;
    ImgManager img = ImgManager.get();
    Image isprite;
    MapManager map = MapManager.get();
    ArrayList<Dest> all_dest;
    float endx, endy;
    Polygon poly;
    Timer clock_cible;
    float desx;
    float desy;
    float vel;
    float velx;
    float vely;
    double dir;
    Random r;
    float p0[];
    float p1[];
    float p2[];
    float p3[];
    boolean cibled;

    public Bird() throws SlickException {
        super();
        r = new Random();
        vel = (float) (6. / 30);
        pv = 30;
        dir = 0;
        layer = 2;
        isprite = img.birdr;
        all_dest = new ArrayList();
        height = isprite.getHeight();
        width = isprite.getWidth();
        poly = new Polygon(new float[]{
            posx, posy,
            posx + width, posy,
            posx + width, posy + height,
            posx, posy + height
        });
        p0 = new float[2];
        p1 = new float[2];
        p2 = new float[2];
        p3 = new float[2];

        randompos();

        clock_cible = new Timer();
        clock_cible.Reset();
        cibled = false;
        randomdes();

        midx = width / 2;
        midy = height / 2;

    }

    public void dessiner(Graphics gr) {
        isprite.setRotation((float) ((dir * 180) / Math.PI));
        isprite.draw(posx + map.current.getposx(), posy + map.current.getposy());
    }

    public void update_nothing(float looptime) throws SlickException {

        float parcours = vel * looptime;
        float dist_step = (float) Math.hypot(desx - posx, desy - posy);

        while (dist_step < parcours) {
            if (all_dest.size() == 0) {
                randomdes();
            }

            posx = desx;
            posy = desy;
            Dest d = all_dest.get(0);
            desx = d.x;
            desy = d.y;
            all_dest.remove(0);
            parcours -= dist_step;
            dist_step = (float) Math.hypot(desx - posx, desy - posy);
            update_dir();
        }

        if (all_dest.size() == 0) {
            randomdes();
        }

        posx += parcours * Math.cos(dir);
        posy += parcours * Math.sin(dir);
        update_poly();
        update_dir();

        if (cibled == true) {
            cibled = false;
        }

    }

    public void update_hit() {
        pv -= 10;
        if (pv <= 0) {
            is_dead = true;
        }
    }

    public boolean is_dead() {

        return is_dead;
    }

    void randomdes() throws SlickException {

        float nx, ny;

        nx = r.nextInt(map.current.getMapWidth());
        ny = r.nextInt(map.current.getMapHeight());

        double a = Math.hypot(nx - posx, ny - posy) / 2;
        double dir_end = (Math.atan2(ny - posy, nx - posx));
        double vert_dep = a / Math.cos(dir_end);

        p0[0] = posx;
        p0[1] = posy;
        p1[0] = (float) (posx + a * Math.cos(dir));
        p1[1] = (float) (posy + a * Math.sin(dir));
        double sdir = -Math.PI / 2 - dir_end - dir;
        p2[0] = (float) (nx + a * Math.cos(sdir));
        p2[1] = (float) (ny + a * Math.sin(sdir));
        p3[0] = nx;
        p3[1] = ny;

        endx = nx;
        endy = ny;

        path_update();
        update_dir();
    }

    void set_des(float nx, float ny) throws SlickException {


        double a = Math.hypot(nx - posx, ny - posy) / 2;
        double dir_end = (Math.atan2(ny - posy, nx - posx));
        double vert_dep = a / Math.cos(dir_end);

        p0[0] = posx;
        p0[1] = posy;
        p1[0] = (float) (posx + a * Math.cos(dir));
        p1[1] = (float) (posy + a * Math.sin(dir));
        double sdir = -Math.PI / 2 - dir_end - dir;
        p2[0] = (float) (nx + a * Math.cos(sdir));
        p2[1] = (float) (ny + a * Math.sin(sdir));
        p3[0] = nx;
        p3[1] = ny;

        endx = nx;
        endy = ny;

        path_update();
        update_dir();
    }

    void randompos() throws SlickException {

        int k = r.nextInt(2);
        posx = r.nextInt(map.current.getMapWidth());
        posy = r.nextInt(map.current.getMapHeight());
        update_poly();

    }

    double bezier_length() {
        double t;
        int i;
        int steps = 10;
        float[] dot = {0, 0};
        float[] previous_dot = {0, 0};
        double length = 0.0;
        for (i = 0; i <= steps; i++) {
            t = (double) i / (double) steps;
            dot[0] = (float) (p0[0] * Math.pow(1 - t, 3) + 3 * p1[0] * t * (1 - t) * (1 - t) + 3 * p2[0] * t * t * (1 - t) + p3[0] * t * t * t);
            dot[1] = (float) (p0[1] * Math.pow(1 - t, 3) + 3 * p1[1] * t * (t - 1) * (t - 1) + 3 * p2[1] * t * t * (t - 1) + p3[1] * t * t * t);

            if (i > 0) {
                double x_diff = dot[0] - previous_dot[0];
                double y_diff = dot[1] - previous_dot[1];
                length += Math.sqrt(x_diff * x_diff + y_diff * y_diff);
            }
            previous_dot = dot;
        }

        return length;
    }

    void path_update() {

        all_dest = new ArrayList();
        float step = 50;

        for (int i = 1; i <= step; i++) {

            float t = i / step;
            float px = (float) (p0[0] * Math.pow(1 - t, 3) + 3 * p1[0] * t * (1 - t) * (1 - t) + 3 * p2[0] * t * t * (1 - t) + p3[0] * t * t * t);
            float py = (float) (p0[1] * Math.pow(1 - t, 3) + 3 * p1[1] * t * (1 - t) * (1 - t) + 3 * p2[1] * t * t * (1 - t) + p3[1] * t * t * t);
            all_dest.add(new Dest(px, py));

        }

        Dest dest = all_dest.get(0);
        desx = dest.x;
        desy = dest.y;
        update_dir();

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

    public void draw_poly(Graphics gr) {
        KaniMap tmap = map.current;
        for (int i = 0; i < tmap.blocked.size(); i++) {
            Block entity = (Block) tmap.blocked.get(i);
            entity.draw(gr);
        }
        gr.draw(poly);
    }

    public Shape get_poly() {
        return poly;
    }

    public float getdX() {
        return desx;
    }

    public float getdY() {
        return desy;
    }

    public double dist_rest() {
        return Math.hypot(desx - posx, desy - posy);
    }

    public void update_dir() {
        dir = (Math.atan2(desy - posy, desx - posx));
    }

    public void draw_path(Graphics gr) {

        if (all_dest.size() > 0) {

            for (int i = 0; i < all_dest.size() - 1; i++) {

                Dest d = all_dest.get(i);
                Dest dd = all_dest.get(i + 1);
                gr.drawLine(d.x, d.y, dd.x, dd.y);

            }
        }
    }

    @Override
    public boolean isCibled() {
        return cibled;
    }

    @Override
    public void set_cibled(float hx, float hy) throws SlickException {

        if (clock_cible.getElapsedTime() > 10000) {
            int lancer = r.nextInt(3);

            if (lancer == 1) {
                set_des(hx, hy);
                update_dir();
                clock_cible.Reset();
                cibled = true;
            }
        }

    }
}
