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
public class FXML_InputPelangganController implements Initializable {

    @FXML
    private Button btnexit;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtkodelgn;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txtnama;

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
        PelangganModel s = new PelangganModel();
        s.setKodelgn(txtkodelgn.getText());
        s.setNamalgn(txtnama.getText());
        s.setAlamat(txtalamat.getText());
        FXMLDocumentController.dtpelanggan.setPelangganModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtpelanggan.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnexitklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtpelanggan.validasi(s.getKodelgn()) <= 0) {
            if (FXMLDocumentController.dtpelanggan.insert()) {
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
            txtkodelgn.requestFocus();
        }
    }

    @FXML
    private void btnresetklik(ActionEvent event) {
        txtkodelgn.setText("");
        txtnama.setText("");
        txtalamat.setText("");
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    public void execute(PelangganModel s) {
        if (!s.getKodelgn().isEmpty()) {
            editdata = true;
            txtkodelgn.setText(s.getKodelgn());
            txtnama.setText(s.getNamalgn());
            txtalamat.setText(s.getAlamat());
            txtkodelgn.setEditable(false);
            btnreset.setDisable(true);
            txtnama.requestFocus();
        }
    }

}
