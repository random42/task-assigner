import controllers.CateringAppManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import views.MainController;


public class Main {

    private CateringAppManager app;

    public void start(Stage primaryStage) throws Exception {

    	System.out.println("a");
      this.app = CateringAppManager.getInstance();
    }


    public static void main(String[] args) {
      CateringAppManager app = CateringAppManager.getInstance();
    }
}
