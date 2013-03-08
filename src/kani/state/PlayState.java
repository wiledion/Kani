/**
 *
 *
 * @author WILEDION
 */
package kani.state;

import java.util.ArrayList;
import kani.monde.Bird;
import kani.monde.Fleche;
import kani.monde.Heros;
import kani.monde.Monst;
import kani.monde.Perso;
import kani.monde.Statue;
import kani.utils.Camera;
import kani.utils.Combos;
import kani.utils.KaniMap;
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
import org.python.util.PythonInterpreter;

public class PlayState extends LevelState {

    boolean intro;
    boolean end;
    boolean is_dead;
    boolean loopstarted = false;
    boolean key_up;
    boolean key_down;
    boolean key_left;
    boolean key_right;
    Timer loopclock;
    Timer comboclock;
    float looptime;
    String intro_msg;
    String end_msg;
    String dead_msg;
    String level_name;
    int number_enn;
    PythonInterpreter interp = new PythonInterpreter();
    PythonInterpreter interp2 = new PythonInterpreter();
    KaniMap kmap;
    Combos kcombo;
    int id_level = 1;
    int enn_config[][];
    int size_cfg;

    public PlayState(int stateID) {
        super();
        this.stateID = stateID;
    }

    public int getID() {
        return stateID;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start_level(gc, sbg, "level1");
    }

    public void start_level(GameContainer gc, StateBasedGame sbg, String level) throws SlickException {
        input = gc.getInput();
        cam = new Camera();
        dead_msg = "Vous etes MORT !";
        interp.execfile("data/map/" + level + ".py");
        interp.set("pyplay", this);
        interp.exec("init(pyplay)");
        level_reset();
        intro = true;
        end = false;
        is_dead = false;
        AngelCodeFont font = new AngelCodeFont("data/demo2.fnt", "data/demo2_00.tga");
        msg = new MessageBox(150, 497, 500, 80);
        msg.addText(new StringBuffer(intro_msg));
        msg.start();
        loopclock = new Timer();
        comboclock = new Timer();
        kcombo = new Combos();

    }

    private void level_reset() throws SlickException {

        hkani = new Heros();
        running = true;
        paused = false;
        victory = false;
        ennemis = new ArrayList<Monst>();
        init_enn();
        clock_pause = new Timer();
        clock_pause.Reset();
        cam.setCam(0, 0);
        hkani.setMap(map.current);
        hkani.setCamera(cam);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {

        for (int layer = 0; layer <= 2; layer++) {
            map.current.render(cam, layer);

            for (int i = 0; i < ennemis.size(); i++) {

                Monst kmonst = (Monst) ennemis.get(i);
                if (kmonst.getLayer() == layer) {
                    kmonst.dessiner(gr);
                }
            }

            if (layer == 1) {
                hkani.dessiner();
            }

        }

        img.tablo.draw(0, 450);
        img.logo.draw(701, 500);
        hkani.draw_inf(gr);
        msg.draw(gc, gr);
        if (paused) {
            gr.drawString("PAUSE", 350, 420);
        }

        if (victory) {
            gr.drawString(end_msg, 350, 500);
        }
        int u = ennemis.size();
        gr.setColor(Color.white);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if (loopstarted == false) {
            loopclock.Reset();
            loopstarted = true;
        } else {
            looptime = loopclock.getElapsedTime();
            loopclock.Reset();
        }

        if (!paused) {

            if (intro) {
                msg.update();
                if (msg.iswait() && input.isKeyDown(Input.KEY_SPACE)) {
                    msg.set_wait_false();
                }

                intro = !(msg.is_ended() && input.isKeyDown(Input.KEY_SPACE));
                if (intro == false) {
                    msg.clean();
                }
            } else if (end) {
                msg.update();
                if (msg.iswait() && input.isKeyDown(Input.KEY_SPACE)) {
                    msg.set_wait_false();
                }
                if (msg.is_ended() && input.isKeyDown(Input.KEY_SPACE)) {
                    id_level++;
                    if (id_level != 5) {
                        start_level(gc, sbg, "level" + String.valueOf(id_level));
                    } else {
                        id_level = 1;
                        start_level(gc, sbg, "level" + String.valueOf(id_level));
                        sbg.enterState(2);
                    }
                }

            } else if (is_dead) {
                hkani.set_stand();
                msg.update();
                if (msg.iswait() && input.isKeyDown(Input.KEY_SPACE)) {
                    msg.set_wait_false();
                }
                if (msg.is_ended() && input.isKeyDown(Input.KEY_SPACE)) {
                    id_level = 1;
                    start_level(gc, sbg, "level" + String.valueOf(id_level));
                    sbg.enterState(2);
                }
            } else {

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

                    // Combos verifications
                    if (input.isKeyPressed(Input.KEY_UP)) {
                        kcombo.add(Input.KEY_UP, comboclock);
                    }
                    if (input.isKeyPressed(Input.KEY_DOWN)) {
                        kcombo.add(Input.KEY_DOWN, comboclock);
                    }
                    if (input.isKeyPressed(Input.KEY_LEFT)) {
                        kcombo.add(Input.KEY_LEFT, comboclock);
                    }
                    if (input.isKeyPressed(Input.KEY_RIGHT)) {
                        kcombo.add(Input.KEY_RIGHT, comboclock);
                    }
                    hkani.setDir(vx, vy);
                    hkani.update_move(looptime);
                }

                if (input.isKeyPressed(Input.KEY_Q)) {
                    kcombo.add(Input.KEY_Q, comboclock);
                }

                if (input.isKeyDown(Input.KEY_P)) {
                    if (clock_pause.getElapsedTime() > 1000) {
                        paused = true;
                        clock_pause.Reset();
                    }
                }
                heroColl_update();
                monst_update();
                map.current.update(cam);

                kcombo.verify(hkani);

                if (hkani.get_pv() <= 0) {
                    is_dead = true;
                    msg.clean();
                    msg.addText(new StringBuffer(dead_msg));
                }

                if (ennemis.isEmpty()) {
                    end = true;
                    msg.clean();
                    msg.addText(new StringBuffer(end_msg));
                    hkani.update_nothing(looptime);
                }
            }
        } else if (input.isKeyDown(Input.KEY_P)) {
            if (clock_pause.getElapsedTime() > 1000) {
                paused = false;
                clock_pause.Reset();
            }
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
            Perso kmonst = ennemis.get(i);
            if (hero.intersects(kmonst.get_poly())) {
                hkani.addTo_pv(-1);
                break;
            }
        }
    }

