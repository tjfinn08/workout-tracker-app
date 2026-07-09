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

    // Model
    private MuscleGroup muscle;
    private Exercise currExercise;

    // Scenes
    private Scene workoutScene;
    private Scene muscleGroupScene;
    private Scene exerciseScene;

    // Controls
    private Label currMuscle;
    private Label currExerciseName;

    ListView<Exercise> exerciseList = new ListView<>();


    private Scene createWorkoutScene(Stage stage) {
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
            muscleGroupScene = createMuscleGroupuScene(stage);
            stage.setScene(muscleGroupScene);
        });

        workoutScene = new Scene(workoutLayout, 300, 250);
        return workoutScene;
    }

    private Scene createMuscleGroupuScene(Stage stage) {
        // MuscleGroupScene Breakdown
        VBox muscleGroupLayout = new VBox(10);
        HBox backArrowMuscleGroup = new HBox(10);

        // MuscleGroupScene Control Breakdown
        TextField newExerciseField = new TextField();
        newExerciseField.setPromptText("Enter Exercise");

        Button addExercise = new Button("Add Exercise");

        Label exerciseLabel = new Label("Exercises: ");

        Button backToWorkoutScene = new Button("<-");

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

        addExercise.setOnAction(addExerciseButton -> {
            String exerciseName = newExerciseField.getText().trim();
            if(!exerciseName.isEmpty()) {
                Exercise exercise = new Exercise(exerciseName);
                muscle.addExercise(exercise);
                exerciseList.getItems().add(exercise);
                newExerciseField.clear();
            }
        });

        backToWorkoutScene.setOnAction(backButton -> {
            stage.setScene(workoutScene);
        });


        exerciseList.setOnMouseClicked(e -> {
            Exercise selected = exerciseList.getSelectionModel().getSelectedItem();
            if (selected == null) {
                return;
            }
            currExercise = selected;
            exerciseScene = createExerciseScene(stage);
            stage.setScene(exerciseScene);
        });

        muscleGroupScene = new Scene(muscleGroupLayout, 300, 250);
        return muscleGroupScene;
    }

    private Scene createExerciseScene(Stage stage) {
        VBox exerciseLayout = new VBox(10);
        HBox backArrowExercise = new HBox(10);

        currExerciseName.setText(currExercise.getExerciseName());
        Button backToMuscleGroupScene = new Button("<-");

        backArrowExercise.getChildren().addAll(
                backToMuscleGroupScene,
                currExerciseName
        );

        Button addSet = new Button("+");

        addSet.setOnAction(addNewSet -> {
            HBox repsAndWeight = new HBox(10);
            TextField repsTextField = new TextField();
            repsTextField.setPromptText("Reps");
            repsTextField.setMaxWidth(75);

            TextField weightTextField = new TextField();
            weightTextField.setPromptText("Weight");
            weightTextField.setMaxWidth(75);

            repsAndWeight.getChildren().addAll(
                    repsTextField,
                    new Label("X"),
                    weightTextField
            );

            exerciseLayout.getChildren().add(repsAndWeight);
        });

        exerciseLayout.getChildren().addAll(
                backArrowExercise,
                addSet
        );

        backToMuscleGroupScene.setOnAction(backButton -> {
            stage.setScene(muscleGroupScene);
        });

        exerciseScene = new Scene(exerciseLayout, 300, 200);
        return exerciseScene;
    }

    @Override
    public void start(Stage stage) {
        currMuscle = new Label();
        currExerciseName = new Label();
        exerciseList = new ListView<>();

        Scene workoutScene = createWorkoutScene(stage);

        stage.setTitle("Workout Tracker App");
        stage.setScene(workoutScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
