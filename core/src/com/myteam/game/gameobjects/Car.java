package com.myteam.game.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.myteam.game.help.Builder;

public class Car extends Builder {

    private Body carBody;
    private Body backWheel;
    private Body frontWheel;

    public void compileCar(){
        carBody = createRectangleBody(BodyDef.BodyType.DynamicBody, new Vector2(200,600), 100,30, 5f, 2f);
        frontWheel = createCircleBody(BodyDef.BodyType.DynamicBody, new Vector2(100,610), 25, 0.5f, 0.1f);
        backWheel = createCircleBody(BodyDef.BodyType.DynamicBody, new Vector2(100,610), 25, 0.5f, 0.1f);

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

    public Body getCarBody() {
        return carBody;
    }

    public Body getFrontWheel() {
        return frontWheel;
    }

    public Body getBackWheel() {
        return backWheel;
    }
}
