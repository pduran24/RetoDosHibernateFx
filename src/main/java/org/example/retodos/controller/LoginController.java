package org.example.retodos.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.retodos.models.Usuario;
import org.example.retodos.repository.UsuarioRepository;
import org.example.retodos.util.Auth;
import org.example.retodos.util.JavaFXUtil;
import org.example.retodos.util.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @javafx.fxml.FXML
    private Button btnCancelar;
    @javafx.fxml.FXML
    private Button btnEntrar;
    @javafx.fxml.FXML
    private TextField txtUsuario;

    private UsuarioRepository usuarioRepository;
    private Auth auth;
    @javafx.fxml.FXML
    private PasswordField txtContrasenia;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioRepository = new UsuarioRepository();
        auth = new Auth(usuarioRepository);

        btnEntrar.setOnAction(event -> onLoginClick());
        btnCancelar.setOnAction(event -> System.exit(0));
    }


    private void onLoginClick() {
        String usuario = txtUsuario.getText();
        String contrasenia = txtContrasenia.getText();

        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            mostrarAlerta("Error", "Por favor, introduce usuario y contraseña.");
            return;
        }

        Usuario u = auth.validarUsuario(usuario, contrasenia).orElse(null);
        if (u != null) {
            Session.getInstance().setUsuarioLogeado(u);
            JavaFXUtil.setScene("/org/example/retodos/main-view.fxml");
        } else
            mostrarAlerta("Error", "Usuario o contraseña incorrectos.");
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
