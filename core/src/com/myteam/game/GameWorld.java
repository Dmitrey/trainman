package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.myteam.game.gameobjects.Person;

public class GameWorld {

    private World world;

    public World getWorld() {
        return world;
    }

    private Rectangle rectangle;
    private Circle circle;

    public Body getCarBody() {
        return carBody;
    }

    private Body carBody;
    private Body backWheel;
    private Body frontWheel;
    private Body groundBody;

    static boolean rightButtonHold = false;
    static boolean leftButtonHold = false;

    final float PIXELS_TO_METERS = 100f;

    public GameWorld() {

        initGameObjects();

        world = new World(new Vector2(0, 200), true);
        world.setContactListener(new MyContactListener());

        //создание земли
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0,0);
        groundBody = world.createBody(groundBodyDef);
        EdgeShape edgeShapeGround = new EdgeShape();
        edgeShapeGround.set(0,Gdx.graphics.getHeight()-20,Gdx.graphics.getWidth()*10, Gdx.graphics.getHeight()-20);
        FixtureDef fixtureDefGround = new FixtureDef();
        fixtureDefGround.shape = edgeShapeGround;
        fixtureDefGround.friction = 1f;
        groundBody.createFixture(fixtureDefGround);

        float[] points2 = new float[]{620,460,800,400,900,460};
        PolygonShape polygonShapeExp = new PolygonShape();
        polygonShapeExp.set(points2);

        //Body ramp1 = createRectangleBody(BodyDef.BodyType.StaticBody, new Vector2(560,480), 100,50, 0.5f, 1);
        Body ramp2 = createRectangleBodyWithCustomShape(BodyDef.BodyType.StaticBody,0.5f, 1, polygonShapeExp);

        //создание частей машины
        carBody = createRectangleBody(BodyDef.BodyType.DynamicBody, new Vector2(150,210), 100,30, 0.5f, 0.5f);
        frontWheel = createCircleBody(BodyDef.BodyType.DynamicBody, new Vector2(80,310), 25, 0.5f, 0.5f);
        backWheel = createCircleBody(BodyDef.BodyType.DynamicBody, new Vector2(230,310), 25, 0.5f, 0.5f);
        Person person = new Person(world);
        person.compilePerson();
        //соединение частей машины
        WheelJointDef wheelJointDef = new WheelJointDef();
        wheelJointDef.bodyA = frontWheel;
        wheelJointDef.bodyB = carBody;
        wheelJointDef.localAnchorA.set(0,0); //место вокруг которого крутится колесо
        wheelJointDef.localAnchorB.set(70,50); //место вокруг которого крутится тело, т.е. место колеса
        wheelJointDef.localAxisA.set(Vector2.Y);
        wheelJointDef.frequencyHz = 50;
        world.createJoint(wheelJointDef);

        WheelJointDef wheelJointDef2 = new WheelJointDef();
        wheelJointDef2.bodyA = backWheel;
        wheelJointDef2.bodyB = carBody;
        wheelJointDef2.localAnchorA.set(0,0); //место вокруг которого крутится колесо
        wheelJointDef2.localAnchorB.set(-70,50); //место вокруг которого крутится тело
        wheelJointDef2.localAxisA.set(Vector2.Y);
        wheelJointDef2.frequencyHz = 50;
        world.createJoint(wheelJointDef2);

    }

    public Body getBody() {
        return carBody;
    }

    private void initGameObjects() {
        rectangle = new Rectangle(100, 150, 100, 120);
        circle = new Circle(100,50,25f);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Circle getCircle() {
        return circle;
    }

    public void update(){

        world.step(Gdx.graphics.getDeltaTime(), 8, 4);

        if(rightButtonHold){
            frontWheel.applyTorque(1000_000_00,true);
            backWheel.applyTorque(1000_000_00,true);
        }

        if(leftButtonHold){
            frontWheel.applyTorque(-1000_000_00,true);
            backWheel.applyTorque(-1000_000_00,true);
        }

        if(backWheel.getAngularVelocity()>20)
            backWheel.setAngularVelocity(20);
        if(backWheel.getAngularVelocity()<-20)
            backWheel.setAngularVelocity(-20);
        if(frontWheel.getAngularVelocity()>20)
            frontWheel.setAngularVelocity(20);
        if(frontWheel.getAngularVelocity()<-20)
            frontWheel.setAngularVelocity(-20);

    }

    public Body createCircleBody(BodyDef.BodyType bodyType, Vector2 position,
                                 float radius, float density, float restitution){

        BodyDef bodyDefCircle = new BodyDef();
        bodyDefCircle.type = bodyType;
        bodyDefCircle.position.set(position);
        Body worldBody = world.createBody(bodyDefCircle);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);  //здесь задается его размер
        FixtureDef fixtureDefCircle = new FixtureDef();
        fixtureDefCircle.shape = circleShape;
        fixtureDefCircle.density =density;   //здесь задается его плотность
        fixtureDefCircle.restitution = restitution;
        fixtureDefCircle.friction = 1f;
        worldBody.createFixture(fixtureDefCircle);
        circleShape.dispose();
        return worldBody;
    }

    public Body createRectangleBody(BodyDef.BodyType bodyType, Vector2 position,
                                    float hx, float hy, float density, float restitution) {

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

    public Body getBackWheel() {
        return backWheel;
    }

    public Body getFrontWheel() {
        return frontWheel;
    }
}