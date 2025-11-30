module org.example.retodos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens org.example.retodos to javafx.fxml;
    opens org.example.retodos.models to org.hibernate.orm.core;

    exports org.example.retodos;
}