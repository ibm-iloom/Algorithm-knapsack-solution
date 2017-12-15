package com.alg.solver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/SolverDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Binary KnapsackFX");
        stage.setScene(scene);
        stage.show();
    }
}