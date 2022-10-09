/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_InputJualController implements Initializable {

    @FXML
    private TextField txtkodelgn;
    @FXML
    private TextField txtnofaktur;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnexit;
    @FXML
    private DatePicker datetanggal;
    @FXML
    private Button btnlookupkodepelanggan;

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
        JualModel s = new JualModel();
        s.setNofaktur(txtnofaktur.getText());
        s.setKodelgn(txtkodelgn.getText());
        s.setTanggal(Date.valueOf(datetanggal.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        FXMLDocumentController.dtjual.setJualModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtjual.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnexitklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtjual.validasi(s.getNofaktur()) <= 0) {
            if (FXMLDocumentController.dtjual.insert()) {
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
            txtnofaktur.requestFocus();
        }
    }

    @FXML
    private void btnresetklik(ActionEvent event) {
        txtnofaktur.setText("");
        txtkodelgn.setText("");
        datetanggal.setValue(null);
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    public void execute(JualModel s) {
        if (!s.getNofaktur().isEmpty()) {
            editdata = true;
            txtnofaktur.setText(s.getNofaktur());
            txtkodelgn.setText(s.getKodelgn());
            datetanggal.setValue(s.getTanggal().toLocalDate());
            txtnofaktur.setEditable(false);
            btnreset.setDisable(true);
            txtnofaktur.requestFocus();
        }
    }

    @FXML
    private void btnlookupkodepelangganclick(ActionEvent event) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
