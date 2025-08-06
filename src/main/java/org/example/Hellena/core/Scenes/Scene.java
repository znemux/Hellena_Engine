package org.example.Hellena.core.Scenes;

import org.example.Hellena.core.Camera;
import org.example.Hellena.core.GameObject;
import org.example.Hellena.core.Rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private String name = "No Name";
    protected Renderer renderer = new Renderer();
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();

    protected Camera camera;

    public Scene() {

    }

    public void init() {

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
}
