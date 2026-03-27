package app;

import model.Exercise;
import model.SetEntry;

public class Main {
    public static void main(String[] args) {
        System.out.println("Workout Tracker App started\n");

        Exercise exercise1 = new Exercise("Chest", "Barbell Bench");
        exercise1.addSet(new SetEntry(8, 225));
        exercise1.addSet(new SetEntry(8, 225));
        System.out.println(exercise1.toString());
        Exercise exercise2 = new Exercise("Chest", "Incline Smith Bench");
        exercise2.addSet(new SetEntry(12, 185));
        System.out.println(exercise2.toString());
    }
}
