/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Automobil;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import kontroler.Komunikacija;

/**
 *
 * @author mihajlodjordjevic
 */
public class ModelTabeleAutomobili extends AbstractTableModel{
    
    ArrayList<Automobil> automobili;
    String[] kolone = {"Marka", "Model","Registarski broj","Cena"};
    
    
    public ModelTabeleAutomobili(ArrayList<Automobil> automobili){
        this.automobili = automobili;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public int getRowCount() {
        return automobili.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Automobil a = automobili.get(rowIndex);
        
        switch(columnIndex){
                case 0: return a.getMarka();
                case 1: return a.getModel();
                case 2: return a.getRegistarskiBroj();
                case 3: return a.getCenaPoDanu();
                default: return "N/A";
        }
    }
    
    public Automobil getAutomobilAt(int row){
        return automobili.get(row);
    }
    
}
