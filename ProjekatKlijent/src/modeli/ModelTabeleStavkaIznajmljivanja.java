/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.StavkaIznajmljivanja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mihajlodjordjevic
 */
public class ModelTabeleStavkaIznajmljivanja extends AbstractTableModel {

    private ArrayList<StavkaIznajmljivanja> lista;
    String[] kolone = {"Rb", "Automobil", "Datum Od", "Datum Do", "Broj Dana", "Cena", "Napomena"};

    public ModelTabeleStavkaIznajmljivanja() {
        lista = new ArrayList<>();
    }
    public ModelTabeleStavkaIznajmljivanja(ArrayList<StavkaIznajmljivanja> stavke){
        this.lista = stavke;
    }
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIznajmljivanja si = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return si.getRb();
            case 1:
                return si.getAutomobi();
            case 2:
                return new SimpleDateFormat("dd.MM.yyyy").format(si.getDatumOd());
            case 3:
                return new SimpleDateFormat("dd.MM.yyyy").format(si.getDatumDo());
            case 4:
                return si.getBrojDana();
            case 5:
                return si.getCena();
            case 6:
                return si.getNapomena();
            default:
                return "N/A";
        }
    }

    public void dodajStavku(StavkaIznajmljivanja stavkaIznajmljivanja) {
        lista.add(stavkaIznajmljivanja);
        fireTableDataChanged();
    }

    public ArrayList<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void setLista(ArrayList<StavkaIznajmljivanja> lista) {
        this.lista = lista;
    }

    public StavkaIznajmljivanja getStavkaAt(int i) {
        return lista.get(i);
    }

    public void obrisiStavku(int selectedRow) {
        lista.remove(selectedRow);
    }
    
}
