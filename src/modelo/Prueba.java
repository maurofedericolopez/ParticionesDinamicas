/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Mauro
 */
public class Prueba {
    public static void main(String[] args) {
        LinkedList<String> cadenas = new LinkedList();
        cadenas.addFirst("Mauro Lopez");

        Iterator<String> i = cadenas.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println("");
        cadenas.addFirst("Mauro Federico");

        i = cadenas.iterator();
        while(i.hasNext())
            System.out.println(i.next());
    }
}
