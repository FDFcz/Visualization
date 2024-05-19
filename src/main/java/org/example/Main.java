package org.example;

import org.engine.Input;
import org.engine.Window;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {
    public Thread visu;
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 760;


    public static void main(String[] args) {
        new Main().stertThreds();
    }
    @Override
    public void run() {
        init();
        while (!window.shouldClose()) {
            update();
            render();
            if(Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) break;
        }
        window.destroy();
    }
    public void init() {
        window = new Window(WIDTH,HEIGHT,"Visualization");
        window.create();
    }

    public void stertThreds()
    {
        visu = new Thread( this,"visu");
        visu.start();
    }

    private void update()
    {
        window.update();
        if(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("X: "+Input.getMouseX() + " Y: "+Input.getMouseY());
    }
    private void render()
    {
        window.swapBuffers();
    }


}