package com.williamkiely.todo;

import java.util.Random;

/**
 * Created by robsavioli on 26/10/14.
 */
public class GoalBook {

    public String[] mGoal = {
            "Drink 2L of water",
            "Walk or run for 30 minutes",
            "Eat 3 fruits",
            "Climb 100 stairs",
            "Talk to a friend that you haven't seen for more than a year",
            "Don't eat any sweets",
            "Dance in your bedroom",
            "Be charitable charity to someone",
            "Call your parents",
            "Meditate for 10 minutes",
            "Stop saying bad words for a day"

    };

    public String getGoal() {

        String randomGoal = "";
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mGoal.length);

        randomGoal = mGoal[randomNumber];


        return randomGoal;

    }
}
