package app;

import model.Exercise;
import model.MuscleGroups;
import model.SetEntry;

public class Main {
    public static void main(String[] args) {
        System.out.println("Workout Tracker App started\n");

        MuscleGroups chest = new MuscleGroups("Chest");

        Exercise exercise1 = new Exercise("Chest", "Barbell Bench");
        exercise1.addSet(new SetEntry(8, 225));
        exercise1.addSet(new SetEntry(8, 225));
        Exercise exercise2 = new Exercise("Chest", "Incline Smith Bench");
        exercise2.addSet(new SetEntry(12, 185));

        chest.addExercise(exercise1);
        chest.addExercise(exercise2);

        System.out.println(chest.toString());
    }
}
