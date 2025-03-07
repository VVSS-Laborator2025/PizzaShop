module pizzashop {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires org.slf4j;

    opens pizzashop.model to javafx.base;
    exports pizzashop.model;
    opens pizzashop to javafx.fxml;
    exports pizzashop;
    opens pizzashop.controller to javafx.fxml;
    exports pizzashop.controller;
}
