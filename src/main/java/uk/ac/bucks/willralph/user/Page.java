package uk.ac.bucks.willralph.user;

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
    private void setTitle() {
        String title = "Master My midi";
        switch (PAGE_TYPE) {
            case SETTINGS -> title = "Settings";
            case PLAYER -> title = "MMM - Midi Player";
        }
        // set title
    }
    protected abstract void setLayout();
}
