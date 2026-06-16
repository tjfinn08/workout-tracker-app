package app;

import logic.WorkoutManager;
import model.Exercise;
import model.MuscleGroup;
import model.SetEntry;
import model.WorkoutDay;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Workout Tracker App started\n");



        WorkoutManager manager = new WorkoutManager();

        System.out.print("Would you like to use today's date? (Yes/No): ");
        String userInput = input.nextLine();

        LocalDate date;

        if(userInput.equals("No")) {
            System.out.print("Select a date (Must be in YYYY-MM-DD format): ");
            String dateString = input.nextLine();
            date = LocalDate.parse(dateString);
        }
        else {
            date = LocalDate.now();
        }

        WorkoutDay userDay = manager.getWorkoutDay(date);

        System.out.print("What muscle group are you hitting today? ");

        String userMuscleGroup = input.nextLine();

        MuscleGroup currMuscleGroup = new MuscleGroup(userMuscleGroup);

        while(true) {
            System.out.print("Would you like to add an exercise? (Yes/No): ");
            userInput = input.nextLine();
            if(userInput.equals("No")) {
                break;
            }

            System.out.print("What exercise do you want to do? ");
            String userExercise = input.nextLine();
            Exercise currExercise = new Exercise(userExercise);

            while(true) {
                System.out.print("Would you like to add a set? (Yes/No): ");
                userInput = input.nextLine();
                if(userInput.equals("No")) {
                    break;
                }
                SetEntry currSet = new SetEntry();
                System.out.print("How many reps did you do? ");
                int numReps = input.nextInt();
                System.out.print("What was the weight? ");
                double weight = input.nextDouble();
                currSet.setNumReps(numReps);
                currSet.setWeight(weight);
                currExercise.addSet(currSet);
                input.nextLine();
            }
            currMuscleGroup.addExercise(currExercise);
        }

        userDay.addMuscleGroup(currMuscleGroup);

        System.out.print(userDay);

        System.out.println("END");
    }
}
