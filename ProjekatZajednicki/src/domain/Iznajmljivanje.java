/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mihajlodjordjevic
 */
public class Iznajmljivanje extends AbstractDomainObject {

    private long iznajmljivanjeID;
    private Date datumVreme;
    private String opis;
    private double ukupanIznos;
    private Klijent klijent;
    private Radnik radnik;
    private ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(long iznajmljivanjeID, Date datumVreme, String opis, double ukupanIznos, Klijent klijent, Radnik radnik, ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.iznajmljivanjeID = iznajmljivanjeID;
        this.datumVreme = datumVreme;
        this.opis = opis;
        this.ukupanIznos = ukupanIznos;
        this.klijent = klijent;
        this.radnik = radnik;
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

    @Override
    public String nazivTabele() {
        return " Iznajmljivanje ";
    }

    @Override
    public String alijas() {
        return " izn ";
    }

    @Override
    public String join() {
        return " JOIN KLIJENT k ON (izn.KlijentID = k.KlijentID) JOIN RADNIK r ON (izn.RadnikID = r.RadnikID) "
                + "JOIN GRAD g ON(g.GradID = k.GradID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Radnik r = new Radnik(rs.getLong("RadnikID"), rs.getString("r.Ime"), rs.getString("r.Prezime"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            Grad g = new Grad(rs.getLong("GradID"), rs.getString("g.Naziv"));
            Klijent k = new Klijent(rs.getLong("KlijentID"), rs.getString("k.Ime"), rs.getString("k.Prezime"), rs.getString("Email"), rs.getString("Telefon"), g);
            Iznajmljivanje izn = new Iznajmljivanje(rs.getLong("IznajmljivanjeID"), rs.getDate("DatumVreme"), rs.getString("izn.Opis"),
                    rs.getDouble("UkupanIznos"), k, r, null);
            lista.add(izn);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DatumVreme,Opis,UkupanIznos, KlijentID, RadnikID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + new java.sql.Date(datumVreme.getTime()) + "', '" + opis + "', " + ukupanIznos + ", " + klijent.getKlijentID() + ", " + radnik.getRadnikID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " ukupanIznos = " + ukupanIznos + " ";
    }

    @Override
    public String uslov() {
        return "IznajmljivanjeID = " + iznajmljivanjeID;
    }

    @Override
    public String uslovZaSelect() {
        if (this.klijent != null) {
            return "izn.KlijentID = " + klijent.getKlijentID();
        }
        return "";
    }

    public long getIznajmljivanjeID() {
        return iznajmljivanjeID;
    }

    public void setIznajmljivanjeID(long iznajmljivanjeID) {
        this.iznajmljivanjeID = iznajmljivanjeID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public ArrayList<StavkaIznajmljivanja> getStavkeIznajmljivanja() {
        return stavkeIznajmljivanja;
    }

    public void setStavkeIznajmljivanja(ArrayList<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

}
