/**
 * 
 *
 * @author WILEDION
 */


package kani.monde;

import java.util.ArrayList;
import java.util.Iterator;

public class Monde {

ArrayList <Perso> list_perso;
ArrayList <Objet> list_objet;
ArrayList <Lieu> list_lieu;
Iterator eP;
Iterator eO;
Iterator eL;
        

Monde()
{
list_perso = new ArrayList<kani.monde.Perso>();
list_objet = new ArrayList<kani.monde.Objet>();
list_lieu = new ArrayList<kani.monde.Lieu>();
eP = list_perso.iterator();
eO = list_objet.iterator();
eL = list_lieu.iterator();

}

public void init()
    {   while (eP.hasNext())
        ((Perso)eP.next()).init();

        while (eO.hasNext())
        ((Objet)eO.next()).init();

        while (eL.hasNext())
        ((Lieu)eL.next()).init();
}

public void update()
{}

public void render()
{}

}
