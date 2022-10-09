/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_InputBarangController implements Initializable {

    @FXML
    private TextField txtharga;
    @FXML
    private TextField txtkodebrg;
    @FXML
    private TextField txtnamabrg;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnexit;

    private boolean editdata = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnsimpanklik(ActionEvent event) {
        BarangModel s = new BarangModel();
        s.setKodebrg(txtkodebrg.getText());
        s.setNamabrg(txtnamabrg.getText());
        s.setHarga(Integer.parseInt(txtharga.getText()));
        FXMLDocumentController.dtbarang.setBarangModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtbarang.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnexitklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtbarang.validasi(s.getKodebrg()) <= 0) {
            if (FXMLDocumentController.dtbarang.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnresetklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtkodebrg.requestFocus();
        }
    }

    @FXML
    private void btnresetklik(ActionEvent event) {
        txtkodebrg.setText("");
        txtnamabrg.setText("");
        txtharga.setText("");
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    public void execute(BarangModel s) {
        if (!s.getKodebrg().isEmpty()) {
            editdata = true;
            txtkodebrg.setText(s.getKodebrg());
            txtnamabrg.setText(s.getNamabrg());
            txtharga.setText(String.valueOf(s.getHarga()));
            txtkodebrg.setEditable(false);
            btnreset.setDisable(true);
            txtnamabrg.requestFocus();
        }
    }
}
