package org.example.retodos.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.retodos.models.Copia;
import org.example.retodos.models.Pelicula;
import org.example.retodos.models.Rol;
import org.example.retodos.models.Usuario;
import org.example.retodos.repository.CopiaRepository;
import org.example.retodos.repository.PeliculaRepository;
import org.example.retodos.util.JavaFXUtil;
import org.example.retodos.util.Session;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @javafx.fxml.FXML
    private Label lblUsuario;
    @javafx.fxml.FXML
    private TableColumn<Object, String> c_titulo;
    @javafx.fxml.FXML
    private TableView<Object> tablaPeliculas;
    @javafx.fxml.FXML
    private TableColumn<Object,String> c_soporte;
    @javafx.fxml.FXML
    private TableColumn<Object, String> c_estado;

    @javafx.fxml.FXML
    private Button btnCerrarSesion;
    @javafx.fxml.FXML
    private Button btnCerrar;


    private CopiaRepository copiaRepository;
    private PeliculaRepository peliculaRepository;
    private Usuario usuarioLogueado;
    @javafx.fxml.FXML
    private Button btnEliminar;
    @javafx.fxml.FXML
    private Button btnDetalle;
    @javafx.fxml.FXML
    private Button btnAniadir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        copiaRepository = new CopiaRepository();
        peliculaRepository = new PeliculaRepository();
        usuarioLogueado = Session.getInstance().getUsuarioLogeado();

        if (usuarioLogueado != null) {
            lblUsuario.setText("Usuario: " + usuarioLogueado.getNombreUsuario());
        }

        if (usuarioLogueado.getRol() == Rol.ADMIN) {
            configurarTablaAdmin();
            cargarDatosAdmin();
            btnAniadir.setOnAction(event -> onAniadirPeliculaClick());

            btnEliminar.setOnAction(event -> onEliminarPeliculaClick());

        } else {
            configurarTablaUsuario();
            cargarDatosUsuario();
            btnAniadir.setOnAction(event -> onAniadirCopiaClick());

            btnEliminar.setOnAction(event -> onEliminarCopiaClick());
        }

        btnCerrarSesion.setOnAction(event -> onCerrarSesionClick());
        btnCerrar.setOnAction(event -> System.exit(0));
        btnDetalle.setOnAction(event -> onDetalleClick());
    }

    private void configurarTablaUsuario() {
        c_titulo.setText("Título Película");
        c_estado.setText("Estado");
        c_soporte.setText("Soporte");

        c_titulo.setCellValueFactory(cellData -> {
            Copia c = (Copia) cellData.getValue();
            return new SimpleStringProperty(c.getPelicula().getTitulo());
        });

        c_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        c_soporte.setCellValueFactory(new PropertyValueFactory<>("soporte"));
    }

    private void cargarDatosUsuario() {
        List<Copia> copias = copiaRepository.findByUsuarioId(usuarioLogueado.getId());
        tablaPeliculas.setItems(FXCollections.observableArrayList((List)copias));
    }

    private void configurarTablaAdmin() {
        c_titulo.setText("Título");
        c_estado.setText("Director");
        c_soporte.setText("Género");

        c_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        c_estado.setCellValueFactory(new PropertyValueFactory<>("director"));
        c_soporte.setCellValueFactory(new PropertyValueFactory<>("genero"));
    }

    private void cargarDatosAdmin() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        tablaPeliculas.setItems(FXCollections.observableArrayList((List)peliculas));
    }

    private void onAniadirCopiaClick() {
        abrirModal("/org/example/retodos/add-copy-view.fxml", "Añadir Copia");
        cargarDatosUsuario();
    }

    private void onAniadirPeliculaClick() {
        abrirModal("/org/example/retodos/add-movie-view.fxml", "Nueva Película");
        cargarDatosAdmin();
    }

    private void abrirModal(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage modalStage = new Stage();
            modalStage.setTitle(titulo);
            modalStage.setScene(new Scene(root));
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(btnAniadir.getScene().getWindow());
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            JavaFXUtil.showModal(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana", e.getMessage());
        }
    }

    private void onEliminarCopiaClick() {
        Object seleccion = tablaPeliculas.getSelectionModel().getSelectedItem();

        // Validación básica por si no hay nada seleccionado
        if (seleccion == null) {
            JavaFXUtil.showModal(Alert.AlertType.WARNING, "Aviso", "Nada seleccionado", "Por favor, selecciona una copia de la lista.");
            return;
        }

        Copia copia = (Copia) seleccion;

        // --- CORRECCIÓN PROBLEMA #5: Confirmación antes de borrar ---
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText("¿Eliminar copia de '" + copia.getPelicula().getTitulo() + "'?");
        alert.setContentText("¿Estás seguro de que quieres eliminar esta copia de tu colección? Esta acción no se puede deshacer.");

        // Solo borramos si el usuario pulsa OK
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                copiaRepository.delete(copia);
                tablaPeliculas.getItems().remove(copia);
                // Feedback opcional de éxito
                JavaFXUtil.showModal(Alert.AlertType.INFORMATION, "Eliminado", "Copia eliminada", "La copia ha sido eliminada correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                JavaFXUtil.showModal(Alert.AlertType.ERROR, "Error", "Fallo al eliminar", "No se pudo eliminar la copia de la base de datos.");
            }
        }
    }

    private void onEliminarPeliculaClick() {
        Object seleccion = tablaPeliculas.getSelectionModel().getSelectedItem();
        if (seleccion == null) return;

        Pelicula peli = (Pelicula) seleccion;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("¿Borrar película '" + peli.getTitulo() + "'?");
        alert.setContentText("Esto borrará también TODAS las copias de todos los usuarios.");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                peliculaRepository.delete(peli);
                tablaPeliculas.getItems().remove(peli);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void onCerrarSesionClick() {
        Session.getInstance().setUsuarioLogeado(null);
        JavaFXUtil.setScene("/org/example/retodos/login-view.fxml");
    }

    private void onDetalleClick() {
        Object seleccion = tablaPeliculas.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            JavaFXUtil.showModal(Alert.AlertType.WARNING, "Aviso", "Nada seleccionado", "Selecciona una fila para ver el detalle.");
            return;
        }

        Pelicula peliculaAMostrar = null;


        if (seleccion instanceof Pelicula) {
            peliculaAMostrar = (Pelicula) seleccion;
        }
        else if (seleccion instanceof Copia) {
            peliculaAMostrar = ((Copia) seleccion).getPelicula();
        }

        if (peliculaAMostrar != null) {
            abrirVentanaDetalle(peliculaAMostrar);
        }
    }

    private void abrirVentanaDetalle(Pelicula pelicula) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/retodos/detail-view.fxml"));
            Parent root = loader.load();

            DetailController controller = loader.getController();
            controller.setPelicula(pelicula);

            Stage stage = new Stage();
            stage.setTitle("Detalle de Película");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnDetalle.getScene().getWindow());
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            JavaFXUtil.showModal(Alert.AlertType.ERROR, "Error", "No se pudo abrir el detalle", e.getMessage());
        }
    }
}
