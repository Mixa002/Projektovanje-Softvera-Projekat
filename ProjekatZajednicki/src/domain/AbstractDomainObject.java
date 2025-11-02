/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mihajlodjordjevic
 */

//ocemo da ostatak sistema radi sa bilo kojim objektom bez da zna tip pa pravimo ovakvu klasu
public abstract class AbstractDomainObject implements Serializable {

    public abstract String nazivTabele();

    public abstract String alijas();

    public abstract String join();

    public abstract ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;//pretvara rezultat sql upita u listu ovog domena

    public abstract String koloneZaInsert();

    public abstract String vrednostiZaInsert();

    public abstract String vrednostiZaUpdate();

    public abstract String uslov();

    public abstract String uslovZaSelect();

}
