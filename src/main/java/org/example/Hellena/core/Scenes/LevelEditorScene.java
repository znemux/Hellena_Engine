package org.example.Hellena.core.Scenes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imgui.ImGui;
import org.example.Hellena.core.Camera;
import org.example.Hellena.core.Deserializers.ComponentDeserializer;
import org.example.Hellena.core.Deserializers.GameObjectDeserializer;
import org.example.Hellena.core.GameObject;
import org.example.Hellena.core.components.*;
import org.example.Hellena.core.util.AssetPool;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class LevelEditorScene extends Scene {

    private GameObject obj1;

    private SpriteSheet sprites;

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        loadResources();

        if (levelLoaded) {
            return;
        }

        sprites = AssetPool.getSpriteSheet("assets/textures/spritesheet.png");

        setName("LevelEditorScene");
        this.camera = new Camera(new Vector2f());


        if (levelLoaded) {
            return;
        }

        load();
        
        if (gameObjects == null || gameObjects.isEmpty()) {
            obj1 = new GameObject("Obj 1", new Transform(new Vector2f(200,100), new Vector2f(256,256)), 2);
            obj1.addComponent(new SpriteRenderer());
            obj1.getComponent(SpriteRenderer.class).setColor(new Vector4f(1, 0, 0, 1));
            obj1.addComponent(new RigidBody());
            this.addGameObjectToScene(obj1);


            GameObject obj2 = new GameObject("Obj 2", new Transform(new Vector2f(400,100), new Vector2f(256,256)), 1);
            SpriteRenderer obj2SpriteRenderer = new SpriteRenderer();
            Sprite obj2Sprite = new Sprite();
            obj2Sprite.setTexture(AssetPool.getTexture("assets/textures/blendImage2.png"));
            obj2SpriteRenderer.setSprite(obj2Sprite);
            obj2.addComponent(obj2SpriteRenderer);
            this.addGameObjectToScene(obj2);
        }

        this.activeGameObject = gameObjects.getFirst();
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpriteSheet("assets/textures/spritesheet.png", new SpriteSheet(AssetPool.getTexture("assets/textures/spritesheet.png"), 16, 16, 26, 0));
    }

    @Override
    public void update(float delta) {
        for (GameObject go : this.gameObjects) {
            go.update(delta);
        }

        this.renderer.render();
    }


    @Override
    public void imgui() {
        ImGui.begin("Level Editor");
        ImGui.text("Something");
        ImGui.end();
    }
}
