import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application
{
    //
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Pane root1 = new Pane();
        Scene scene1 = new Scene(root1, 350, 200);

        Label lblID = lbl("ID:", 0, 10, 10, 30);
        Label lblName = lbl("Name:", 0, 45, 10, 30);
        Label lblMobile = lbl("Mobile:", 0, 80, 10, 30);

        TextField txfID = txf(45, 10, 40, 30);
        TextField txfName = txf(45, 45, 40, 30);
        TextField txfMobile = txf(45, 80, 40, 30);

        Button btnAdd = btn("Add", 0, 120, 15, 30);
        Button btnViewStudent = btn("View Stud", 50, 120, 30, 30);
        Button btnSearch = btn("Search", 130, 120, 35, 30);
        Button btnDelete = btn("Delete", 195, 120, 25, 30);

        primaryStage.setTitle("Student Manager");
        primaryStage.setScene(scene1);
        primaryStage.show();

        root1.getChildren().add(lblID);
        root1.getChildren().add(lblName);
        root1.getChildren().add(lblMobile);

        root1.getChildren().add(txfID);
        root1.getChildren().add(txfName);
        root1.getChildren().add(txfMobile);

        root1.getChildren().add(btnAdd);
        root1.getChildren().add(btnViewStudent);
        root1.getChildren().add(btnSearch);
        root1.getChildren().add(btnDelete);
    }

    //
    public TextField txf(int x, int y, int w, int h) 
    {
        TextField txf = new TextField();
        txf.setMinSize(w, h);
        txf.setLayoutX(x);
        txf.setLayoutY(y);
        return txf;
    }

    //
    public Label lbl(String text, int x, int y, int w, int h) 
    {
        Label lbl = new Label();
        lbl.setText(text);
        lbl.setMinSize(w, h);
        lbl.setLayoutX(x);
        lbl.setLayoutY(y);
        return lbl;
    }

    //
    public Button btn(String text, int x, int y, int w, int h) {
        Button btn = new Button();
        btn.setText(text);
        btn.setMinSize(w, h);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        return btn;
    }
}