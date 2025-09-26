package org.example.Hellena;

import org.example.Hellena.core.Input.KeyListener;
import org.example.Hellena.core.Input.MouseListener;
import org.example.Hellena.core.Scenes.LevelEditorScene;
import org.example.Hellena.core.Scenes.LevelScene;
import org.example.Hellena.core.Scenes.Scene;
import org.example.Hellena.core.util.ImGuiLayer;
import org.example.Hellena.core.util.Time;
import org.lwjgl.Version;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int height, width;
    String title;
    private long glfwWindow;
    private boolean isWindowOpen = false;

    public float r, g, b, a;

    private static Window window = null;
    private static Scene currentScene;
    private static ImGuiLayer imGuiLayer;



    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "2D engine";
        r = 1;
        g = 1;
        b = 1;
        a = 1;
    }

    public static void changeScene(int newScene) {
        switch (newScene) {
            case 0:
                currentScene = new LevelEditorScene();
                break;
            case 1:
                currentScene = new LevelScene();
                break;
            default:
                assert  false : "Unknown scene '" + newScene + "'";
                break;
        }

        currentScene.init();
        // currentScene.load(); // The .load() is called in the .init()
        currentScene.start();
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }


    public static Scene getScene() {
        return currentScene;
    }
    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free Memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and free error callback
        cleanup();
    }

    private void cleanup() {
        // Free the window callbacks and destroy the window
        Callbacks.glfwFreeCallbacks(glfwWindow);
        GLFW.glfwDestroyWindow(glfwWindow);

        // Terminate GLFW
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    public void init() {
        // Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Init GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create GLFW window");
        }

        glfwSetWindowCloseCallback(glfwWindow, windowHandle -> {
            System.out.println("Closed Window. Setting flag to false");
            isWindowOpen = false;
        });

        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
        glfwSetWindowCloseCallback(glfwWindow, (window) -> {
            currentScene.saveExit();
            System.exit(0);
        });

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);

        // Enable V-Sync
        glfwSwapInterval(1);

        // Make Window Visible
        glfwShowWindow(glfwWindow);

        // Warning TO NOT REMOVE under any circumstance
        // If you do I will find you
        GL.createCapabilities();

        // Enable Alpha
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

        imGuiLayer = new ImGuiLayer(glfwWindow, true);

        Window.changeScene(0);

        this.isWindowOpen = true;
    }

    public void loop() {
        float beginTime = Time.getTime();
        float endTime = Time.getTime();
        float delta = -1.0f;

        while (!glfwWindowShouldClose(glfwWindow)) {
            // Poll events
            glfwPollEvents();

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (delta >= 0) {
                currentScene.update(delta);
            }

            glfwSetWindowTitle(glfwWindow, "Hellena - " + currentScene.getName());

            imGuiLayer.update(currentScene);

            glfwSwapBuffers(glfwWindow);

            endTime = Time.getTime();
            delta = endTime - beginTime;
            beginTime = endTime;
        }
    }
}
