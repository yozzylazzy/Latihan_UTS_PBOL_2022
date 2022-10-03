/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yosef Adrian
 */
public class DBJual {

    private JualModel data = new JualModel();

    public JualModel getJualModel() {
        return (data);
    }

    public void setJualModel(JualModel s) {
        data = s;
    }

    public ObservableList<JualModel> Load() {
        try {
            ObservableList<JualModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs
                    = con.statement.executeQuery("Select * from jual");
            int i = 1;
            while (rs.next()) {
                JualModel d = new JualModel();
                d.setNofaktur(rs.getString("NoFaktur"));
                d.setKodelgn(rs.getString("KodeLgn"));
                d.setTanggal(rs.getDate("Tanggal"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
