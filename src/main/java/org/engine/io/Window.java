package org.engine.io;

import org.engine.maths.Matrix4f;
import org.engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
    private int width, height;
    private String title;
    private long window;
    private int frames;
    private long time;
    private Input input = new Input();
    private Vector3f background = new Vector3f(0.1f, 0.1f, 0.1f);
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResize,isFullscreen;
    private int[] windowPosX = new int[1], windowPosY = new int[1];
    private Matrix4f projection;

    public void setBgColor(float r, float g, float b) {background.set(r,g,b);}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setTitle(String title) {this.title = title;}
    public void setWindow(long window) {this.window = window;}
    public void setFullscreen(boolean fullscreen)
    {
        isFullscreen = fullscreen;
        isResize = true;
        if (isFullscreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public String getTitle() {return title;}
    public long getWindow() {return window;}
    public Matrix4f getProjectionMatrix() {return projection;}
    public boolean isFullscreen() {return isFullscreen;}

    public Window(int width, int height, String title)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        this.projection = Matrix4f.projection(70f,(float) width/height,0.1f,100f);
    }
    public void create()
    {
        if(GLFW.glfwInit())
        {
            System.out.println("GLFW Init Succeeded");

            input = new Input();
            window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor():0, 0);
            if(window == 0)
            {
                System.out.println("GLFW window Failed");
                return;
            }
            GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            windowPosX[0] = (videoMode.width() - width) / 2;
            windowPosY[0] = (videoMode.height() - height) / 2;
            GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
            GLFW.glfwMakeContextCurrent(window);
            GL.createCapabilities();
            GL11.glEnable(GL11.GL_DEPTH_TEST);

            createCallBacks();

            GLFW.glfwShowWindow(window);
            GLFW.glfwSwapInterval(1);

            time=System.currentTimeMillis();
        }
        else {
            System.out.println("GLFW Init Failed");
        }
    }
    private void createCallBacks()
    {
        sizeCallback =  new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int newWidth, int newHeight) {
                width = newWidth;
                height = newHeight;
                isResize = true;
            }
        };

        GLFW.glfwSetKeyCallback(window,input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window,input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window,input.getMouseButtonsCallback());

        GLFW.glfwSetWindowSizeCallback(window,sizeCallback);
    }
    public void update()
    {
        if(isResize){GL11.glViewport(0,0,width,height);isResize=false;}

        GL11.glClearColor(background.getX(),background.getY(),background.getZ(),1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
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
        sizeCallback.free();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
