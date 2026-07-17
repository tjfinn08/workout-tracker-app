package app;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import model.Exercise;
import model.MuscleGroup;
import model.SetEntry;

public class WorkoutAppFX extends Application {

    // Model
    private MuscleGroup currMuscleGroup;
    private Exercise currExercise;

    // Scenes
    private Scene workoutScene;
    private Scene muscleGroupScene;
    private Scene exerciseScene;

    // Controls
    private Label currMuscleLabel;
    private Label currExerciseNameLabel;

    ListView<MuscleGroup> muscleGroupListView = new ListView<>();
    ListView<Exercise> exerciseListView = new ListView<>();
    private VBox setRows;

    private Scene createWorkoutScene(Stage stage) {
        // WorkoutScene Breakdown
        VBox workoutLayout = new VBox(10);

        // WorkoutScene Control Breakdown
        Label workoutLabel = new Label("Workout Tracker");

        Button startWorkout = new Button("Start Workout");

        TextField muscleGroupField = new TextField();
        muscleGroupField.setPromptText("Enter Workout");
        muscleGroupField.setVisible(false);

        Button addMuscleGroup = new Button("Add Muscle Group");
        addMuscleGroup.setVisible(false);

        muscleGroupListView.setVisible(false);

        // WorkoutScene Layout
        workoutLayout.getChildren().addAll(
                workoutLabel,
                startWorkout,
                muscleGroupField,
                addMuscleGroup,
                muscleGroupListView
        );

        workoutLayout.setAlignment(Pos.TOP_LEFT);
        workoutLayout.setPadding(new Insets(20));

        // WorkoutScene Button action
        startWorkout.setOnAction(addMuscleGroupButton -> {
            muscleGroupField.setVisible(true);
            addMuscleGroup.setVisible(true);
        });

        addMuscleGroup.setOnAction(startWorkoutButton -> {
            String muscleName = muscleGroupField.getText().trim();
            if(!muscleName.isEmpty()) {
                currMuscleGroup = new MuscleGroup(muscleName);
                //currMuscleGroup.addExercise(exercise); day will go here
                muscleGroupListView.setVisible(true);
                muscleGroupListView.getItems().add(currMuscleGroup);
                muscleGroupField.clear();
            }
        });

        muscleGroupListView.setOnMouseClicked(e -> {
            MuscleGroup selected = muscleGroupListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                return;
            }
            currMuscleGroup = selected;
            currMuscleLabel.setText(currMuscleGroup.getMuscle());
            muscleGroupScene = createMuscleGroupScene(stage);
            refreshExerciseList();
            stage.setScene(muscleGroupScene);
        });

        workoutScene = new Scene(workoutLayout, 800, 800);
        return workoutScene;
    }

    private Scene createMuscleGroupScene(Stage stage) {
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
                currMuscleLabel
        );

        muscleGroupLayout.getChildren().addAll(
                backArrowMuscleGroup,
                newExerciseField,
                addExercise,
                exerciseLabel,
                exerciseListView
        );

        muscleGroupLayout.setAlignment(Pos.TOP_LEFT);
        muscleGroupLayout.setPadding(new Insets(20));

        addExercise.setOnAction(addExerciseButton -> {
            String exerciseName = newExerciseField.getText().trim();
            if(!exerciseName.isEmpty()) {
                Exercise exercise = new Exercise(exerciseName);
                currMuscleGroup.addExercise(exercise);
                refreshExerciseList();
                newExerciseField.clear();
            }
        });

        backToWorkoutScene.setOnAction(backButton -> {
            stage.setScene(workoutScene);
        });


        exerciseListView.setOnMouseClicked(e -> {
            Exercise selected = exerciseListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                return;
            }
            currExercise = selected;
            exerciseScene = createExerciseScene(stage);
            stage.setScene(exerciseScene);
        });

        muscleGroupScene = new Scene(muscleGroupLayout, 800, 800);
        return muscleGroupScene;
    }

    private Scene createExerciseScene(Stage stage) {
        VBox exerciseLayout = new VBox(10);
        setRows = new VBox(10);
        HBox backArrowExercise = new HBox(10);

        currExerciseNameLabel.setText(currExercise.getExerciseName());
        Button backToMuscleGroupScene = new Button("<-");

        backArrowExercise.getChildren().addAll(
                backToMuscleGroupScene,
                currExerciseNameLabel
        );

        VBox savedSetsToday = new VBox(10);

        Label todaysSets = new Label();

        if(currExercise.getExerciseSets().isEmpty()) {
            todaysSets = new Label("No Sets Saved So Far Today");
        }
        else {
            todaysSets = new Label(currExercise.exerciseSets());
        }

        savedSetsToday.getChildren().addAll(
           new Label("----------TODAY----------"),
           todaysSets,
           new Label("----------------------------")
        );

        Button addSet = new Button("Add Set");

        Button save = new Button("Save Sets");

        Label repsLabel = new Label("Reps");
        repsLabel.setPrefWidth(100);

        Label weightLabel = new Label("Weight");
        weightLabel.setPrefWidth(100);

        HBox repsWeightText = new HBox(10);
        repsWeightText.getChildren().addAll(
                repsLabel,
                weightLabel
        );

        addSet.setOnAction(addNewSet -> {
            setRows.getChildren().add(createSetRow());
        });

        save.setOnAction(saveSets -> {
            for (Node node : setRows.getChildren()) {
                HBox row = (HBox) node;

                TextField repsField = (TextField) row.getChildren().get(0);
                TextField weightField = (TextField) row.getChildren().get(2);

                SetEntry set = new SetEntry();

                set.setNumReps(Integer.parseInt(repsField.getText()));
                set.setWeight(Double.parseDouble(weightField.getText()));

                currExercise.addSet(set);
            }
        });

        exerciseLayout.setAlignment(Pos.TOP_LEFT);
        exerciseLayout.setPadding(new Insets(20));

        exerciseLayout.getChildren().addAll(
                backArrowExercise,
                savedSetsToday,
                repsWeightText,
                setRows,
                addSet,
                save
        );

        backToMuscleGroupScene.setOnAction(backButton -> {
            stage.setScene(muscleGroupScene);
        });

        exerciseScene = new Scene(exerciseLayout, 800, 800);
        return exerciseScene;
    }

    private HBox createSetRow() {
        HBox row = new HBox(10);
        TextField repsTextField = new TextField();
        repsTextField.setPromptText("Reps");
        repsTextField.setPrefWidth(75);

        TextField weightTextField = new TextField();
        weightTextField.setPromptText("Weight");
        weightTextField.setPrefWidth(75);

        Button deleteSet = new Button("Delete");

        deleteSet.setOnAction(delete -> {
            ((VBox) row.getParent()).getChildren().remove(row);
        });

        row.getChildren().addAll(
                repsTextField,
                new Label("X"),
                weightTextField,
                deleteSet
        );

        return row;
    }

    private void refreshExerciseList() {
        exerciseListView.getItems().clear();

        exerciseListView.getItems().addAll(currMuscleGroup.getMuscleExercises());
    }

    @Override
    public void start(Stage stage) {
        currMuscleLabel = new Label();
        currExerciseNameLabel = new Label();
        exerciseListView = new ListView<>();

        Scene workoutScene = createWorkoutScene(stage);

        stage.setTitle("Workout Tracker App");
        stage.setScene(workoutScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
