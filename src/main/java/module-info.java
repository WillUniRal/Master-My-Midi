module uk.ac.bucks.willralph.mmmidi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires javafx.graphics;
    requires com.github.kwhat.jnativehook;
    requires com.fazecast.jSerialComm;

    //requires org.controlsfx.controls;
    //requires org.kordamp.ikonli.javafx;
    //requires eu.hansolo.tilesfx;

    opens uk.ac.bucks.willralph.mmmidi to javafx.fxml;
    exports uk.ac.bucks.willralph.mmmidi;
    exports uk.ac.bucks.willralph.mmmidi.user;
    exports uk.ac.bucks.willralph.picom;
}