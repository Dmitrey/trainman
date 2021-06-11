package com.myteam.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.myteam.game.help.Builder;

public class FallingObject extends Builder {

    private Body objectBody;
    private float lifeTime = 0;

    public FallingObject(float objX, float objY){
        createObject(BodyDef.BodyType.DynamicBody, new Vector2(objX, objY),
                0.2f, 0.1f, 64f, 0, 0);
    }
    public Body createObject(BodyDef.BodyType bodyType, Vector2 position,
                             float hx, float hy, float density, float restitution, float friction) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        Body body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(0.75f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;   //здесь задается его плотность
        fixtureDef.restitution = restitution;
        fixtureDef.friction = friction;
        fixtureDef.filter.categoryBits = CATEGORY_ENEMY;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public void setLifeTime(float lifeTime) {
        this.lifeTime = lifeTime;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public Body getObjectBody() {
        return objectBody;
    }
}
