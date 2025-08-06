package org.example.Hell.core;

import org.example.Hell.core.components.GameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private String name = "No Name";
    private boolean isRunning = false;
    private List<GameObject> gameObjects = new ArrayList<>();

    protected Camera camera;

    public Scene() {

    }

    public void init() {

    }

    public void start() {
        for (GameObject go : gameObjects) {
            go.start();
        }
    }

    public void addGameObjectToScene(GameObject go) {
        if (!isRunning) {
            gameObjects.add(go);
        } else {
            gameObjects.add(go);
            go.start();
        }
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public abstract void update(float delta);
}
