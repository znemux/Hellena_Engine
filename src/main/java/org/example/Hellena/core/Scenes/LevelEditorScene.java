package org.example.Hellena.core.Scenes;

import org.example.Hellena.core.Camera;
import org.example.Hellena.core.GameObject;
import org.example.Hellena.core.components.SpriteRenderer;
import org.example.Hellena.core.components.Transform;
import org.example.Hellena.core.util.AssetPool;
import org.joml.Vector2f;

public class LevelEditorScene extends Scene {

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        setName("LevelEditorScene");
        this.camera = new Camera(new Vector2f());

        GameObject obj1 = new GameObject("Obj 1", new Transform(new Vector2f(100,100), new Vector2f(256,256)));
        obj1.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/textures/player.png")));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Obj 2", new Transform(new Vector2f(400,100), new Vector2f(256,256)));
        obj2.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/textures/coin.png")));
        this.addGameObjectToScene(obj2);

        loadResources();
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
    }

    @Override
    public void update(float delta) {
        for (GameObject go : this.gameObjects) {
            go.update(delta);
        }

        this.renderer.render();
    }
}
