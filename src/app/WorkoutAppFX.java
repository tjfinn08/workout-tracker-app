package app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import model.Exercise;
import model.MuscleGroup;

public class WorkoutAppFX extends Application {

    private MuscleGroup muscle;
    private Exercise currExercise;

    @Override
    public void start(Stage stage) {

        // WorkoutScene Breakdown
        VBox workoutLayout = new VBox(10);

        // WorkoutScene Control Breakdown
        Label workoutLabel = new Label("Workout Tracker");

        Button addMuscleGroup = new Button("+");

        TextField muscleGroupField = new TextField();
        muscleGroupField.setPromptText("Enter Workout");
        muscleGroupField.setVisible(false);

        Button startWorkout = new Button("Start Workout");
        startWorkout.setVisible(false);

        // WorkoutScene Layout
        workoutLayout.getChildren().addAll(
                workoutLabel,
                addMuscleGroup,
                muscleGroupField,
                startWorkout
        );

        workoutLayout.setAlignment(Pos.TOP_LEFT);
        workoutLayout.setPadding(new Insets(20));

        Scene workoutScene = new Scene(workoutLayout, 300, 250);

        // MuscleGroupScene Breakdown
        VBox muscleGroupLayout = new VBox(10);
        HBox backArrowMuscleGroup = new HBox(10);

        // MuscleGroupScene Control Breakdown
        TextField newExerciseField = new TextField();
        newExerciseField.setPromptText("Enter Exercise");
        newExerciseField.setVisible(true);

        Button addExercise = new Button("Add Exercise");
        addExercise.setVisible(true);

        Label exerciseLabel = new Label("Exercises: ");
        exerciseLabel.setVisible(true);

        ListView<Exercise> exerciseList = new ListView<>();
        exerciseList.setVisible(true);

        Button backToWorkoutScene = new Button("<-");

        Label currMuscle = new Label("");

        // MuscleGroupScene Layout

        backArrowMuscleGroup.getChildren().addAll(
                backToWorkoutScene,
                currMuscle
        );

        muscleGroupLayout.getChildren().addAll(
                backArrowMuscleGroup,
                newExerciseField,
                addExercise,
                exerciseLabel,
                exerciseList
        );

        muscleGroupLayout.setAlignment(Pos.TOP_LEFT);
        muscleGroupLayout.setPadding(new Insets(20));

        Scene muscleGroupScene = new Scene(muscleGroupLayout, 300, 250);

        // Button Breakdowns

        // WorkoutScene Button action
        addMuscleGroup.setOnAction(addMuscleGroupButton -> {
            muscleGroupField.setVisible(true);
            startWorkout.setVisible(true);
        });

        startWorkout.setOnAction(startWorkoutButton -> {
            String muscleName = muscleGroupField.getText().trim();
            if(!muscleName.isEmpty()) {
                muscle = new MuscleGroup(muscleName);
                currMuscle.setText(muscle.getMuscle());
            }
            stage.setScene(muscleGroupScene);
        });

        // MuscleGroupScene Button action
        addExercise.setOnAction(addExerciseButton -> {
            String exerciseName = newExerciseField.getText().trim();
            if(!exerciseName.isEmpty()) {
                Exercise exercise = new Exercise(exerciseName);
                muscle.addExercise(exercise);
                exerciseList.getItems().add(exercise);
                newExerciseField.clear();
            }
        });

        exerciseList.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    if(newVal != null) {
                        VBox exerciseLayout = new VBox(10);
                        HBox backArrowExercise = new HBox(10);

                        Label exerciseName = new Label(newVal.getExerciseName());
                        Button backToMuscleGroupScene = new Button("<-");

                        backArrowExercise.getChildren().addAll(
                                backToMuscleGroupScene,
                                exerciseName
                        );

                        exerciseLayout.getChildren().addAll(
                                backArrowExercise
                        );

                        backToMuscleGroupScene.setOnAction(backButton -> {
                            stage.setScene(muscleGroupScene);
                        });

                        Scene exerciseScene = new Scene(exerciseLayout, 300, 200);
                        stage.setScene(exerciseScene);
                    }
                });

        backToWorkoutScene.setOnAction(backButton -> {
            stage.setScene(workoutScene);
        });

        stage.setTitle("Workout Tracker App");
        stage.setScene(workoutScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
