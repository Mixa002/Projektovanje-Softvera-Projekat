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
public class StavkaIznajmljivanja extends AbstractDomainObject {

    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private String napomena;
    private Date datumOd;
    private Date datumDo;
    private int brojDana;
    private double cena;
    private Automobil automobi;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, int rb, String napomena, Date datumOd, Date datumDo, int brojDana, double cena, Automobil automobi) {
        this.iznajmljivanje = iznajmljivanje;
        this.rb = rb;
        this.napomena = napomena;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.brojDana = brojDana;
        this.cena = cena;
        this.automobi = automobi;
    }

    @Override
    public String nazivTabele() {
        return " StavkaIznajmljivanja ";
    }

    @Override
    public String alijas() {
        return " si ";
    }

    @Override
    public String join() {
        return """
                  join automobil auto on(auto.automobilID = si.AutomobilID)
                  join iznajmljivanje izn on(izn.iznajmljivanjeID = si.IznajmljivanjeID)
                  join radnik r on(r.radnikID = izn.radnikID)
                  join klijent k on(k.KlijentID = izn.KlijentID)
                  join grad g on(g.gradID = k.gradID)
                  """;
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Grad g = new Grad(rs.getLong("GradID"), rs.getString("g.naziv"));
            Radnik r = new Radnik(rs.getLong("radnikID"), rs.getString("r.Ime"), rs.getString("r.Prezime"),
                    rs.getString("KorisnickoIme"), rs.getString("Lozinka"));
            Klijent k = new Klijent(rs.getLong("KlijentID"), rs.getString("k.Ime"), rs.getString("k.Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"), g);
            Iznajmljivanje izn = new Iznajmljivanje(rs.getLong("IznajmljivanjeID"), rs.getDate("DatumVreme"), rs.getString("izn.Opis"),
                    rs.getDouble("izn.UkupanIznos"), k, r, null);
            Automobil auto = new Automobil(rs.getLong("AutomobilID"), rs.getString("RegistarskiBroj"), rs.getString("Marka"),
                    rs.getString("Model"), rs.getDouble("CenaPoDanu"));
            StavkaIznajmljivanja si = new StavkaIznajmljivanja(izn, rs.getInt("Rb"), rs.getString("si.Napomena"), rs.getDate("DatumOd"),
                    rs.getDate("DatumDo"), rs.getInt("BrojDana"), rs.getDouble("Cena"), auto);
            lista.add(si);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (IznajmljivanjeID, Rb,Napomena,DatumOd,DatumDo,BrojDana,Cena,AutomobilID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return iznajmljivanje.getIznajmljivanjeID() + ", "
                + rb + ", '"
                + napomena + "', '"
                + new java.sql.Date(datumOd.getTime()) + "', '"
                + new java.sql.Date(datumDo.getTime()) + "', "
                + brojDana + ", "
                + cena + ", "
                + automobi.getAutomobilID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "AutomobilID = " + automobi.getAutomobilID() +
           ", DatumOd = '" + new java.sql.Date(datumOd.getTime()) + "'" +
           ", DatumDo = '" + new java.sql.Date(datumDo.getTime()) + "'" +
           ", BrojDana = " + brojDana +
           ", Cena = " + cena +
           ", Napomena = '" + napomena + "'";
    }

    @Override
    public String uslov() {
        return "IznajmljivanjeID = " + iznajmljivanje.getIznajmljivanjeID() +
           " AND Rb = " + rb;
    }

    @Override
    public String uslovZaSelect() {
        if (this.iznajmljivanje != null && iznajmljivanje.getIznajmljivanjeID() > 0) {
            return "izn.IznajmljivanjeID = " + iznajmljivanje.getIznajmljivanjeID();
        }
        return "";
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Automobil getAutomobi() {
        return automobi;
    }

    public void setAutomobi(Automobil automobi) {
        this.automobi = automobi;
    }

}
