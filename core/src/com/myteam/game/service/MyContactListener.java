package com.myteam.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.myteam.game.GameWorld;

public class MyContactListener implements ContactListener {

    public static int contactsAmount;
    private GameWorld gameWorld;

    public MyContactListener(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void beginContact(Contact contact) {
        //Gdx.app.log("Contact","is working");
        if (contact.getFixtureA().getFilterData().categoryBits == 0x0001 ||
                contact.getFixtureB().getFilterData().categoryBits == 0x0001)
        contactsAmount++;
        if (contact.getFixtureA().getBody().getUserData() == "enemy1" || contact.getFixtureB().getBody().getUserData() == "enemy1")
            gameWorld.getEnemy().haveContact = true;
        //System.out.println(contactsAmount);
    }

    @Override
    public void endContact(Contact contact) {
        //Gdx.app.log("Contact","no contact");
        if (contact.getFixtureA().getFilterData().categoryBits == 0x0001 ||
                contact.getFixtureB().getFilterData().categoryBits == 0x0001)
        contactsAmount--;
        if (contact.getFixtureA().getBody().getUserData() == "enemy1" || contact.getFixtureB().getBody().getUserData() == "enemy1")
            gameWorld.getEnemy().haveContact = false;
        //System.out.println(contactsAmount);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}