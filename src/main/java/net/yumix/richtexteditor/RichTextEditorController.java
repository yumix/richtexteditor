package net.yumix.richtexteditor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.HTMLEditorSkin;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RichTextEditorController implements Initializable {

    @FXML
    private HTMLEditor htmlEditor;
    
    private Path editFile = null;
    
    @FXML
    public void handleNew(ActionEvent event) {
        htmlEditor.setHtmlText(null);
    }
    
    @FXML
    public void handleOpen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("HTML Files", "*.html"), new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            editFile = selectedFile.toPath();
            byte[] by = Files.readAllBytes(editFile);
            String htmlText = new String(by);
            htmlEditor.setHtmlText(htmlText);
        }
    }
    
    @FXML
    public void handleSave(ActionEvent event) throws IOException {
        if (editFile == null) {
            handleSaveAs(event);
        } else {
            String htmlText = htmlEditor.getHtmlText();
            Files.write(editFile, htmlText.getBytes());
        }
    }
    
    @FXML
    public void handleSaveAs(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("HTML Files", "*.html"), new ExtensionFilter("All Files", "*.*"));
        if (editFile != null) {
            fileChooser.setInitialFileName(editFile.getFileName().toString());
        }
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            String htmlText = htmlEditor.getHtmlText();
            Files.write(selectedFile.toPath(), htmlText.getBytes());
        }
    }
    
    @FXML
    public void handleQuit(ActionEvent event) {
        Platform.exit();
    }
    
    private void fireButtonEvent(String buttonName) throws Exception {
        Field field = HTMLEditorSkin.class.getDeclaredField(buttonName);
        field.setAccessible(true);
        Button button = (Button) field.get(htmlEditor.getSkin());
        button.fireEvent(new ActionEvent());
    }
    
    @FXML
    public void handleCut(ActionEvent event) throws Exception {
        fireButtonEvent("cutButton");
    }
    
    @FXML
    public void handleCopy(ActionEvent event) throws Exception {
        fireButtonEvent("copyButton");
    }
    
    @FXML
    public void handlePaste(ActionEvent event) throws Exception {
        fireButtonEvent("pasteButton");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        
    }

}
