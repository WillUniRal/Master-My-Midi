module uk.ac.bucks.willralph.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens uk.ac.bucks.willralph.demo to javafx.fxml;
    exports uk.ac.bucks.willralph.demo;
}