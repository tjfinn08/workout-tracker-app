package app;

import model.Exercise;
import model.SetEntry;

public class Main {
    public static void main(String[] args) {
        System.out.println("Workout Tracker App started");

        Exercise exericse1 = new Exercise("Chest", "Barbell Bench");
        exericse1.addSet(SetEntry set1 = new SetEntry(8, 225));
    }
}
