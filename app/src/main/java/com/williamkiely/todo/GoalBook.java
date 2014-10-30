package com.williamkiely.todo;

import java.util.Random;

/**
 * Created by William on 10/29/2014.
 */
public class GoalBook {

    public String[] mGoal = {
            "Drink 2L of water",
            "Walk or run for 30 minutes",
            "Eat 3 fruits",
            "Climb 100 stairs",
            "Talk to a friend that you haven't seen for more than a year",
            "Refrain from eating any sweets for a day",
            "Dance in your bedroom",
            "Be charitable to someone",
            "Call your parents",
            "Meditate for 10 minutes",
            "Stop saying bad words for a day",
            "Get 8 hours of sleep",
            "Cook a healthy meal",
            "Give a speech to a crowd",
            "Make some cash in a new way"
    };

    public String getGoal() {
        String randomGoal = "";
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mGoal.length);
        randomGoal = mGoal[randomNumber];
        return randomGoal;
    }
}
