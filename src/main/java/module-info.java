module org.example.retodos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;


    opens org.example.retodos to javafx.fxml;
    exports org.example.retodos;
}