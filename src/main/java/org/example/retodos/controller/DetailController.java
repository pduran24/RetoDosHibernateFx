package org.example.retodos.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.example.retodos.models.Pelicula;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailController implements Initializable {

    @FXML private Label lblTitulo;
    @FXML private Label lblDirector;
    @FXML private Label lblGenero;
    @FXML private Label lblAnio;
    @FXML private Button btnCerrar;
    @FXML
    private Label lblSinopsis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCerrar.setOnAction(event -> {
            Stage stage = (Stage) btnCerrar.getScene().getWindow();
            stage.close();
        });
    }

    public void setPelicula(Pelicula pelicula) {
        if (pelicula != null) {
            lblTitulo.setText(pelicula.getTitulo());
            lblDirector.setText("Director: " + pelicula.getDirector());
            lblGenero.setText("Género: " + pelicula.getGenero());
            lblAnio.setText("Año: " + pelicula.getAnio());
            // --- CORRECCIÓN PROBLEMA #6: Cursor Visual ---
            lblSinopsis.setText(("Sinopsis: " + pelicula.getDescripcion()));
        }
    }
}