    private boolean is_dead() {
        return is_dead;
    }

    private void monst_update() throws SlickException {

        for (int i = 0; i < ennemis.size(); i++) {
            Monst kmonst = (Monst) ennemis.get(i);
            kmonst.update_nothing(looptime);
            float hx = hkani.getX();
            float hy = hkani.getY();
            if ((Math.abs(kmonst.getX() - hx) < 200) && (Math.abs(kmonst.getY() - hy) < 200) && !kmonst.isCibled()) {
                kmonst.set_cibled(hx, hy);
            }
        }

        if (ennemis.size() != 0) {
            ArrayList<Fleche> all_arrow = hkani.get_all_arrow();
            for (int i = 0; i < ennemis.size() && ennemis.size() != 0; i++) {
                for (int j = 0; j < all_arrow.size() && ennemis.size() != 0; j++) {
                    Monst kmonst = (Monst) ennemis.get(i);
                    Fleche karrow = all_arrow.get(j);
                    Shape enn_poly = kmonst.get_poly();
                    Shape arr_poly = karrow.get_poly();

                    if (enn_poly.intersects(arr_poly)) {
                        kmonst.update_hit();
                        if (kmonst.is_dead()) {
                            ennemis.remove(i);
                        }
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
    }

    public void set_intro_msg(String msg) {
        intro_msg = msg;
    }

    public void set_end_msg(String msg) {
        end_msg = msg;
    }

    public void set_map(KaniMap nmap) {
        kmap = nmap;
    }

    public void set_enn(int numb_enn) {
        number_enn = numb_enn;


    }

    public void set_enn_config(int nconfig[][], int size) {

        enn_config = nconfig;
        size_cfg = size;

    }

    void init_enn() throws SlickException {

        for (int i = 0; i < size_cfg; i++) {
            switch (enn_config[i][0]) {

                case 1:
                    for (int j = 0; j < enn_config[i][1]; j++) {
                        ennemis.add(new Statue());
                        ennemis.get(j).setMap(map.current);
                    }
                    break;

                case 2:
                    for (int j = 0; j < enn_config[i][1]; j++) {
                        ennemis.add(new Bird());
                        ennemis.get(j).setMap(map.current);
                    }
                    break;
            }
        }
    }
}
