package net.yumix.richtexteditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RichTextEditor extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/RichTextEditor.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rich Text Editor");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
