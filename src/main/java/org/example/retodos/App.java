package org.example.retodos;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.retodos.util.DataSeeder; // Importa tu nueva clase
import org.example.retodos.util.JavaFXUtil;
import org.example.retodos.util.JPAUtil;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // 1. Inicializar BD y crear datos si está vacía
        DataSeeder.seed();

        // 2. Iniciar JavaFX
        JavaFXUtil.initStage(stage);
        JavaFXUtil.setScene("/org/example/retodos/login-view.fxml");
    }

    @Override
    public void stop() throws Exception {
        // Buena práctica: Cerrar la conexión JPA al cerrar la ventana
        JPAUtil.shutdown();
        super.stop();
    }
}