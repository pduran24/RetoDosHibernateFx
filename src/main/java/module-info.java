module org.example.retodos {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.retodos to javafx.fxml;
    exports org.example.retodos;
}