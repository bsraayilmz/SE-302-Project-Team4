import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public CoursesModel model = new CoursesModel();
    @FXML
    Pane displayVersionsPage, newCoursePage, updateCoursePage, userManualPage, homePage, saveFeaturesPage;

    @FXML
    Button newCourseButton;

    public void clickNC(){
        newCourseButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(true);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            saveFeaturesPage.setVisible(false);
            userManualPage.setVisible(false);
        });
    }

    @FXML
    Button updateCourseButton;
    public void clickUC(){
        updateCourseButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(false);
            updateCoursePage.setVisible(true);
            displayVersionsPage.setVisible(false);
            saveFeaturesPage.setVisible(false);
            userManualPage.setVisible(false);
        });
    }

    @FXML
    Button displayVersionsButton;

    public void clickDV(){
        displayVersionsButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(true);
            saveFeaturesPage.setVisible(false);
            userManualPage.setVisible(false);
        });
    }

    @FXML
    Button userManualButton;

    public void clickUM(){
        userManualButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            saveFeaturesPage.setVisible(false);
            userManualPage.setVisible(true);
        });
    }

    @FXML
    Button saveFeaturesButton;

    public void clickSF(){
        saveFeaturesButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            saveFeaturesPage.setVisible(true);
            userManualPage.setVisible(false);
        });
    }



////    @FXML
////    AnchorPane anchorPaneC;
//      @FXML
//      private Button newCourseButton;
//
//      @FXML
//      public void displayNewCoursePage(){
//
//      }
////
////    @FXML
////    private Button updateCourseButton;
////
////    @FXML
////    private Button displayVersionsButton;
////
////    @FXML
////    private Button saveFeaturesButton;
////
////    @FXML
////    private Button userManualButton;
////
////
//    @FXML
//    private Pane userManualPage;
//
//    @FXML
//    private Pane homePage;
//
//    @FXML
//    private Pane newCoursePage;
//
//    @FXML
//    private Pane updateCoursePage;
////
//    @FXML
//    private Pane displayVersionsPage;
//
//    @FXML
//    private Pane saveFeaturesPage;
////
////    @FXML
////    private Pane userManualPage;
//
///*
//    @FXML
//    public void switchScreen(){
//        ActionEvent actionEvent = new ActionEvent();
//        if(event.getSource() == newCourseButton){
//
//        }
//        else if(event.getSource() == updateCourseButton){
//            homePage.setVisible(false);
//            newCoursePage.setVisible(false);
//            updateCoursePage.setVisible(true);
//            displayVersionsPage.setVisible(false);
//            saveFeaturesPage.setVisible(false);
//            userManualPage.setVisible(false);
//        }
//        if(event.getSource() == displayVersionsButton){
//            homePage.setVisible(false);
//            newCoursePage.setVisible(false);
//            updateCoursePage.setVisible(false);
//            displayVersionsPage.setVisible(true);
//            saveFeaturesPage.setVisible(false);
//            userManualPage.setVisible(false);
//        }
//        if(event.getSource() == saveFeaturesButton){
//            homePage.setVisible(false);
//            newCoursePage.setVisible(false);
//            updateCoursePage.setVisible(false);
//            displayVersionsPage.setVisible(false);
//            saveFeaturesPage.setVisible(true);
//            userManualPage.setVisible(false);
//        }
//        if(event.getSource() == userManualButton){
//            homePage.setVisible(false);
//            newCoursePage.setVisible(false);
//            updateCoursePage.setVisible(false);
//            displayVersionsPage.setVisible(false);
//            saveFeaturesPage.setVisible(false);
//            userManualPage.setVisible(true);
//        }
//    }
//
//
//*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (model.isDatabaseConnected()) {
            System.out.println("The database connection has been provided.");

        } else {
            System.out.println("The connection is failed.");
        }
    }}

//    @FXML
//    private void page0(MouseEvent event){
//
//    }
//    @FXML
//    private void page1(MouseEvent event){
//
//    }
//    @FXML
//    private void page2(MouseEvent event){
//
//    }
//    @FXML
//    private void page3(MouseEvent event){
//
//    }
//    @FXML
//    private void page4(MouseEvent event){
//
//    }



//    public void page0(ActionEvent event) throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("page0.fxml"));
//        contentArea.getChildren().removeAll();
//        contentArea.getChildren().setAll(fxml);
//    }
//
//
//    public void page1(ActionEvent event) throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("page1.fxml"));
//        contentArea.getChildren().removeAll();
//        contentArea.getChildren().setAll(fxml);
//    }
//    public void page2(ActionEvent event) throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("page2.fxml"));
//        contentArea.getChildren().removeAll();
//        contentArea.getChildren().setAll(fxml);
//    }
//    public void page3(ActionEvent event) throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("page3.fxml"));
//        contentArea.getChildren().removeAll();
//        contentArea.getChildren().setAll(fxml);
//    }
//    public void page4(ActionEvent event) throws IOException{
//        Parent fxml = FXMLLoader.load(getClass().getResource("page4.fxml"));
//        contentArea.getChildren().removeAll();
//        contentArea.getChildren().setAll(fxml);
// */   }
