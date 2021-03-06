package ch.fhnw.cuie.module01;

//test comment

/**
 * @author Dieter Holz
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Hello World");

        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(button);

        Scene myScene = new Scene(rootPane);

	    primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(myScene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.show();
	    primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
