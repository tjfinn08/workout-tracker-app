package logic;

import model.Exercise;
import model.MuscleGroup;
import model.SetEntry;
import model.WorkoutDay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WorkoutManager {
    private final HashMap<LocalDate, WorkoutDay> workoutDays;
    private final HashMap<String, TreeMap<LocalDate, Exercise>> exericseHistory;

    public WorkoutManager(){
        workoutDays = new HashMap<>();
        exericseHistory = new HashMap<>();
    }

    public WorkoutDay getWorkoutDay(LocalDate aDate) {
        if(!workoutDays.containsKey(aDate)) {
            WorkoutDay day = new WorkoutDay(aDate);
            workoutDays.put(aDate, day);
        }

        return workoutDays.get(aDate);
    }

    public Exercise findMostRecent(String name, LocalDate currDate) {
        TreeMap<LocalDate, Exercise> history = exericseHistory.get(name.toLowerCase());
        if(history == null) {
            return null;
        }

        Map.Entry<LocalDate, Exercise> previous = history.lowerEntry(currDate);
        if(previous == null) {
            return null;
        }

        return  previous.getValue();
    }

    public void addExercise(LocalDate date, Exercise currExercise) {
        String name = currExercise.getExerciseName().toLowerCase();

        TreeMap<LocalDate, Exercise> history = exericseHistory.get(name);
        if(history == null) {
            history = new TreeMap<>();
            exericseHistory.put(name, history);
        }

        history.put(date, currExercise);
    }

    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        for(WorkoutDay day : workoutDays.values()) {
            writer.write("DAY|" + day.getDate() + "\n");
            for(MuscleGroup muscle : day.getMuscleGroups()) {
                writer.write("MUSCLE|" + muscle.getMuscle() + "\n");
                for(Exercise exercise : muscle.getMuscleExercises()) {
                    writer.write("EXERCISE|" + exercise.getExerciseName() + "\n");
                    for(SetEntry sets : exercise.getExerciseSets()) {
                        writer.write("SET|" + sets.getNumReps() + "|" + sets.getWeight() + "\n");
                    }
                }
            }
        }
        writer.close();
    }

    public void loadFromFile(String filename) throws IOException {
        workoutDays.clear();
        exericseHistory.clear();

        Scanner input = new Scanner(new File(filename));

        WorkoutDay currDay = null;
        MuscleGroup currMuscle = null;
        Exercise currExercise = null;
        SetEntry currSet = null;

        while(input.hasNextLine()) {
            String currLine = input.nextLine();
            String[] parts = currLine.split("\\|");

            switch(parts[0]) {
                case "DAY":
                    LocalDate date = LocalDate.parse(parts[1]);
                    currDay = getWorkoutDay(date);
                    break;

                case "MUSCLE":
                    currMuscle = new MuscleGroup(parts[1]);
                    assert currDay != null;
                    currDay.addMuscleGroup(currMuscle);
                    break;

                case "EXERCISE":
                    currExercise = new Exercise(parts[1]);
                    assert currMuscle != null;
                    currMuscle.addExercise(currExercise);
                    addExercise(currDay.getDate(), currExercise);
                    break;

                case "SET":
                    int reps = Integer.parseInt(parts[1]);
                    double weight = Double.parseDouble(parts[2]);

                    assert currExercise != null;
                    currExercise.addSet(new SetEntry(reps, weight));
                    break;
            }
        }
    }
}