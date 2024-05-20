package org.example;

import org.engine.grphic.Mesh;
import org.engine.grphic.Renderer;
import org.engine.grphic.Vertex;
import org.engine.io.Input;
import org.engine.io.Window;
import org.engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {
    public Thread visu;
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 760;
    public Renderer renderer;

    public Mesh mesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-0.5f,  0.5f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, 0.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, 0.0f))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    });

    public static void main(String[] args) {
        new Main().stertThreds();
    }
    @Override
    public void run() {
        init();
        while (!window.shouldClose()&&!Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            update();
            render();
            if(Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
        }
        window.destroy();
    }
    public void init() {
        renderer = new Renderer();

        window = new Window(WIDTH,HEIGHT,"Visualization");
        window.setBgColor(0.1f,0.1f,0.1f);
        window.create();
        //window.setFullscreen(true);
        mesh.create();
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
        renderer.renderMesh(mesh);
        window.swapBuffers();
    }


}