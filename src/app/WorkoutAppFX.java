package app;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import logic.WorkoutManager;
import model.Exercise;
import model.MuscleGroup;
import model.SetEntry;
import model.WorkoutDay;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class WorkoutAppFX extends Application {

    // Model
    private MuscleGroup currMuscleGroup;
    private Exercise currExercise;
    private WorkoutDay currDate;
    private final WorkoutManager manager = new WorkoutManager();

    // Scenes
    private Scene calendarScene;
    private Scene workoutScene;
    private Scene muscleGroupScene;
    private Scene exerciseScene;

    // Controls
    private Label currMuscleLabel;
    private Label currExerciseNameLabel;

    ListView<MuscleGroup> muscleGroupListView = new ListView<>();
    ListView<Exercise> exerciseListView = new ListView<>();
    private VBox setRows;
    private VBox savedSetsToday;

    private Scene createCalendarScene(Stage stage) {
        VBox calendarLayout = new VBox(10);
        calendarLayout.setStyle("-fx-background-color: darkgray;");

        DatePicker workoutDate = new DatePicker(LocalDate.now());

        Button startWorkout = new Button("Start Workout");

        startWorkout.setOnAction(e-> {
            LocalDate date = workoutDate.getValue();
            currDate = manager.getWorkoutDay(date);
            workoutScene = createWorkoutScene(stage);
            stage.setScene(workoutScene);
            refreshMuscleGroupList();
        });

        Label workoutCalendarLabel = new Label("Workout Calendar");
        workoutCalendarLabel.getStyleClass().add("title");

        Label askCalendar = new Label("Please select the date of your workout:");
        askCalendar.getStyleClass().add("larger");

        calendarLayout.getChildren().addAll(
                workoutCalendarLabel,
                askCalendar,
                workoutDate,
                startWorkout
        );

        calendarLayout.setAlignment(Pos.TOP_LEFT);
        calendarLayout.setPadding(new Insets(20));

        calendarScene = new Scene(calendarLayout, 600, 600);
        calendarScene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm()
        );
        return calendarScene;
    } //scene is pretty good, maybe just touch it up with CSS to make it look nicer

    private Scene createWorkoutScene(Stage stage) {
        // WorkoutScene Breakdown
        VBox workoutLayout = new VBox(10);
        workoutLayout.setStyle("-fx-background-color: darkgray;");

        // WorkoutScene Control Breakdown
        Label workoutLabel = new Label("Workout Tracker");
        workoutLabel.getStyleClass().add("title");

        TextField muscleGroupField = new TextField();
        muscleGroupField.setPromptText("Enter Workout");

        Button addMuscleGroup = new Button("Add Muscle Group");

        Button backToCalendarScene = new Button("<- Back To Calendar");

        Label currDateLabel = new Label(currDate.getDate().toString());

        // WorkoutScene Layout
        workoutLayout.getChildren().addAll(
                currDateLabel,
                backToCalendarScene,
                workoutLabel,
                muscleGroupField,
                addMuscleGroup,
                muscleGroupListView
        );

        workoutLayout.setAlignment(Pos.TOP_LEFT);
        workoutLayout.setPadding(new Insets(20));

        // WorkoutScene Button action
        addMuscleGroup.setOnAction(startWorkoutButton -> {
            String muscleName = muscleGroupField.getText().trim();
            if(!muscleName.isEmpty()) {
                currMuscleGroup = new MuscleGroup(muscleName);
                currDate.addMuscleGroup(currMuscleGroup);
                muscleGroupListView.setVisible(true);
                refreshMuscleGroupList();
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

        backToCalendarScene.setOnAction(backButton -> {
            stage.setScene(calendarScene);
        });

        workoutScene = new Scene(workoutLayout, 600, 600);
        workoutScene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm()
        );
        return workoutScene;
    } //scene is pretty good, maybe just touch it up with CSS to make it look nicer

    private Scene createMuscleGroupScene(Stage stage) {
        // MuscleGroupScene Breakdown
        VBox muscleGroupLayout = new VBox(10);
        muscleGroupLayout.setStyle("-fx-background-color: darkgray;");
        HBox backArrowMuscleGroup = new HBox(10);

        // MuscleGroupScene Control Breakdown
        TextField newExerciseField = new TextField();
        newExerciseField.setPromptText("Enter Exercise");

        Button addExercise = new Button("Add Exercise");

        Label exerciseLabel = new Label("Exercises: ");

        Button backToWorkoutScene = new Button("<-");

        currMuscleLabel.getStyleClass().add("heading");

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

        muscleGroupScene = new Scene(muscleGroupLayout, 600, 600);
        muscleGroupScene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm()
        );
        return muscleGroupScene;
    } //scene is pretty good, maybe just touch it up with CSS to make it look nicer

    private Scene createExerciseScene(Stage stage) {
        VBox exerciseLayout = new VBox(10);
        exerciseLayout.setStyle("-fx-background-color: darkgray;");
        setRows = new VBox(10);
        setRows.getChildren().add(createSetRow());
        HBox backArrowExercise = new HBox(10);

        currExerciseNameLabel.setText(currExercise.getExerciseName());
        Button backToMuscleGroupScene = new Button("<-");

        currExerciseNameLabel.getStyleClass().add("heading");

        backArrowExercise.getChildren().addAll(
                backToMuscleGroupScene,
                currExerciseNameLabel
        );

        savedSetsToday = new VBox(10);

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

                refreshSetList();
            }
            setRows.getChildren().clear();
            setRows.getChildren().add(createSetRow());
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

        exerciseScene = new Scene(exerciseLayout, 600, 600);
        exerciseScene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm()
        );
        return exerciseScene;
    } //only scene that still needs work, need to add "find most recent exercise" portion

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

    private void refreshMuscleGroupList() {
        muscleGroupListView.getItems().clear();

        muscleGroupListView.getItems().addAll(currDate.getMuscleGroups());
    }

    private void refreshExerciseList() {
        exerciseListView.getItems().clear();

        exerciseListView.getItems().addAll(currMuscleGroup.getMuscleExercises());
    }

    private void refreshSetList() {
        savedSetsToday.getChildren().clear();
        Label setLabel = new Label(currExercise.exerciseSets());
        savedSetsToday.getChildren().addAll(
                new Label("----------TODAY----------"),
                setLabel,
                new Label("----------------------------")
        );
    }

    @Override
    public void start(Stage stage) {
        try {
            manager.loadFromFile("workouts.txt");
        } catch (IOException e) {
            System.out.println("No previous workouts found.");
        }
        currMuscleLabel = new Label();
        currExerciseNameLabel = new Label();
        exerciseListView = new ListView<>();

        calendarScene = createCalendarScene(stage);

        stage.setTitle("Workout Tracker App");
        stage.setScene(calendarScene);

        stage.setOnCloseRequest(e -> {
            try {
                manager.saveToFile("workouts.txt");
            } catch (IOException ex) {
                System.out.println("Save failed");
            }
        });

        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
