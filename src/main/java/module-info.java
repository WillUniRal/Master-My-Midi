module uk.ac.bucks.willralph.mmmidi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    //requires org.controlsfx.controls;
    //requires org.kordamp.ikonli.javafx;
    //requires eu.hansolo.tilesfx;

    opens uk.ac.bucks.willralph.mmmidi to javafx.fxml;
    exports uk.ac.bucks.willralph.mmmidi;
    exports uk.ac.bucks.willralph.mmmidi.old;
    opens uk.ac.bucks.willralph.mmmidi.old to javafx.fxml;
}