package com.myteam.game;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class MyFilter implements ContactFilter {
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        return fixtureA.getFilterData().categoryBits != fixtureB.getFilterData().categoryBits;
    }
}
