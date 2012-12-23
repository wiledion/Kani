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
import org.python.util.PythonInterpreter;

/**
 * Classe personnage de jeu
 */
public class PersoScript extends Chose {

    AnimManager anim = AnimManager.get();
    MapManager map = MapManager.get();
    Animation sprite;
    int pv;
    int mana;
    float dx;
    float dy;
    float desx;
    float desy;
    float vel;
    float velx;
    float vely;
    int height;
    int width;
    int level_width;
    int level_height;
    int mode;
    Polygon surface;
    Polygon poly;
    Random r = new Random();
    PythonInterpreter interp = new PythonInterpreter();
    double dir;
    boolean ifwait;
    boolean cibled;

    public PersoScript(String name) {
        sprite = new Animation();
        r = new Random();


        interp.execfile(name + ".py");
        interp.set("pymonst", this);
        /*Call Lua create function.*/
        interp.exec("create(pymonst)");

    }

    public PersoScript() {
        sprite = new Animation();

    }

    public void setCibledTrue() {
        cibled = true;
    }

    public void setCibledFalse() {
        cibled = false;
    }

    public void init() {
        sprite = new Animation();
    }

    public void dessiner() {
        sprite.draw(posx + map.maptest.getposx(), posy + map.maptest.getposy());
    }

    public void dessiner(Graphics gr) {
        sprite.draw(posx + map.maptest.getposx(), posy + map.maptest.getposy());
        gr.fillOval(desx + map.maptest.getposx(), desy + map.maptest.getposy(), 10, 10);
        draw_poly(gr);
    }

    public void setX(float X) {
        posx = X;
    }

    public void setY(float Y) {
        posy = Y;
    }

    public void addtoX(float X) {
        posx += X;
    }

    public void addtoY(float Y) {
        posy += Y;
    }

    public float getX() {
        return posx;
    }

    public float getY() {
        return posy;
    }

    public float getdesX() {
        return desx;
    }

    public float getdesY() {
        return desy;
    }

    public float getVel() {
        return vel;
    }

    public double getDir() {
        return dir;
    }

    public void setLevelWidth(int levelw) {
        level_width = levelw;
    }

    public void setLevelHeight(int levelh) {
        {
            level_height = levelh;

        }
    }

    public void setAnim(Animation anim) {
        sprite = anim;
        height = anim.getHeight();
        width = anim.getWidth();
    }

    public void setVel(float nvel) {
        vel = nvel;

    }

    public void setVelX(float nvelx) {
        velx = nvelx;

    }

    public void setVelY(float nvely) {
        vely = nvely;

    }

    public void setdesX(float ndesx) {
        desx = ndesx;

    }

    public void setdesY(float ndesy) {
        desy = ndesy;

    }

    void set(float X, float Y) {
    }

    void deplacer(float X, float Y) {
    }

    public void update(KaniMap map) throws SlickException {
    }

    public void setMap(KaniMap maptest) {
        setLevelWidth(maptest.getMapWidth());
        setLevelHeight(maptest.getMapHeight());
        surface = maptest.get_surface();
    }

    public void setPoly() {
        poly = new Polygon(new float[]{
                    posx, posy,
                    posx + width, posy,
                    posx + width, posy + height,
                    posx, posy + height
                });
    }

    public void setHeight(int nheight) {
        height = nheight;
    }

    public void setWidth(int nwidth) {
        width = nwidth;
    }

    public void draw_poly(Graphics gr) {
        KaniMap tmap = map.maptest;
        for (int i = 0; i < tmap.blocked.size(); i++) {
            Block entity = (Block) tmap.blocked.get(i);
            Polygon polyt = entity.getPoly();
            entity.draw(gr);
        }
        gr.draw(poly);

    }

    public void setSprite(Animation anim) {
        sprite = anim;
    }

    public void randompos() throws SlickException {

        boolean founded = false;
        while (!founded) {
            int k = r.nextInt(2);
            posx = r.nextInt(map.maptest.getMapWidth());
            posy = r.nextInt(map.maptest.getMapHeight());
            update_poly();
            if (!isColl()) {
                founded = true;
            }

        }
    }

    public void update_poly() {
        poly = new Polygon(new float[]{
                    posx, posy,
                    posx + width, posy,
                    posx + width, posy + height,
                    posx, posy + height
                });
    }

    public boolean isColl() throws SlickException {

        KaniMap tmap = map.maptest;
        for (int i = 0; i < tmap.blocked.size(); i++) {
            Block entity1 = (Block) tmap.blocked.get(i);
            if (poly.intersects(entity1.poly)) {
                return true;
            }
        }
        return false;
    }

    public boolean isColl(float tx, float ty) throws SlickException {
        return map.maptest.isBlocked(tx, ty);
    }

    public void randomdes() throws SlickException {
        boolean founded = false;
        while (!founded) {
            desx = posx;
            desy = posy;
            int k = r.nextInt(2);

            if (k == 1) {
                desx = r.nextInt(map.maptest.getMapWidth());
            } else {
                desy = r.nextInt(map.maptest.getMapHeight());
            }
            if (!isColl(desx, desy)) {
                founded = true;
            }

        }
        dir = (Math.atan2(desy - posy, desx - posx));
        interp.exec("clockReset()");
    }

    public float getdX() {
        return desx;
    }

    public float getdY() {
        return desy;
    }

    public Shape get_poly() {
        return poly;
    }

    public double dist_rest() {
        return Math.hypot(desx - posx, desy - posy);
    }

    public void update_nothing(float timeloop) {
        interp.set("tloop", timeloop);
        interp.exec("update_nothing(pymonst,tloop )");
    }

    public void update_dir() {
        dir = (Math.atan2(desy - posy, desx - posx));
    }

    public boolean isCibled() {

        return cibled;
    }

    public void set_cibled(float hx, float hy) {
        interp.set("hx", hx);
        interp.set("hy", hy);
        interp.exec("set_cibled(pymonst, hx, hy)");

    }

    @Override
    void dessiner(float X, float Y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
