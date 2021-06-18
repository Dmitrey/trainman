package com.myteam.game.help;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Builder {
    protected final short CATEGORY_PERSON = 0x0001;
    protected final short CATEGORY_GROUND = 0x0002;
    protected final short CATEGORY_BULLET = 0x0004;
    protected final short CATEGORY_ENEMY = 0x0008;

    protected static final World world = new World(new Vector2(0, 20), true);

    public Body createCircleBody(BodyDef.BodyType bodyType, Vector2 position,
                                 float radius, float density, float restitution) {

        BodyDef bodyDefCircle = new BodyDef();
        bodyDefCircle.type = bodyType;
        bodyDefCircle.position.set(position);
        Body worldBody = world.createBody(bodyDefCircle);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);  //здесь задается его размер
        FixtureDef fixtureDefCircle = new FixtureDef();
        fixtureDefCircle.shape = circleShape;
        fixtureDefCircle.density = density;   //здесь задается его плотность
        fixtureDefCircle.restitution = restitution;
        fixtureDefCircle.friction = 1f;
        worldBody.createFixture(fixtureDefCircle);
        circleShape.dispose();
        return worldBody;
    }

    public Body createRectangleBody(BodyDef.BodyType bodyType, Vector2 position,
                                    float hx, float hy, float density, float restitution, float friction) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(hx, hy);  //здесь задается его размер
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;   //здесь задается его плотность
        fixtureDef.restitution = restitution;
        fixtureDef.friction = friction;
        fixtureDef.filter.categoryBits = CATEGORY_GROUND;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public Body createRectangleBodyWithCustomShape(BodyDef.BodyType bodyType, float density, float restitution, Shape shape) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        Body body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;   //здесь задается его плотность
        fixtureDef.restitution = restitution;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    public World getWorld() {
        return world;
    }
}
