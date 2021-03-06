/**
 *
 *
 * @author WILEDION
 */
package kani.utils;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;

public class MessageBox {

    RoundedRectangle box;
    String str;
    StringBuffer buff;
    int index;
    AngelCodeFont font;
    int x, y, width, height, numb_line;
    Color back;
    Timer tac;
    Timer wait;
    Boolean end;
    Boolean ifwait;

    public MessageBox(int px, int py, int pwidth, int pheight) throws SlickException {

        x = px;
        y = py;
        width = pwidth;
        height = pheight;
        font = new AngelCodeFont("data/demo.fnt", "data/demo_00.tga");
        box = new RoundedRectangle(x, y, width, height, 15);
        back = new Color(55, 55, 55, 155);
        clean();
    }

    public void draw(Graphics gr) {
    }

    public void addText(StringBuffer text) {

        StringBuffer word = new StringBuffer();
        StringBuffer line = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            while ((i < text.length()) && (text.charAt(i) != ' ')) {
                word.append((text.charAt(i)));
                i++;
            }

            if (i < text.length()) {
                if (font.getWidth(line + " " + word) < width) {
                    line.append(word.append(" "));
                    word.delete(0, word.length());
                } else {
                    str = str.concat(line.toString() + "\n");
                    line.delete(0, line.length());
                    line.append(word + " ");
                    word.delete(0, word.length());
                }
            }
        }

        if (font.getWidth(line + " " + word) < width) {
            str = str.concat(line + " " + word.toString());
        } else {
            str = str.concat(line.toString() + "\n" + word.toString());
        }
    }

    public void clean() {
        str = new String();
        buff = new StringBuffer();
        tac = new Timer();
        wait = new Timer();
        index = 0;
        end = false;
        ifwait = false;
        numb_line = 0;
    }

    public void draw(GameContainer gc, Graphics gr) {

        gr.setColor(back);
        gr.fill(box);
        gr.setColor(Color.white);
        font.drawString(x, y, buff.toString());
    }

    public void start() {
    }

    public void update() {
        if (tac.getElapsedTime() > 30 && (!end)) {
            if (numb_line == 3) {
                ifwait = true;
            }

            if (ifwait == false) {
                char c = str.charAt(index);
                if (c == '\n') {
                    numb_line++;
                }
                buff.append(str.charAt(index));
                index++;
                tac.Reset();
                if (index >= str.length()) {
                    end = true;
                }
            }
        }
    }

    public boolean is_ended() {
        return end;
    }

    public void set_wait_false() {
        ifwait = false;
        numb_line = 0;
        buff.delete(0, buff.length());

    }

    public boolean iswait() {
        return ifwait;
    }
}
