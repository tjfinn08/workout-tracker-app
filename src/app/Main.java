package app;

import logic.WorkoutManager;
import model.Exercise;
import model.MuscleGroup;
import model.SetEntry;
import model.WorkoutDay;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Workout Tracker App started\n");

        WorkoutManager manager = new WorkoutManager();

        while(true) {
            System.out.print("Would you like to use today's date? (Yes/No): ");
            String userInput = input.nextLine();

            LocalDate date;

            if (userInput.equals("No")) {
                System.out.print("Select a date (Must be in YYYY-MM-DD format): ");
                String dateString = input.nextLine();
                date = LocalDate.parse(dateString);
            }
            else {
                date = LocalDate.now();
            }

            WorkoutDay userDay = manager.getWorkoutDay(date);

            while(true) {
                System.out.print("What muscle group are you hitting? ");

                String userMuscle = input.nextLine();

                MuscleGroup currMuscle = new MuscleGroup(userMuscle);

                while(true) {
                    System.out.print("Add exercise? (will be a button) (Yes/No): ");
                    userInput = input.nextLine();
                    if (userInput.equals("No")) {
                        break;
                    }

                    System.out.print("What exercise? (Will be a blank where the user can type in the name of their exercise) ");
                    String userExercise = input.nextLine();
                    Exercise previous = manager.findMostRecent(userExercise);
                    if (previous != null) {
                        System.out.println("Last Workout:");
                        System.out.println(previous);
                    }

                    Exercise currExercise = new Exercise(userExercise);

                    while(true) {
                        System.out.print("Add set? (will be a button) (Yes/No): ");
                        userInput = input.nextLine();
                        if (userInput.equals("No")) {
                            break;
                        }
                        SetEntry currSet = new SetEntry();
                        System.out.print("Reps (will be a blank where user can type in number) ");
                        int numReps = input.nextInt();
                        System.out.print("Weight (will be a blank where user can type in number with decimal option) ");
                        double weight = input.nextDouble();
                        currSet.setNumReps(numReps);
                        currSet.setWeight(weight);
                        currExercise.addSet(currSet);
                        input.nextLine();
                    }
                    manager.addExercise(currMuscle, currExercise);
                }
                userDay.addMuscleGroup(currMuscle);

                System.out.print("Would you like to add another muscle group? (Yes/No) ");
                userInput = input.nextLine();
                if(userInput.equals("No")){
                    break;
                }
            }

            System.out.print(userDay);

            System.out.print("Enter Quit if you are done, if not press enter to enter another date: ");
            userInput = input.nextLine();
            if(userInput.equals("Quit")){
                break;
            }
        }

        manager.saveToFile("workouts.txt");
        System.out.println("END");
    }
}
