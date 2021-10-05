package app.engine;

import app.ui.ApplicationFrame;

public class Engine implements Runnable {

    @Override
    public void run() {
        new ApplicationFrame();
    }
}
