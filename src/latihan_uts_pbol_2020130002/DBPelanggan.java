/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
            ResultSet rs
                    = con.statement.executeQuery("Select * from pelanggan");
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

    public int validasi(String a) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select Count(*) as jml from pelanggan where KodeLgn = '" + a + "'");
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
                    "delete from pelanggan where KodeLgn = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pelanggan(KodeLgn,NamaLgn,Alamat) values (?,?,?)");
            con.preparedStatement.setString(1, getPelangganModel().getKodelgn());
            con.preparedStatement.setString(2, getPelangganModel().getNamalgn());
            con.preparedStatement.setString(3, getPelangganModel().getAlamat());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pelanggan set NamaLgn = ?, Alamat = ? where KodeLgn = ?");
            con.preparedStatement.setString(1, getPelangganModel().getNamalgn());
            con.preparedStatement.setString(2, getPelangganModel().getAlamat());
            con.preparedStatement.setString(3, getPelangganModel().getKodelgn());
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

    public ObservableList<PelangganModel> LookUp(String dt) {
        try {
            ObservableList<PelangganModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from pelanggan where NamaLgn like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                PelangganModel d = new PelangganModel();
                d.setKodelgn(rs.getString("KodeLgn"));
                d.setNamalgn(rs.getString("NamaLgn"));
                d.setAlamat(rs.getString("Alamat"));
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

    public void print() {
        Koneksi con = new Koneksi();
        String is = "./src/latihan_uts_pbol_2020130002/LaporanPelanggan.jasper";
        Map map = new HashMap();
        map.put("p_periode", "Desember");
        con.bukaKoneksi();
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(is, map, con.dbKoneksi);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        con.tutupKoneksi();
    }
   
}
