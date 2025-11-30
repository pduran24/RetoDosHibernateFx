package org.example.retodos;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.retodos.util.JavaFXUtil;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        JavaFXUtil.initStage(stage);
        JavaFXUtil.setScene("/org/example/retodos/login-view.fxml");
    }
}
