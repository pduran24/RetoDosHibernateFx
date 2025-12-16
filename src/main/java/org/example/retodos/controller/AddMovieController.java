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
        // --- INICIO CORRECCIÓN PROBLEMA #7: Mensajes de error específicos ---
        java.util.List<String> camposFaltantes = new java.util.ArrayList<>();

        if (txtTitulo.getText().trim().isEmpty()) {
            camposFaltantes.add("Título");
        }
        if (txtDirector.getText().trim().isEmpty()) {
            camposFaltantes.add("Director");
        }
        if (txtAnio.getText().trim().isEmpty()) {
            camposFaltantes.add("Año");
        }

        if (!camposFaltantes.isEmpty()) {
            String mensaje = "Por favor, rellena los siguientes campos obligatorios:\n- "
                    + String.join("\n- ", camposFaltantes);
            JavaFXUtil.showModal(Alert.AlertType.WARNING, "Campos incompletos", "Faltan datos", mensaje);
            return; // Detenemos la ejecución
        }
        // --- FIN CORRECCIÓN ---

        try {
            Pelicula p = new Pelicula();
            p.setTitulo(txtTitulo.getText());
            p.setGenero(txtGenero.getText());
            p.setDirector(txtDirector.getText());
            p.setDescripcion(txtDescripcion.getText());

            // INICIO CORRECCIÓN PROBLEMA #3
            try {
                int anioInput = Integer.parseInt(txtAnio.getText());
                int anioActual = java.time.Year.now().getValue();

                // Validación de rango lógico (1888 - Actualidad)
                if (anioInput < 1888 || anioInput > anioActual) {
                    JavaFXUtil.showModal(Alert.AlertType.ERROR, "Error", "Año inválido",
                            "El año debe ser un número entre 1888 y " + anioActual + ".");
                    return;
                }

                p.setAnio(anioInput);

            } catch (NumberFormatException e) {
                JavaFXUtil.showModal(Alert.AlertType.ERROR, "Error", "Formato inválido", "El año debe ser un número entero válido.");
                return;
            }
            // FIN CORRECCIÓN PROBLEMA #3

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



