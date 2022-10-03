/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package latihan_uts_pbol_2020130002;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Yosef Adrian
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private MenuItem masterbarang;
    @FXML
    private MenuItem masterjual;
    @FXML
    private MenuItem masterpelanggan;
    @FXML
    private MenuItem transaksijual;
    @FXML
    private MenuItem displaybarang;
    @FXML
    private MenuItem displayjual;
    @FXML
    private MenuItem displaypelanggan;
    @FXML
    private MenuItem displaysubjual;
    @FXML
    private MenuItem exit;

    public static DBPelanggan dtpelanggan = new DBPelanggan();
    public static DBBarang dtbarang = new DBBarang();
    public static DBSubjual dtsubjual = new DBSubjual();
    public static DBJual dtjual = new DBJual();
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void masterbarangclick(ActionEvent event) {

    }

    @FXML
    private void masterjualclick(ActionEvent event) {
    }

    @FXML
    private void masterpelangganclick(ActionEvent event) {
    }

    @FXML
    private void transaksijualclick(ActionEvent event) {
    }

    @FXML
    private void displaybarangclick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DisplayBarang.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayjualclick(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DisplayJual.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displaypelangganclick(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DisplayPelanggan.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displaysubjualclick(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DisplaySubJual.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitclick(ActionEvent event) {
        System.exit(0);
    }

}
