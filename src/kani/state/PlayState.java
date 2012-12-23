/**
 *
 *
 * @author WILEDION
 */
package kani.state;

import java.util.ArrayList;
import kani.monde.Fleche;
import kani.monde.Heros;
import kani.monde.PersoScript;
import kani.monde.Statue;
import kani.utils.Camera;
import kani.utils.MessageBox;
import kani.utils.Timer;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends LevelState {

    boolean intro;
    boolean loopstarted = false;
    boolean key_up;
    boolean key_down;
    boolean key_left;
    boolean key_right;
    Timer loopclock;
    float looptime;

    public PlayState(int stateID) {

        super();
        this.stateID = stateID;
    }

    public int getID() {
        return stateID;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {


        input = gc.getInput();
        cam = new Camera();
        level_reset();
        intro = true;
        AngelCodeFont font = new AngelCodeFont("demo2.fnt", "demo2_00.tga");
        msg = new MessageBox(150, 497, 500, 80);
        msg.addText(new StringBuffer("Jeu"));
        msg.start();
        loopclock = new Timer();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {

        for (int layer = 0; layer <= 2; layer++) {

            map.maptest.render(cam, layer);
            if (layer == 1) {

                for (int i = 0; i < ennemis.size(); i++) {

                    Statue kmonst = ennemis.get(i);
                    kmonst.dessiner(gr);

                }

                hkani.dessiner();
            }
        }

        img.tablo.draw(0, 450);
        img.logo.draw(701, 500);
        hkani.draw_inf(gr);
        msg.draw(gc, gr);

        if (paused) {
            gr.drawString("PAUSE", 350, 500);
        }

        is_dead(sbg);

        if (victory) {
            gr.drawString("Niveau test complété", 350, 500);
        }
        int u = ennemis.size();
        gr.setColor(Color.white);
        gr.drawString("Monstres restants : " + (ennemis.size()) + "", 300, 472);

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            sbg.enterState(2);
            level_reset();
            running = false;
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if (loopstarted == false) {
            loopclock.Reset();
            loopstarted = true;
        } else {
            looptime = loopclock.getElapsedTime();
            loopclock.Reset();
        }

        if (!victory) {

            if (intro) {
            }

            if (!paused) {

                if (!intro) {


                    if (!input.isKeyDown(Input.KEY_UP)
                            && !input.isKeyDown(Input.KEY_RIGHT)
                            && !input.isKeyDown(Input.KEY_DOWN)
                            && !input.isKeyDown(Input.KEY_LEFT)) {
                        hkani.update_nothing(looptime);
                    } else {
                        int vx = 0;
                        int vy = 0;

                        if (input.isKeyDown(Input.KEY_UP)) {
                            vy--;
                        }

                        if (input.isKeyDown(Input.KEY_DOWN)) {
                            vy++;
                        }

                        if (input.isKeyDown(Input.KEY_LEFT)) {
                            vx--;
                        }

                        if (input.isKeyDown(Input.KEY_RIGHT)) {
                            vx++;
                        }

                        hkani.setDir(vx, vy);
                        hkani.update_move(looptime);

                    }

                    if (input.isKeyDown(Input.KEY_Q)) {
                        hkani.update_tir();
                    }

                    if (input.isKeyDown(Input.KEY_P)) {
                        if (clock_pause.getElapsedTime() > 1000) {
                            paused = true;
                            clock_pause.Reset();
                        }
                    }
                    heroColl_update();
                    monst_update();
                    map.maptest.update(cam);
                } else {
                    msg.update();
                    intro = !msg.is_ended();
                }
            } else if (input.isKeyDown(Input.KEY_P)) {
                if (clock_pause.getElapsedTime() > 1000) {
                    paused = false;
                    clock_pause.Reset();
                }
            }

        }

        if (ennemis.size() == 0) {
            victory = true;
            hkani.update_nothing(looptime);
        }

    }

    private String String(boolean band) {
        if (band = true) {
            return "true";
        } else {
            return "false";
        }

    }

    private void heroColl_update() {
        Polygon hero = hkani.get_poly();
        for (int i = 0; i < ennemis.size(); i++) {
            Statue kmonst = ennemis.get(i);
            if (hero.intersects(kmonst.get_poly())) {
                hkani.addTo_pv(-1);
                break;
            }
        }
    }

    private void is_dead(StateBasedGame sbg) throws SlickException {

        if (hkani.get_pv() <= 0) {
            sbg.enterState(2);
            level_reset();
            running = false;
        }
    }

    private void level_reset() throws SlickException {

        hkani = new Heros();
        running = true;
        paused = false;
        victory = false;
        ennemis = new ArrayList<Statue>();
        for (int i = 0; i < 10; i++) {
            ennemis.add(new Statue());
            ennemis.get(i).setMap(map.maptest);
        }
        clock_pause = new Timer();
        clock_pause.Reset();
        cam.setCam(0, 0);
        hkani.setMap(map.maptest);
        hkani.setCamera(cam);
    }

    private void monst_update() throws SlickException {

        for (int i = 0; i < ennemis.size(); i++) {
            Statue kmonst = ennemis.get(i);
            kmonst.update_nothing(looptime);
            float hx = hkani.getX();
            float hy = hkani.getY();
            if ((Math.abs(kmonst.getX() - hx) < 200) && (Math.abs(kmonst.getY() - hy) < 200) && !kmonst.isCibled()) {
                kmonst.set_cibled(hx, hy);
            }

        }

        ArrayList<Fleche> all_arrow = hkani.get_all_arrow();
        for (int i = 0; i < ennemis.size(); i++) {
            for (int j = 0; j < all_arrow.size(); j++) {
                Statue kmonst = ennemis.get(i);
                Fleche karrow = all_arrow.get(j);
                Shape enn_poly = kmonst.get_poly();
                Shape arr_poly = karrow.get_poly();

                if (enn_poly.intersects(arr_poly)) {
                    ennemis.remove(i);
                    hkani.remove_arrow(j);
                    if (i > 0) {
                        i--;
                    }
                    if (j > 0) {
                        j--;
                    }
                }
            }
        }

    }

    public void set_running() {
        running = true;
    }
}
