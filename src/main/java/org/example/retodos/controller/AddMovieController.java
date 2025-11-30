package org.example.retodos.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.retodos.models.Pelicula;
import org.example.retodos.repository.PeliculaRepository;
import org.example.retodos.util.JavaFXUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {
    @javafx.fxml.FXML
    private TextField txtDirector;
    @javafx.fxml.FXML
    private TextField txtAnio;
    @javafx.fxml.FXML
    private TextArea txtDescripcion;
    @javafx.fxml.FXML
    private TextField txtTitulo;
    @javafx.fxml.FXML
    private TextField txtGenero;

    private PeliculaRepository peliculaRepository;
    @javafx.fxml.FXML
    private Button btnGuardar;
    @javafx.fxml.FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        peliculaRepository = new PeliculaRepository();
        btnGuardar.setOnAction(event -> onGuardar());
        btnCancelar.setOnAction(event -> onCancelar());
    }

    private void onGuardar() {
        if (txtTitulo.getText().isEmpty() || txtDirector.getText().isEmpty() || txtAnio.getText().isEmpty()) {
            JavaFXUtil.showModal(Alert.AlertType.WARNING, "Aviso", "Campos vacíos", "Título, Director y Año son obligatorios.");
            return;
        }

        try {
            Pelicula p = new Pelicula();
            p.setTitulo(txtTitulo.getText());
            p.setGenero(txtGenero.getText());
            p.setDirector(txtDirector.getText());
            p.setDescripcion(txtDescripcion.getText());

            try {
                p.setAnio(Integer.parseInt(txtAnio.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtil.showModal(Alert.AlertType.ERROR, "Error", "Formato inválido", "El año debe ser un número entero.");
                return;
            }

            peliculaRepository.save(p);

            JavaFXUtil.showModal(Alert.AlertType.INFORMATION, "Éxito", "Película Registrada", "La película se ha añadido al catálogo global.");

            Stage myStage = (Stage) txtTitulo.getScene().getWindow();
            myStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            JavaFXUtil.showModal(Alert.AlertType.ERROR, "Error", "Fallo al guardar", e.getMessage());
        }
    }

    private void onCancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}



