package app;

import model.Exercise;
import model.MuscleGroups;
import model.SetEntry;

public class Main {
    public static void main(String[] args) {
        System.out.println("Workout Tracker App started\n");

        MuscleGroups chest = new MuscleGroups("Chest");
        MuscleGroups back = new MuscleGroups("Back");
        MuscleGroups biceps = new MuscleGroups("biceps");

        Exercise barbellBench = new Exercise("Chest", "Barbell Bench");
        barbellBench.addSet(new SetEntry(8, 225));
        barbellBench.addSet(new SetEntry(8, 225));
        Exercise inclineSmithBench = new Exercise("Chest", "Incline Smith Bench");
        inclineSmithBench.addSet(new SetEntry(12, 185));
        Exercise latPulldown = new Exercise("Back", "Lat Pulldown");

        chest.determineMuscleGroup(barbellBench);
        chest.determineMuscleGroup(inclineSmithBench);
        chest.determineMuscleGroup(latPulldown);

        back.determineMuscleGroup(latPulldown);

        System.out.println(chest);
        System.out.println(back);
    }
}
