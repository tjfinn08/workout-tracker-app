package app;

import model.Exercise;
import model.MuscleGroup;
import model.SetEntry;
import model.WorkoutDay;

public class Main {
    public static void main(String[] args) {
        System.out.println("Workout Tracker App started\n");

        MuscleGroup chest = new MuscleGroup("Chest");
        MuscleGroup back = new MuscleGroup("Back");
        MuscleGroup biceps = new MuscleGroup("biceps");
        MuscleGroup chest2 = new MuscleGroup("Chest");

        Exercise barbellBench = new Exercise("Chest", "Barbell Bench");
        barbellBench.addSet(new SetEntry(8, 225));
        barbellBench.addSet(new SetEntry(8, 225));
        Exercise inclineSmithBench = new Exercise("Chest", "Incline Smith Bench");
        inclineSmithBench.addSet(new SetEntry(12, 185));
        Exercise latPulldown = new Exercise("Back", "Lat Pulldown");
        latPulldown.addSet(new SetEntry(12, 250));

        chest.determineMuscleGroup(barbellBench);
        chest.determineMuscleGroup(inclineSmithBench);
        chest.determineMuscleGroup(latPulldown);
        back.determineMuscleGroup(latPulldown);
        chest2.determineMuscleGroup(barbellBench);

        WorkoutDay today = new WorkoutDay();

        today.addMuscleGroup(chest);
        today.addMuscleGroup(chest2);
        today.addMuscleGroup(back);

        System.out.print(today);

        System.out.println("END");
    }
}
