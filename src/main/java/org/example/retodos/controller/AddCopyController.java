package org.example.retodos.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.example.retodos.models.Copia;
import org.example.retodos.models.Pelicula;
import org.example.retodos.models.Usuario;
import org.example.retodos.repository.CopiaRepository;
import org.example.retodos.repository.PeliculaRepository;
import org.example.retodos.util.JavaFXUtil;
import org.example.retodos.util.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCopyController implements Initializable {


    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox<Pelicula> comboPelicula;
    @FXML
    private ComboBox<String> comboEstado;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<String> comboSoporte;


    private final PeliculaRepository peliculaRepository = new PeliculaRepository();
    private final CopiaRepository copiaRepository = new CopiaRepository();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarComboPeliculas();
        cargarDatosCombo();

        btnGuardar.setOnAction(event -> guardarCopia());
        btnCancelar.setOnAction(event -> cerrarVentana());
    }

    private void cargarDatosCombo() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        comboPelicula.setItems(FXCollections.observableArrayList(peliculas));

        comboEstado.setItems(FXCollections.observableArrayList("Bueno", "Regular", "Dañado"));
        comboSoporte.setItems(FXCollections.observableArrayList("DVD", "Blu-ray", "VHS", "Digital"));
    }

    private void guardarCopia() {
        Pelicula peliSeleccionada = comboPelicula.getValue();
        String estado = comboEstado.getValue();
        String soporte = comboSoporte.getValue();
        Usuario usuario = Session.getInstance().getUsuarioLogeado();

        if (peliSeleccionada == null || estado == null || soporte == null) {
            JavaFXUtil.showModal(Alert.AlertType.WARNING, "Datos incompletos", "Faltan campos", "Por favor selecciona película, estado y soporte.");
            return;
        }

        try {
            Copia nuevaCopia = new Copia();
            nuevaCopia.setPelicula(peliSeleccionada);
            nuevaCopia.setUsuario(usuario);
            nuevaCopia.setEstado(estado);
            nuevaCopia.setSoporte(soporte);

            copiaRepository.save(nuevaCopia);

            System.out.println("Copia guardada exitosamente");
            cerrarVentana();

        } catch (Exception e) {
            e.printStackTrace();
            JavaFXUtil.showModal(Alert.AlertType.ERROR, "Error", "Fallo al guardar", "No se pudo registrar la copia.");
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void configurarComboPeliculas() {
        comboPelicula.setConverter(new StringConverter<Pelicula>() {
            @Override
            public String toString(Pelicula pelicula) {
                return (pelicula != null) ? pelicula.getTitulo() : null;
            }

            @Override
            public Pelicula fromString(String string) {
                return null;
            }
        });
    }

}
