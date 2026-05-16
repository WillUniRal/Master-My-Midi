package uk.ac.bucks.willralph.mmmidi.user.pages;

import javafx.scene.Parent;

import javax.swing.*;

public abstract class Page {
    private final Parent layout;
    public enum Type {
        MAIN,
        SETTINGS,
        PLAYER
    }
    private final Type PAGE_TYPE;
    public Page(final Type pageType) {
        PAGE_TYPE = pageType;
        layout = setLayout();
    }
    private void setTitle(JFrame frame) {
        String title = "Master My midi";
        switch (PAGE_TYPE) {
            case SETTINGS -> title = "Settings";
            case PLAYER -> title = "MMM - Midi Player";
        }
        frame.setTitle(title);
    }
    protected abstract Parent setLayout();
    public Parent getLayout() {return layout;}
}
