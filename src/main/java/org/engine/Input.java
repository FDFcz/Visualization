package org.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input
{
    private static Boolean[] keys = new Boolean[GLFW.GLFW_KEY_LAST];
    private static Boolean[] buttons = new Boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX,mouseY;

    private static GLFWKeyCallback keyboard;
    private static GLFWCursorPosCallback mouseMove;
    private static GLFWMouseButtonCallback mouseButtons;

    public static Boolean[] getKeys() {return keys;}
    public static Boolean[] getButtons() {return buttons;}
    public static double getMouseX() {return mouseX;}
    public static double getMouseY() {return mouseY;}
    public static GLFWKeyCallback getKeyboardCallback() {return keyboard;}
    public static GLFWCursorPosCallback getMouseMoveCallback() {return mouseMove;}
    public static GLFWMouseButtonCallback getMouseButtonsCallback() {return mouseButtons;}

    public Input()
    {
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };
        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };
        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };
    }

    public void destroy()
    {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();

    }

    public static boolean isKeyDown(int key)
    {
        if(keys[key] == null) return false;
        return keys[key];
    }
    public static boolean isButtonDown(int button)
    {
        if(buttons[button] == null) return false;
        return buttons[button];
    }
}
