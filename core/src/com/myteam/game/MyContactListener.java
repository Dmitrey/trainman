package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Contact","is working");

//        if((contact.getFixtureA().getBody() == bodyEdgeScreen &&
//                contact.getFixtureB().getBody() == body2)
//                ||
//                (contact.getFixtureA().getBody() == body2 &&
//                        contact.getFixtureB().getBody() == bodyEdgeScreen)) {
//            body.applyForceToCenter(0,MathUtils.random(20,50),true);
//            body2.applyForceToCenter(0, MathUtils.random(20,50), true);
//        }

        //contact.getFixtureA().getBody().applyForceToCenter(0,MathUtils.random(20,50),true);
        //contact.getFixtureB().getBody().applyForceToCenter(0,MathUtils.random(20,50),true);
        //body2.applyForceToCenter(0, MathUtils.random(20,50), true);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}