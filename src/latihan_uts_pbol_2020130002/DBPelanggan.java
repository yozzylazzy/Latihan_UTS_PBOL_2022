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
public class DBPelanggan {
    private PelangganModel data = new PelangganModel();

    public PelangganModel getPelangganModel() {
        return (data);
    }

    public void setPelangganModel(PelangganModel s) {
        data = s;
    }

    public ObservableList<PelangganModel> Load() {
        try {
            ObservableList<PelangganModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = 
                    con.statement.executeQuery("Select * from pelanggan");
            int i = 1;
            while (rs.next()) {
                PelangganModel d = new PelangganModel();
                d.setKodelgn(rs.getString("KodeLgn"));
                d.setNamalgn(rs.getString("NamaLgn"));
                d.setAlamat(rs.getString("Alamat"));
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
