/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.AbstractDomainObject;
import domain.Automobil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;

/**
 *
 * @author mihajlodjordjevic
 */
public class DbBroker {

    private static DbBroker instance;
    private Connection conn;

    private DbBroker() throws Exception {
        konektujSeNaBazu();
    }

    public static DbBroker getInstance() throws Exception {
        if (instance == null) {
            instance = new DbBroker();
        }
        return instance;
    }

    private void konektujSeNaBazu() throws Exception {
        try {
            Class.forName(Util.getInstance().getDriver());
            String url = Util.getInstance().getUrl();
            String username = Util.getInstance().getUsername();
            String password = Util.getInstance().getPassword();

            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);

            System.out.println("USPESNO POVEZANO!");

        } catch (SQLException ex) {
            if (ex.getMessage().toLowerCase().contains("communications link failure") || ex.getMessage().toLowerCase().contains("connection refused")) {
                throw new Exception("Neuspešno povezivanje na bazu. Proverite da li je MySQL server pokrenut.");
            } else {
                throw new Exception("Greška prilikom konekcije na bazu: " + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            throw new Exception("MySQL driver nije pronađen.");
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void commit() throws SQLException {
        conn.commit();
    }

    public void rollback() throws SQLException {
        conn.rollback();
    }

    public List<AbstractDomainObject> pronadji(AbstractDomainObject domainObject) throws RuntimeException {
        List<AbstractDomainObject> lista = new ArrayList<>();

        try {
            Statement s = conn.createStatement();
            String upit = "SELECT * FROM " + domainObject.nazivTabele() + " WHERE " + domainObject.uslovZaSelect();
            System.out.println(upit);
            ResultSet rs = s.executeQuery(upit);
            lista = domainObject.vratiListu(rs);
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void insert(AbstractDomainObject ado) {
        try {
            String upit = "INSERT INTO " + ado.nazivTabele() + ado.koloneZaInsert() + " VALUES (" + ado.vrednostiZaInsert() + ") ";
            System.out.println(upit);

            Statement s = conn.createStatement();
            s.executeUpdate(upit);
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<AbstractDomainObject> select(AbstractDomainObject kriterijum) {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + kriterijum.nazivTabele() + kriterijum.alijas() +  kriterijum.join();

        String uslov = kriterijum.uslovZaSelect();

        if (uslov != null && !uslov.trim().isEmpty()) {
            upit += " WHERE " + uslov;
        }
        System.out.println("SELECT NAM JE OVO: " + upit);

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(upit);
            lista = kriterijum.vratiListu(rs);
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean delete(AbstractDomainObject ado) throws Exception {
        String upit = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.uslov();
        System.out.println("DELETE UPIT: " + upit);

        try {
            PreparedStatement ps = conn.prepareStatement(upit);
            int izbrisaniRedovi = ps.executeUpdate();
            return izbrisaniRedovi > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void proveriDostupnost(AbstractDomainObject ado) throws Exception {
        Automobil auto = (Automobil) ado;

        String upit = "SELECT * FROM StavkaIznajmljivanja WHERE AUTOMOBILID = " + auto.getAutomobilID();

        ResultSet rs = conn.createStatement().executeQuery(upit);
        while (rs.next()) {
            throw new Exception("Auto je vec iznajmljen, pa se ne moze izbrisati!");
        }
    }

    public boolean update(AbstractDomainObject ado) throws Exception {
        String upit = "UPDATE " + ado.nazivTabele() + " SET " + ado.vrednostiZaUpdate() + " WHERE " + ado.uslov();
        System.out.println("SQL koji se izvršava GDE JE PROBLEM: " + upit);
        try {
            PreparedStatement ps = conn.prepareStatement(upit);
            int brojRedova = ps.executeUpdate();
            return brojRedova > 0;
        } catch (Exception ex) {
            throw new Exception("Greska prilikom izmene: " + ex.getMessage());
        }

    }
    
    public AbstractDomainObject vratiJednog(AbstractDomainObject ado) throws Exception{
        ArrayList<AbstractDomainObject> lista = select(ado);
        if(lista != null && !lista.isEmpty()){
            return lista.get(0);
        }
        return null;
    }
    public long insertWithGeneratedKeys(AbstractDomainObject ado) throws Exception{
        String upit = "INSERT INTO " + ado.nazivTabele() + ado.koloneZaInsert() + " VALUES (" + ado.vrednostiZaInsert() + ")";
        System.out.println("INSERT GENERATED KEYS: " + upit);
        
        try{
            PreparedStatement ps = conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                return rs.getLong(1);
            }else{
                throw new Exception("ID nije generisan!");
            }
        }catch(Exception ex){
            throw new Exception("Greska prilikom inserta sa generisanim kljucem!");
        }
                
    }
}
