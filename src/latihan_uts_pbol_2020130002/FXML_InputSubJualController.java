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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_InputSubJualController implements Initializable {

    @FXML
    private TextField txtkodebrg;
    @FXML
    private TextField txtnofaktur;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnexit;
    @FXML
    private Slider sldjumlah;
    @FXML
    private Label lbljumlah;
    @FXML
    private Button btnlookupkodebarang;

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
        SubJualModel s = new SubJualModel();
        s.setNofaktur(txtnofaktur.getText());
        s.setKodebrg(txtkodebrg.getText());
        s.setJumlah((int) sldjumlah.getValue());
        FXMLDocumentController.dtsubjual.setSubJualModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtsubjual.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnexitklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtsubjual.validasi(s.getNofaktur(), s.getKodebrg()) <= 0) {
            if (FXMLDocumentController.dtsubjual.insert()) {
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
        txtkodebrg.setText("");
        sldjumlah.setValue(0);
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    public void execute(SubJualModel s) {
        if (!s.getNofaktur().isEmpty() && !s.getKodebrg().isEmpty()) {
            editdata = true;
            txtnofaktur.setText(s.getNofaktur());
            txtkodebrg.setText(s.getKodebrg());
            sldjumlah.setValue(s.getJumlah());
            txtnofaktur.setEditable(false);
            txtkodebrg.setEditable(false);
            btnreset.setDisable(true);
            txtnofaktur.requestFocus();
        }
    }

    @FXML
    private void btnlookupkodebarangclick(ActionEvent event) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
