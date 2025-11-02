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
public class Klijent extends AbstractDomainObject{
    private long klijentID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Grad grad;

    public Klijent() {
    }

    public Klijent(long klijentID, String ime, String prezime, String email, String telefon, Grad grad) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.grad = grad;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    @Override
    public String nazivTabele() {
        return " Klijent ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return " JOIN Grad g ON g.GradID = k.GradID ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while(rs.next()){
            Grad g = new Grad(rs.getLong("g.GradID"), rs.getString("g.Naziv"));
            Klijent k = new Klijent(rs.getLong("KlijentID"), rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"), g);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon, GradID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + ime + "', '" + prezime + "', '" + email + "', '" + telefon + "', '" + grad.getGradID() + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " ime = '" + ime + "', prezime = '" + prezime + "', email = '" + email + 
                "', telefon = '" + telefon + "', GradID = " + grad.getGradID();
    }

    @Override
    public String uslov() {
        return " KlijentID = " + klijentID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public long getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(long klijentID) {
        this.klijentID = klijentID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }
    
}
