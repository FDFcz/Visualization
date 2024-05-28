package org.example;

import org.engine.grphic.*;
import org.engine.io.Input;
import org.engine.io.Window;
import org.engine.maths.Vector2f;
import org.engine.maths.Vector3f;
import org.engine.objects.Camera;
import org.engine.objects.SceneObjectUI;
import org.lwjgl.glfw.GLFW;
import org.simulation.objects.SceneObject;

public class Main implements Runnable {
    public Thread visu;
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 760;
    public Renderer renderer;
    public Shader shader;
    private SimulationScene scene;
    public Camera camera = new Camera(new Vector3f(0,0,1),new Vector3f(0,0,0));


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
        renderer = new Renderer(window,shader,camera);
        window.setBgColor(0.1f,0.1f,0.1f);
        window.create();
        //window.setFullscreen(true);
        scene = new SimulationScene();
        SceneObject.intRenderer(renderer);
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
        scene.renderObject();
        window.swapBuffers();
    }
    private void close()
    {
        window.destroy();
        shader.destroy();
        scene.destroy();
    }

}