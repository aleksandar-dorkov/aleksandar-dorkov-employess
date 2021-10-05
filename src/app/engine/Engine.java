package app.engine;

import app.GUI.ApplicationFrame;

public class Engine implements Runnable{

    @Override
    public void run() {
        new ApplicationFrame();
    }
}
