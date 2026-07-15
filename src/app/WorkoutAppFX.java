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
    private VBox setRows;

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

        workoutScene = new Scene(workoutLayout, 800, 800);
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

        muscleGroupScene = new Scene(muscleGroupLayout, 800, 800);
        return muscleGroupScene;
    }

    private Scene createExerciseScene(Stage stage) {
        VBox exerciseLayout = new VBox(10);
        setRows = new VBox(10);
        HBox backArrowExercise = new HBox(10);

        currExerciseName.setText(currExercise.getExerciseName());
        Button backToMuscleGroupScene = new Button("<-");

        backArrowExercise.getChildren().addAll(
                backToMuscleGroupScene,
                currExerciseName
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
