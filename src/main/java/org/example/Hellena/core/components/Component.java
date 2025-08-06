package org.example.Hellena.core.components;

import org.example.Hellena.core.GameObject;

public abstract class Component {
    public GameObject gameObject = null;

    public void start() {

    }

    public abstract void update(float delta);
}
