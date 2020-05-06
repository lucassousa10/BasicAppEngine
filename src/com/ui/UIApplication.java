package com.ui;

import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;
import com.engine.entities.ApplicationObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UIApplication extends AbstractApplication {

    private ArrayList<ApplicationObject> objects;
    private HashMap<String, ApplicationObject> objectsTagsMap;

    public UIApplication() {
        objects = new ArrayList<>();
        objectsTagsMap = new HashMap<>();
    }

    public void addObject(ApplicationObject obj) {
        if (!objects.contains(obj)) {
            objects.add(obj);
            if (obj.getTag() != null) {
                objectsTagsMap.put(obj.getTag(), obj);
            }
        }
    }

    public void removeObject(ApplicationObject obj) {
        objects.remove(obj);
        objectsTagsMap.remove(obj.getTag());
    }

    public ApplicationObject getObject(String tag) {
        return objectsTagsMap.get(tag);
    }

    //todo: otimizar um pouco mais
    public void setObject(String tag, ApplicationObject newObject) {
        objects.set(objects.indexOf(objectsTagsMap.get(tag)), newObject);
    }

    @Override
    public void update(Engine engine, float deltaTime) {
        for (ApplicationObject obj : objects) {
            if (!obj.isDead()) {
                obj.update(engine, deltaTime);
            }
        }
    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        for (ApplicationObject obj : objects) {
            if (!obj.isDead()) {
                obj.render(engine, renderer);
            }
        }
    }
}
