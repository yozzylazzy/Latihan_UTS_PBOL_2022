/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yosef Adrian
 */
public class DBSubjual {

    private SubJualModel data = new SubJualModel();
    private HashMap<String, BarangModel> data2 = new HashMap<String, BarangModel>();

    public HashMap<String, BarangModel> getBarangModel() {
        return (data2);
    }

    public void setBarangModel(BarangModel d) {
        data2.put(d.getKodebrg(), d);
    }

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

    public int validasi(String a, String b) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select Count(*) as jml from subjual where NoFaktur = '" + a + "' and KodeBrg = '" + b + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean Delete(String a, String b) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            //System.out.println(a);
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from subjual where NoFaktur = ? and KodeBrg = ?");
            con.preparedStatement.setString(1, a);
            con.preparedStatement.setString(2, b);
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into subjual(NoFaktur,KodeBrg,Jumlah) values (?,?,?)");
            con.preparedStatement.setString(1, getSubJualModel().getNofaktur());
            con.preparedStatement.setString(2, getSubJualModel().getKodebrg());
            con.preparedStatement.setInt(3, getSubJualModel().getJumlah());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update subjual set Jumlah = ? where NoFaktur = ? and KodeBrg = ?");
            con.preparedStatement.setInt(1, getSubJualModel().getJumlah());
            con.preparedStatement.setString(2, getSubJualModel().getNofaktur());
            con.preparedStatement.setString(3, getSubJualModel().getKodebrg());
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

}
