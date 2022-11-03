package se.iths.labbthreestefankarlsson;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class LabbThreeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LabbThreeApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("JavaFX Chat");
        stage.setScene(scene);
        LabbThreeController controller = fxmlLoader.getController();
        controller.init(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
