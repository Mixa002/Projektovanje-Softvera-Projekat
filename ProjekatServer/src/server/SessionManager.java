/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import domain.Radnik;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mihajlodjordjevic
 */
public class SessionManager {
    //ne dozvoljava duplikate
    private static final Set<Radnik> aktivniRadnici = new HashSet<>();

    public static synchronized boolean prijavi(Radnik radnik) {//Ova metoda ne sme biti izvršena istovremeno u više niti nad istim objektom/klasom."
        if (aktivniRadnici.contains(radnik)) {
            return false;
        }
        aktivniRadnici.add(radnik);
        return true;
    }

    public static synchronized void odjavi(Radnik radnik) {
        aktivniRadnici.remove(radnik);
    }

    public static synchronized boolean jePrijavljen(Radnik radnik) {
        return aktivniRadnici.contains(radnik);

    }
}
