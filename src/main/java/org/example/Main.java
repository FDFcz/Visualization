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

    public final Vector3f colorWhite = new Vector3f(1.0f, 1.0f, 1.0f);
    public final Vector3f colorGreen = new Vector3f(0f, 1.0f, 0f);
    private Mesh carMesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-0.062f,  0.05f, 0.0f), SceneObject.StatusColors.white, new Vector2f(0,0)),
            new Vertex(new Vector3f(-0.062f, -0.05f, 0.0f), SceneObject.StatusColors.white,new Vector2f(0,0.8f)),
            new Vertex(new Vector3f( 0.062f, -0.05f, 0.0f), SceneObject.StatusColors.white,new Vector2f(0.8f,0.8f)),
            new Vertex(new Vector3f( 0.062f,  0.05f, 0.0f), SceneObject.StatusColors.white,new Vector2f(0.8f,0))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    },new Material("/textures/car.png"));
    private Mesh metalicMesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-0.072f,  0.06f, 0.0f), SceneObject.StatusColors.green, new Vector2f(0.4f,0.4f)),
            new Vertex(new Vector3f(-0.072f, -0.06f, 0.0f), SceneObject.StatusColors.green,new Vector2f(0.4f,0.6f)),
            new Vertex(new Vector3f( 0.072f, -0.06f, 0.0f), SceneObject.StatusColors.green,new Vector2f(0.6f,0.6f)),
            new Vertex(new Vector3f( 0.072f,  0.06f, 0.0f), SceneObject.StatusColors.green,new Vector2f(0.6f,0.4f))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    },new Material("/textures/metalic.jpg"));
    public SceneObjectUI object = new SceneObjectUI(new Vector3f(0, 0, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1), carMesh);
    public SceneObjectUI object2 = new SceneObjectUI(new Vector3f(0f, 0, 0), new Vector3f(0, 0f, 0), new Vector3f(1, 1, 1), metalicMesh);
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
        carMesh.create();
        metalicMesh.create();
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
        renderer.renderMesh(object);
        renderer.renderMesh(object2);
        window.swapBuffers();
    }
    private void close()
    {
        window.destroy();
        carMesh.destroy();
        metalicMesh.destroy();
        shader.destroy();
    }

}