module org.example.retodos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jdk.compiler;
    requires java.desktop;


    opens org.example.retodos to javafx.fxml;
    opens org.example.retodos.models to org.hibernate.orm.core, javafx.base;

    exports org.example.retodos;
    exports org.example.retodos.controller;
    opens org.example.retodos.controller to javafx.fxml;
}