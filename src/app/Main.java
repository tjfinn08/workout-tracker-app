package app;

import model.Exercise;
import model.SetEntry;

public class Main {
    public static void main(String[] args) {
        System.out.println("Workout Tracker App started");

        Exercise exercise1 = new Exercise("Chest", "Barbell Bench");
        exercise1.addSet(new SetEntry(8, 225));
        exercise1.addSet(new SetEntry(8, 225));
        exercise1.exerciseOutput();
        Exercise exercise2 = new Exercise("Chest", "Incline Smith Bench");
        exercise2.addSet(new SetEntry(12, 185));
        exercise2.exerciseOutput();
    }
}
