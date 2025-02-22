import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.scene.*;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            URL resource = getClass().getResource("./Main.fxml");
            Parent root=FXMLLoader.load(resource);
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("This is first fxml");
            primaryStage.show();
        
    }
}
