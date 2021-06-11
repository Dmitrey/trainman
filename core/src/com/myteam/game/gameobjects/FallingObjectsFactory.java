package com.myteam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.myteam.game.GameRender;
import com.myteam.game.help.Builder;

import java.util.ArrayList;
import java.util.List;

public class FallingObjectsFactory extends Builder {

    private float delay = 0;
    private final List<FallingObject> objs = new ArrayList<>();

    public void createFallingObjects() {
        delay += Gdx.graphics.getDeltaTime();
        if(delay > 3) {
            float objY = GameRender.getCam().viewportHeight + 10;
            int from = (int) (GameRender.getCam().position.x - GameRender.getCam().viewportWidth * 0.6f);
            int to = (int) (GameRender.getCam().position.x + GameRender.getCam().viewportWidth * 0.6f);
            int objX = (int) (Math.random() * (from - to) + to);
            objs.add(new FallingObject(objX,objY));
            delay = 0;
        }
//        for (FallingObject obj: objs) {
//            if (obj.getObjectBody().isActive() && obj.getLifeTime() > 3){
//                world.destroyBody(obj.getObjectBody());
//            }else {
//                obj.setLifeTime(obj.getLifeTime() + Gdx.graphics.getDeltaTime());
//            }
//        }
    }



}
