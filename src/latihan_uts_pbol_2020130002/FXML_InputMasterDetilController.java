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
    private DBJual data = new DBJual(); //Masukan ke FXML_DocumentController agar dapat dibuat static dan dipakai dimana2

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

        //Melaod data sesuai database
        tbvdetil.getColumns().clear();
        tbvdetil.getItems().clear();
        TableColumn col = new TableColumn("nofaktur");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Nofaktur"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("kode barang");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, String>("Kodebrg"));
        tbvdetil.getColumns().addAll(col);
        col = new TableColumn("jumlah");
        col.setCellValueFactory(new PropertyValueFactory<SubJualModel, Integer>("Jumlah"));
        tbvdetil.getColumns().addAll(col);
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
                    ObservableList<SubJualModel> dt = data.LoadDetil();
                    if (dt != null) {
                        tbvdetil.setItems(dt);
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
            }
            data.getSubJualModel().remove(txtkodebrg.getText());
            data.setSubJualModel(tmp);
        }
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
            txtjumlah.setText(String.valueOf(tmp.getJumlah()));
        }
    }

    @FXML
    private void btnclearklik(ActionEvent event) {
        txtkodebrg.setText("");
        txtjumlah.setText("");
        txtkodebrg.requestFocus();
    }

}
