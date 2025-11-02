/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mihajlodjordjevic
 */
public class Radnik extends AbstractDomainObject {

    private long radnikID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Radnik() {
    }

    public Radnik(long radnikID, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.radnikID = radnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public long getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(long radnikID) {
        this.radnikID = radnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " Radnik ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Radnik r = new Radnik(rs.getLong("RadnikID"), rs.getString("Ime"),
                    rs.getString("Prezime"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            lista.add(r);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, KorisnickoIme, Lozinka) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + lozinka + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "Ime = '" + ime + "', Prezime = '" + prezime + "', KorisnickoIme = '" + korisnickoIme + "', Lozinka = '" + lozinka + "' ";
    }

    @Override
    public String uslov() {
        return " RadnikID = " + radnikID;
    }

    @Override
    public String uslovZaSelect() {
        return " KorisnickoIme = '" + korisnickoIme + "' AND Lozinka = '" + lozinka + "' ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Radnik radnik = (Radnik) obj;
        return radnikID == radnik.radnikID;
    }
    //svakom objektu vraca broj koji se koristi kada se koriste hash bazirane kolekcije, i pomocu njega pristupa
    @Override
    public int hashCode() {
        return Long.hashCode(radnikID);
    }

}
