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
    public int validasi(String a) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select Count(*) as jml from jual where NoFaktur = '" + a + "'");
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
                    "delete from jual where NoFaktur = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual(NoFaktur,Tanggal,KodeLgn) values (?,?,?)");
            con.preparedStatement.setString(1, getJualModel().getNofaktur());
            con.preparedStatement.setDate(2, getJualModel().getTanggal());
            con.preparedStatement.setString(3, getJualModel().getKodelgn());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update jual set Tanggal = ?, KodeLgn = ? where NoFaktur = ?");
            con.preparedStatement.setDate(1, getJualModel().getTanggal());
            con.preparedStatement.setString(2, getJualModel().getKodelgn());
            con.preparedStatement.setString(3, getJualModel().getNofaktur());
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
