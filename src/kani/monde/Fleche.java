/**
 *
 *
 * @author WILEDION
 */
package kani.monde;

import kani.data.ImgManager;
import kani.data.MapManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

public class Fleche extends Objet {

    Image isprite;
    Polygon poly;
    double vel;
    float velx;
    float vely;
    public static final double DIR_UP = Math.PI / 2;
    public static final double DIR_DOWN = -Math.PI / 2;
    public static final double DIR_LEFT = -Math.PI;
    public static final double DIR_RIGHT = 0;
    public static final double DIR_UPR = Math.PI / 4;
    public static final double DIR_UPL = 3 * Math.PI / 4;
    public static final double DIR_DOWNL = -3 * Math.PI / 4;
    public static final double DIR_DOWNR = -Math.PI / 4;
    MapManager map = MapManager.get();
    ImgManager img = ImgManager.get();
    double dir;

    Fleche(double ndir, float nx, float ny) {

        super();
        vel = 8. / 30;
        dir = ndir;
        isprite = img.arrow;
        posx = nx;
        posy = ny;
        update_poly();
    }

    public void draw() {
        isprite.setRotation(-(float) ((dir * 180) / Math.PI));
        isprite.draw(posx + map.current.getposx(), posy + map.current.getposy());
    }

    void update(float timeloop) {


        velx = (float) (vel * Math.cos(dir));
        vely = -(float) (vel * Math.sin(dir));
        posx += velx * timeloop;
        posy += vely * timeloop;
        update_poly();

    }

    public double get_dir() {

        return dir;
    }

    public boolean in_map(Polygon surface) {

        return !surface.intersects(poly);
    }

    private void update_poly() {

        int width = isprite.getWidth();
        int height = isprite.getHeight();
        poly = new Polygon(new float[]{
            posx, posy,
            posx + width, posy,
            posx + width, posy + height,
            posx, posy + height
        });
    }

    public void draw_poly(Graphics gr) {

        gr.draw(poly);

    }

    public Polygon get_poly() {
        return poly;
    }
}
