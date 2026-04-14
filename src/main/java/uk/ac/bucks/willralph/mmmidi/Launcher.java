package uk.ac.bucks.willralph.mmmidi;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.application.Application;
import javafx.scene.Parent;
import uk.ac.bucks.willralph.mmmidi.user.Page;
import uk.ac.bucks.willralph.mmmidi.user.Player;

public class Launcher {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory());
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        Application.launch(App.class, args);
    }
}
