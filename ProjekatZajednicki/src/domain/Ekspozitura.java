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
public class Ekspozitura extends AbstractDomainObject{
    private long ekspozituraID;
    private String naziv;

    public Ekspozitura() {
    }

    public Ekspozitura(long ekspozituraID, String naziv) {
        this.ekspozituraID = ekspozituraID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public long getEkspozituraID() {
        return ekspozituraID;
    }

    public void setEkspozituraID(long ekspozituraID) {
        this.ekspozituraID = ekspozituraID;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String nazivTabele() {
        return " Ekspozitura ";
    }

    @Override
    public String alijas() {
        return " ekspo ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while(rs.next()){
            Ekspozitura ekspo = new Ekspozitura(rs.getLong("EkspozituraID"), rs.getString("Naziv"));
            lista.add(ekspo);
        }
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return " EkspozituraID = '" + ekspozituraID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
    
    
}
