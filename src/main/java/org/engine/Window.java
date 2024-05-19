package org.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
    private int width, height;
    private String title;
    private long window;
    private int frames;
    private long time;
    private Input input = new Input();

    public Window(int width, int height,String title)
    {
        this.width = width;
        this.height = height;
        this.title = title;
    }
    public void create()
    {
        if(GLFW.glfwInit())
        {
            System.out.println("GLFW Init Succeeded");

            //input = new Input();
            window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
            if(window == 0)
            {
                System.out.println("GLFW window Failed");
                return;
            }
            GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

            GLFW.glfwSetKeyCallback(window,input.getKeyboardCallback());
            GLFW.glfwSetCursorPosCallback(window,input.getMouseMoveCallback());
            GLFW.glfwSetMouseButtonCallback(window,input.getMouseButtonsCallback());

            GLFW.glfwMakeContextCurrent(window);

            GLFW.glfwShowWindow(window);
            GLFW.glfwSwapInterval(1);

            time=System.currentTimeMillis();
        }
        else {
            System.out.println("GLFW Init Failed");
        }
    }

    public void update()
    {
        GLFW.glfwPollEvents();
        frames++;
        if(System.currentTimeMillis() > time+1000)
        {
            GLFW.glfwSetWindowTitle(window,title +" | FPS: "+frames);
            time=System.currentTimeMillis();
            frames=0;
        }
    }
    public void swapBuffers()
    {
        GLFW.glfwSwapBuffers(window);
    }
    public boolean shouldClose()
    {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy()
    {
        input.destroy();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
