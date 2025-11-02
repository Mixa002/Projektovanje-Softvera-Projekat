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
public class Grad extends AbstractDomainObject{
    private long gradID;
    private String naziv;

    public Grad() {
    }

    public Grad(long gradID, String naziv) {
        this.gradID = gradID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public long getGradID() {
        return gradID;
    }

    public void setGradID(long gradID) {
        this.gradID = gradID;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String nazivTabele() {
        return " Grad ";
    }

    @Override
    public String alijas() {
        return " g ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while(rs.next()){
            Grad g = new Grad(rs.getLong("GradID"), rs.getString("Naziv"));
            lista.add(g);
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
        return "naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return "GradID = " + gradID;
    }

    @Override
    public String uslovZaSelect() {
        return "naziv = '" + naziv + "'";
    }
    
    
}
