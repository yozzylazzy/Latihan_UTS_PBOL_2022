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
public class DBSubjual {

    private SubJualModel data = new SubJualModel();

    public SubJualModel getSubJualModel() {
        return (data);
    }

    public void setSubJualModel(SubJualModel s) {
        data = s;
    }

    public ObservableList<SubJualModel> Load() {
        try {
            ObservableList<SubJualModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs
                    = con.statement.executeQuery("Select s.*, b.harga from subjual s "
                            + "join barang b on (s.kodebrg=b.kodebrg)");
            int i = 1;
            while (rs.next()) {
                SubJualModel d = new SubJualModel();
                d.setNofaktur(rs.getString("NoFaktur"));
                d.setKodebrg(rs.getString("KodeBrg"));
                d.setJumlah(rs.getInt("Jumlah"));
                d.setHarga(rs.getInt("Harga"));
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
