module org.example.retodos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    // API de Persistencia (Javax)
    requires java.persistence;

    // --- ESTA ES LA LÍNEA QUE TE FALTA ---
    requires java.sql;
    // -------------------------------------

    opens org.example.retodos to javafx.fxml;

    opens org.example.retodos.models;
    exports org.example.retodos;
    exports org.example.retodos.controller;
    opens org.example.retodos.controller to javafx.fxml;
}