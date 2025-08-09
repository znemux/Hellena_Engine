package org.example.Hellena.core.util;

import imgui.*;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.example.Hellena.core.Scenes.Scene;
import org.jetbrains.annotations.Nullable;

import static org.lwjgl.glfw.GLFW.*;

public class ImGuiLayer {
    private long glfwWindow;
    private ImGuiIO io;
    private ImGuiImplGlfw implGlfw = new ImGuiImplGlfw();
    private ImGuiImplGl3 implGl3 = new ImGuiImplGl3();

    public ImGuiLayer(long glfwWindow, @Nullable boolean autoInit) {
        this.glfwWindow = glfwWindow;
        this.io = new ImGuiIO(glfwWindow);
        if (autoInit) {
            initImGui();
        }
    }

    public void initImGui() {
        ImGui.createContext();

        // Setup input and output
        io.setConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.setConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.setConfigFlags(ImGuiConfigFlags.ViewportsEnable);
        io.setIniFilename("imgui.ini");

        // Set to dark mod
        ImGui.styleColorsDark();
        ImGuiStyle style = ImGui.getStyle();

        if (io.hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            style.setWindowRounding(0.0f);
        }


        implGlfw.init(glfwWindow, true);
        implGl3.init("#version 460");
    }

    public void update(Scene currentScene) {
        if (glfwWindowShouldClose(glfwWindow)) {
            implGl3.shutdown();
            implGlfw.shutdown();
            ImGui.destroyContext();
        }
        implGlfw.newFrame();
        implGl3.newFrame();


        ImGui.newFrame();
        ImGui.begin("My scene");
        //ImGui.setWindowSize(new ImVec2(300,300));

        //Window goes here
        currentScene.sceneImGui();

        ImGui.end();
        ImGui.render();

        implGl3.renderDrawData(ImGui.getDrawData());
        if (io.hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(glfwWindow);
        }
    }
}
