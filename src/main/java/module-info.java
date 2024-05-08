module com.pbo {
    requires transitive javafx.graphics;
    requires transitive java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires javafx.base;

    opens com.pbo.controller to javafx.fxml;
    exports com.pbo;
    exports com.pbo.controller;
    exports com.pbo.data;
}
