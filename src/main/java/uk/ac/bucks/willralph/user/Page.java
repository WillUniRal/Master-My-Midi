package uk.ac.bucks.willralph.user;

import javax.swing.*;

public abstract class Page {
    public enum Type {
        MAIN,
        SETTINGS,
        PLAYER
    }
    private final Type PAGE_TYPE;
    Page(final Type pageType) {
        PAGE_TYPE = pageType;
    }
    private void setTitle(JFrame frame) {
        String title = "Master My midi";
        switch (PAGE_TYPE) {
            case SETTINGS -> title = "Settings";
            case PLAYER -> title = "MMM - Midi Player";
        }
        frame.setTitle(title);
    }
    protected abstract void setLayout();
}
