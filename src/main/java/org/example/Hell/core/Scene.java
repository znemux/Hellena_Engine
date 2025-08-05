package org.example.Hell.core;

public abstract class Scene {
    private String name = "No Name";

    public Scene() {

    }

    public void init() {

    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public abstract void update(float delta);
}
