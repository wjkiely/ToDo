package com.williamkiely.todo;

import java.util.Random;

/**
 * Created by robsavioli on 26/10/14.
 */
public class GoalBook {

    public String[] mGoal = {
            "Drink 2 L of water",
            "30 minutes walking/running",
            "Eat 3 fruits",
            "Climb 100 stairs",
            "Talk to a friend that you don't see for more than 1 year",
            "No sweets today",
            "Dance in your bedroom",
            "Give charity to someone",
            "Call your parents",
            "10 minutes meditation",

    };

    public String getGoal() {

        String randomGoal = "";
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mGoal.length);

        randomGoal = mGoal[randomNumber];


        return randomGoal;

    }
}
