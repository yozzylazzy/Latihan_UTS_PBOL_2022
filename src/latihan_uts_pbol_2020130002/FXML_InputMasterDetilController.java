/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_InputMasterDetilController implements Initializable {

    @FXML
    private DatePicker datetanggal;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtnofaktur;
    @FXML
    private TextField txtkodelgn;
    @FXML
    private TextField txtkodebrg;
    @FXML
    private TextField txtjumlah;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnclear;
    @FXML
    private TableView<SubJualModel> tbvdetil;

    private boolean editmode = false;
    public static DBJual data = new DBJual(); //Masukan ke FXML_DocumentController agar dapat dibuat static dan dipakai dimana2
    @FXML
    private Button btnloadbarang;
    @FXML
    private Button btnloadpelanggan;

    String namalgn, alamat;
    int harga;
    String namabrg;
    @FXML
    private TextField txtnamapelanggan;
    @FXML
    private TextArea txtalamatlgn;
    @FXML
    private TextField txtnamabrg;
    @FXML
    private TextField txthargabrg;
    @FXML
    private TextField txttotalbayar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data.getSubJualModel().clear();

        //Untuk mengambil tanggal hari ini otomatis
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        datetanggal.setValue(LocalDate.parse(formatter.format(LocalDate.now()), formatter));
        showData();
    }

    public void showData() {
        //Melaod data sesuai database
        tbvdetil.getColumns().clear();
        tbvdetil.getItems().clear();
        TableColumn col = new TableColumn("Nofaktur");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Nofaktur"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Kode Barang");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Kodebrg"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Jumlah");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, Integer>("Jumlah"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Nama Barang");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Namabrg"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Harga Barang");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, Integer>("Harga"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("Bayar");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, Integer>("Total"));
        tbvdetil.getColumns().addAll(col);

    }

    private void hitungTotal() {
        int total = 0;
        for (int i = 0; i < tbvdetil.getItems().size(); i++) {
            SubJualModel n = tbvdetil.getItems().get(i);

            System.out.println(i);
            System.out.println(n);
            System.out.println(n.getTotal());
            total += n.getTotal();
        }
        txttotalbayar.setText(String.valueOf(total));
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void btnresetklik(ActionEvent event) {
        btnclearklik(event);
        txtnofaktur.setText("");
        txtkodelgn.setText("");
        txtalamatlgn.setText("");
        txtnamapelanggan.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        datetanggal.setValue(LocalDate.parse(formatter.format(LocalDate.now()), formatter));
        tbvdetil.getItems().clear();
        data.getSubJualModel().clear();
        txtnofaktur.setEditable(true);
        editmode = false;
        txtnofaktur.requestFocus();
    }

    @FXML
    private void btnsimpanklik(ActionEvent event) {
        data.getJualModel().setNofaktur(txtnofaktur.getText());
        data.getJualModel().setAlamat(txtalamatlgn.getText());
        data.getJualModel().setNamalgn(txtnamapelanggan.getText());
        data.getJualModel().setTanggal(Date.valueOf(datetanggal.getValue().
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        data.getJualModel().setKodelgn(txtkodelgn.getText());
        if (data.saveall()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan ", ButtonType.OK);
            a.showAndWait();
            btnresetklik(event);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan ", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void nofakturcek(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            data.getJualModel().setNofaktur(txtnofaktur.getText());
            if (data.validasi(txtnofaktur.getText()) >= 1) {
                JualModel tmp = data.getdata(txtnofaktur.getText());
                if (tmp.getTanggal() != null) {
                    datetanggal.setValue(tmp.getTanggal().toLocalDate());
                    txtkodelgn.setText(tmp.getKodelgn());
                    txtnamapelanggan.setText(tmp.getNamalgn());
                    txtalamatlgn.setText(tmp.getAlamat());
                    ObservableList<SubJualModel> dt = data.LoadDetil();
                    if (dt != null) {
                        tbvdetil.setItems(dt);
                        hitungTotal();
                    }
                }
                txtnofaktur.setEditable(false);
                editmode = true;
            }
        }
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
        SubJualModel tmp = new SubJualModel();
        tmp.setNofaktur(txtnofaktur.getText());
        tmp.setKodebrg(txtkodebrg.getText());
        tmp.setNamabrg(txtnamabrg.getText());
        tmp.setHarga(Integer.parseInt(txthargabrg.getText()));
        tmp.setJumlah(Integer.parseInt(txtjumlah.getText()));
        if (data.getSubJualModel().get(txtkodebrg.getText()) == null) {
            data.setSubJualModel(tmp);
            tbvdetil.getItems().add(tmp);
        } else {
            int p = -1;
            for (int i = 0; i < tbvdetil.getItems().size(); i++) {
                if (tbvdetil.getItems().get(i).getKodebrg().equalsIgnoreCase(
                        txtkodebrg.getText())) {
                    p = i;
                }
            }
            if (p >= 0) {
                tbvdetil.getItems().set(p, tmp);
                data.getSubJualModel().remove(txtkodebrg.getText());
                data.setSubJualModel(tmp);
            }

        }
        hitungTotal();
        btnclearklik(event);
    }

    @FXML
    private void btnhapusklik(ActionEvent event) {
        SubJualModel tmp = tbvdetil.getSelectionModel().getSelectedItem();
        if (tmp != null) {
            tbvdetil.getItems().remove(tmp);
            data.getSubJualModel().remove(tmp.getKodebrg());
            btnclearklik(event);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Pilih data dulu", ButtonType.OK);
            a.showAndWait();
            tbvdetil.requestFocus();
        }
    }

    @FXML
    private void tbvdetilklik(MouseEvent event) {
        SubJualModel tmp = tbvdetil.getSelectionModel().getSelectedItem();
        if (tmp != null) {
            txtkodebrg.setText(tmp.getKodebrg());
            txtnamabrg.setText(tmp.getNamabrg());
            txthargabrg.setText(String.valueOf(tmp.getHarga()));
            txtjumlah.setText(String.valueOf(tmp.getJumlah()));
            int total = 0;
            for (int i = 0; i < tbvdetil.getItems().size(); i++) {
                SubJualModel n = tbvdetil.getItems().get(i);

                System.out.println(i);
                System.out.println(n);
                System.out.println(n.getTotal());
                total += n.getTotal();
            }
            txttotalbayar.setText(String.valueOf(total));
        }
    }

    @FXML
    private void btnclearklik(ActionEvent event) {
        txtkodebrg.setText("");
        txthargabrg.setText("");
        txtnamabrg.setText("");
        txtjumlah.setText("");
        txtkodebrg.requestFocus();
    }

    @FXML
    private void btnloadbarangklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_PilihBarang.fxml"));
            Parent root = (Parent) loader.load();
            FXML_PilihBarangController isidt = (FXML_PilihBarangController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtkodebrg.setText(isidt.getKodeBarang());
                harga = isidt.getHargaBarang();
                txthargabrg.setText(String.valueOf(harga));
                namabrg = isidt.getNamaBarang();
                txtnamabrg.setText(namabrg);
                //data.getSubJualModel().get(txtkodebrg.getText()).setJumlah(isidt.getJumlah());
                //data.getSubJualModel().get(txtkodebrg.getText()).set(isidt.get());
                //data.getSubJualModel().get(txtkodebrg.getText()).setHarga(isidt.getHargaBarang());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnloadpelangganklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_PilihPelanggan.fxml"));
            Parent root = (Parent) loader.load();
            FXML_PilihPelangganController isidt = (FXML_PilihPelangganController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtkodelgn.setText(isidt.getKodeHasil());
                namalgn = isidt.getNamaLgn();
                txtnamapelanggan.setText(namalgn);
                alamat = isidt.getAlamatLgn();
                txtalamatlgn.setText(alamat);
                //data.getJualModel().set
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(JualModel d) {
        if (!d.getNofaktur().isEmpty()) {
            FXML_InputMasterDetilController.data.getJualModel().setNofaktur(d.getNofaktur());
            if (FXML_InputMasterDetilController.data.validasi(d.getNofaktur()) >= 1) {
                JualModel tmp = FXML_InputMasterDetilController.data.getdata(d.getNofaktur());
                editmode = true;
                FXML_InputMasterDetilController.data.setJualModel(d);
                txtnofaktur.setText(d.getNofaktur());
                if (d.getTanggal() != null) {
                    datetanggal.setValue(d.getTanggal().toLocalDate());
                }
                txtkodelgn.setText(d.getKodelgn());
                txtnamapelanggan.setText(d.getNamalgn());
                txtalamatlgn.setText(d.getAlamat());
                ObservableList<SubJualModel> data
                        = FXML_InputMasterDetilController.data.LoadDetil();
                if (data != null) {
                    tbvdetil.setItems(data);
                    hitungTotal();
                }
                txtnofaktur.setEditable(false);
                txtkodelgn.requestFocus();
            }
        }
    }
}
