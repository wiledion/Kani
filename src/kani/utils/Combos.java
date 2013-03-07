/**
 *
 *
 * @author WILEDION
 */
package kani.utils;

import java.util.ArrayList;
import kani.monde.Heros;
import org.newdawn.slick.Input;


public class Combos {

    ArrayList<Integer> taps;
    ArrayList<Integer> up_3_arrows;
    ArrayList<Integer> down_3_arrows;
    ArrayList<Integer> left_3_arrows;
    ArrayList<Integer> right_3_arrows;
    ArrayList<Integer> card_arrows;
    boolean added;
    long last_tap;

    void init_combos() {
        up_3_arrows = new ArrayList();
        down_3_arrows = new ArrayList();
        left_3_arrows = new ArrayList();
        right_3_arrows = new ArrayList();
        card_arrows = new ArrayList();

        for (int i = 1; i <= 2; i++) {
            up_3_arrows.add(Input.KEY_UP);
            down_3_arrows.add(Input.KEY_DOWN);
            left_3_arrows.add(Input.KEY_LEFT);
            right_3_arrows.add(Input.KEY_RIGHT);
        }

        up_3_arrows.add(Input.KEY_Q);
        down_3_arrows.add(Input.KEY_Q);
        left_3_arrows.add(Input.KEY_Q);
        right_3_arrows.add(Input.KEY_Q);
        
        up_3_arrows.add(Input.KEY_UP);
        down_3_arrows.add(Input.KEY_DOWN);
        left_3_arrows.add(Input.KEY_LEFT);
        right_3_arrows.add(Input.KEY_RIGHT);
        
        card_arrows.add(Input.KEY_RIGHT);
        card_arrows.add(Input.KEY_DOWN);
        card_arrows.add(Input.KEY_LEFT);
        card_arrows.add(Input.KEY_UP);
        card_arrows.add(Input.KEY_Q);

    }

    public Combos() {
        reset();
        last_tap = 0;
        init_combos();
        added = false;
    }

    public void add(int key, Timer comboclock) {
        long time = comboclock.getElapsedTime();

        added = true;

        if (time - last_tap > 200) {
            taps = new ArrayList();
            last_tap = 0;
            comboclock.Reset();
            taps.add(key);
        } else {
            last_tap = time;
            taps.add(key);
        }

        if (taps.size() > 5) {
            taps.remove(0);
        }
    }

    public void reset() {
        taps = new ArrayList();
    }

    public void verify(Heros hkani) {


        if (!taps.isEmpty()) {

            if (taps.equals(up_3_arrows)) {
                reset();
                last_tap = -500;

                hkani.add_tir(Heros.DIR_UP, -10, 0);
                hkani.add_tir(Heros.DIR_UP, 10, 0);

            } else if (taps.equals(down_3_arrows)) {
                reset();
                last_tap = -500;

                hkani.add_tir(Heros.DIR_DOWN, -10, 0);
                hkani.add_tir(Heros.DIR_DOWN, 10, 0);

            } else if (taps.equals(left_3_arrows)) {
                reset();
                last_tap = -500;

                hkani.add_tir(Heros.DIR_LEFT, 0, -10);
                hkani.add_tir(Heros.DIR_LEFT, 0, 10);

            } else if (taps.equals(right_3_arrows)) {
                reset();
                last_tap = -500;

                hkani.add_tir(Heros.DIR_RIGHT, 0, -10);
                hkani.add_tir(Heros.DIR_RIGHT, 0, 10);

            } else if (taps.equals(card_arrows)) {
                reset();
                last_tap = -500;

                hkani.add_tir(Heros.DIR_UP, 0, 0);
                hkani.add_tir(Heros.DIR_DOWN, 0, 0);
                hkani.add_tir(Heros.DIR_LEFT, 0, 0);
                hkani.add_tir(Heros.DIR_RIGHT, 0, 0);

            } else if (taps.get(taps.size() - 1) == Input.KEY_Q && added) {
                added = false;
                hkani.update_tir();
            }
        }
    }

    public int size() {
        return taps.size();
    }
}
