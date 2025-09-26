package org.example.Hellena.core.Scenes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imgui.ImGui;
import org.example.Hellena.core.Camera;
import org.example.Hellena.core.Deserializers.ComponentDeserializer;
import org.example.Hellena.core.Deserializers.GameObjectDeserializer;
import org.example.Hellena.core.GameObject;
import org.example.Hellena.core.Rendering.Renderer;
import org.example.Hellena.core.components.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private String name = "No Name";
    protected Renderer renderer = new Renderer();
    private boolean isRunning = false;
    protected boolean levelLoaded = false;
    protected List<GameObject> gameObjects = new ArrayList<>();
    protected GameObject activeGameObject = null;

    protected Camera camera;

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Component.class, new ComponentDeserializer())
            .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
            .create();

    public Scene() {

    }

    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.saveExit();
            System.out.println("Shutdown");
        }));
    }

    public void start() {
        for (GameObject go : gameObjects) {
            go.start();
            this.renderer.add(go);
        }
        isRunning = true;
    }

    public void addGameObjectToScene(GameObject go) {
        if (!isRunning) {
            gameObjects.add(go);
        } else {
            gameObjects.add(go);
            go.start();
            this.renderer.add(go);
        }
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public abstract void update(float delta);

    public Camera getCamera() {
        return this.camera;
    }

    public void sceneImGui() {
        if (activeGameObject != null) {
            ImGui.begin("Inspector");
            activeGameObject.imgui();
            ImGui.end();
        }

        imgui();
    }

    public void imgui() {

    }

    public void saveExit() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("level.txt"))) {
            gson.toJson(this.gameObjects, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        String inFile = "";
        try {
            inFile = new String(Files.readAllBytes(Paths.get("level.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inFile.equals("")) {
            GameObject[] objs = gson.fromJson(inFile, GameObject[].class);
            for (int i = 0; i < objs.length; i++) {
                addGameObjectToScene(objs[i]);
            }
            this.levelLoaded = true;
        }
    }
}
