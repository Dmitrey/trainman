package com.myteam.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.myteam.game.help.Builder;

public class BulletFactory extends Builder {

    public Body getBullet(Vector2 from, Vector2 to){
        Body bullet = createBullet(BodyDef.BodyType.DynamicBody, from,
                0.2f,0.1f,64f,0,0);
        bullet.setLinearVelocity(to);
        return bullet;
    }

    public Body createBullet(BodyDef.BodyType bodyType, Vector2 position,
                                    float hx, float hy, float density, float restitution, float friction) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        bodyDef.bullet = true;
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy);  //здесь задается его размер
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;   //здесь задается его плотность
        fixtureDef.restitution = restitution;
        fixtureDef.friction = friction;
        fixtureDef.filter.categoryBits = CATEGORY_BULLET;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

}
