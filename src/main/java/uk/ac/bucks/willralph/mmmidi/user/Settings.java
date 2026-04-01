package uk.ac.bucks.willralph.mmmidi.user;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import uk.ac.bucks.willralph.mmmidi.App;
import uk.ac.bucks.willralph.mmmidi.MidiConnection;

import javax.sound.midi.MidiDevice;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Settings extends Page {
    FXMLLoader loader;

    public Settings() {
        super(Type.SETTINGS);
    }

    @Override
    protected Parent setLayout() {
        URL resource = getClass().getClassLoader().getResource("settings.fxml");
        return getFXML(resource);
    }

    private Parent getFXML(URL fxml) {
        try {
            loader = new FXMLLoader(fxml);
            loader.setController(this);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public TitledPane inputs;
    @FXML public void getInputs() {
        ArrayList<MidiDevice.Info> devices = MidiConnection.inputDevices;
        VBox dropDown = (VBox) inputs.getContent();

        var children = dropDown.getChildren();
        children.clear();

        for (MidiDevice.Info device : devices) {
            System.out.println(device.getName());
            Label deviceOption = new Label(device.getName());

            deviceOption.setOnMousePressed(new DeviceSelectEvent(device));

            children.add(deviceOption);
        }
        System.out.println(inputs.isExpanded());
        inputs.setExpanded(!inputs.isExpanded());

    }
    @FXML public MenuItem goBack;
    @FXML public void back() {
        App.changeLayout(Type.PLAYER);
        App.setCurrentScene();
    }

}
