package org.example;

import org.engine.grphic.*;
import org.engine.io.Input;
import org.engine.io.Window;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {
    public Thread visu;
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 760;
    public Renderer renderer;
    public Shader shader;

    public Mesh mesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-0.5f,  0.5f, 0.0f),new Vector3f(1f,0,0), new Vector2f(0,0)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f),new Vector3f(0f,1.0f,0),new Vector2f(0,1)),
            new Vertex(new Vector3f( 0.5f, -0.5f, 0.0f),new Vector3f(1.0f,0,0),new Vector2f(1,1)),
            new Vertex(new Vector3f( 0.5f,  0.5f, 0.0f),new Vector3f(0f,1.0f,0),new Vector2f(1,0))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    },new Material("/textures/car.png"));

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
        close();
    }
    public void init() {
        window = new Window(WIDTH,HEIGHT,"Visualization");
        shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
        renderer = new Renderer(shader);
        window.setBgColor(0.1f,0.1f,0.1f);
        window.create();
        //window.setFullscreen(true);
        mesh.create();
        shader.create();
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
    private void close()
    {
        window.destroy();
        mesh.destroy();
        shader.destroy();
    }

}