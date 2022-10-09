/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yosef Adrian
 */
public class DBBarang {

    private BarangModel data = new BarangModel();

    public BarangModel getBarangModel() {
        return (data);
    }

    public void setBarangModel(BarangModel s) {
        data = s;
    }

    public ObservableList<BarangModel> Load() {
        try {
            ObservableList<BarangModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs
                    = con.statement.executeQuery("Select * from barang");
            int i = 1;
            while (rs.next()) {
                BarangModel d = new BarangModel();
                d.setKodebrg(rs.getString("KodeBrg"));
                d.setNamabrg(rs.getString("NamaBrg"));
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

    public int validasi(String a) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select Count(*) as jml from barang where KodeBrg = '" + a + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean Delete(String a) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            //System.out.println(a);
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from barang where KodeBrg = ?");
            con.preparedStatement.setString(1, a);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into barang(KodeBrg,NamaBrg,Harga) values (?,?,?)");
            con.preparedStatement.setString(1, getBarangModel().getKodebrg());
            con.preparedStatement.setString(2, getBarangModel().getNamabrg());
            con.preparedStatement.setInt(3, getBarangModel().getHarga());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update barang set NamaBrg = ?, Harga = ? where KodeBrg = ?");
            con.preparedStatement.setString(1, getBarangModel().getNamabrg());
            con.preparedStatement.setInt(2, getBarangModel().getHarga());
            con.preparedStatement.setString(3, getBarangModel().getKodebrg());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    
    public ObservableList<BarangModel> LookUp(String krit, String dt) {
        try {
            ObservableList<BarangModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from barang  where " + krit + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                BarangModel d = new BarangModel();
                d.setKodebrg(rs.getString("KodeBrg"));
                d.setNamabrg(rs.getString("NamaBrg"));
                d.setHarga(rs.getInt("Harga"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
