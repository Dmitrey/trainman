package com.myteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {

    public static int contactsAmount;

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Contact","is working");
        contactsAmount++;
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("Contact","no contact");
        contactsAmount--;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}