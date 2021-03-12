import java.text.DecimalFormat;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.*;
import javafx.stage.DirectoryChooser;
import java.nio.charset.StandardCharsets;

public class Filter extends Application {
    public class TestFile {
        // set private variables
        private String filename;
        private double spamProbability;
        private String actualClass;

        // set the test file class
        public TestFile(String filename, double spamProbability, String actualClass) {
            this.filename = filename;
            this.spamProbability = spamProbability;
            this.actualClass = actualClass;
        }

        public String getFilename() {
            return this.filename;
        }

        public double getSpamProbability() {
            return this.spamProbability;
        }

        public String getSpamProbRounded() {
            DecimalFormat df = new DecimalFormat("0.00000");
            return df.format(this.spamProbability);
        }

        public String getActualClass() {
            return this.actualClass;
        }

        public void setFilename(String value) {
            this.filename = value;
        }

        public void setSpamProbability(double val) {
            this.spamProbability = val;
        }

        public void setActualClass(String value) {
            this.actualClass = value;
        }
    }

    // create a data source class
    public class DataSource {

        // create a string that represents a directory
        private String path;
        private int total;
        private String classes;
        List<String> fn = new ArrayList<String>();
        List<Double> totalwords = new ArrayList<Double>();
        List<String> className = new ArrayList<String>();

        // create a constructor class that sets the directory
        public DataSource(String directory, String cn, int totalW) {
            // set the path to the directory
            this.path = directory;

            // set the total value of words found in the doc
            this.total = totalW;

            // set the classname
            this.classes = cn;
        }

        public void dataListing() {

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Filter");

        // Use table function to allow tables
        TableView tableView = new TableView();

        Scene scene = new Scene(new Group());
        primaryStage.setWidth(900);
        primaryStage.setHeight(700);

        tableView.setEditable(true);

        // columns section
        // create columns that fit the description of the program
        // class column, will be use to display the class of each class
        TableColumn<TestFile, String> ClassColumn = new TableColumn<>("Actual Class");
        ClassColumn.setCellValueFactory(new PropertyValueFactory<>("actualClass"));

        // class column, will be use to display the class of each spam
        TableColumn<TestFile, String> SpamColumn = new TableColumn<>("Spam Probability");
        SpamColumn.setCellValueFactory(new PropertyValueFactory<>("spamProbability"));

        // class column, will be use to display the class of each file
        TableColumn<TestFile, String> FileColumn = new TableColumn<>("File");
        FileColumn.setCellValueFactory(new PropertyValueFactory<>("filename"));

        // add all columns to the table
        tableView.getColumns().addAll(FileColumn, ClassColumn, SpamColumn);

        // create a VBox emote to display the table
        VBox vbox = new VBox();

        // set the tanle preferences starting with the table size
        tableView.setMinSize(900, 500);

        // the length of the file column
        tableView.resizeColumn(FileColumn, 300);

        // the length of the class column
        tableView.resizeColumn(ClassColumn, 50);

        // the length of the spam column
        tableView.resizeColumn(SpamColumn, 300);

        // Creating grid window
        GridPane myGrid = new GridPane();

        // set the alignment to the left
        myGrid.setAlignment(Pos.BASELINE_LEFT);

        // set the gaps and lengths for each text box
        myGrid.setHgap(5);
        myGrid.setVgap(10);

        myGrid.setPadding(new Insets(25, 25, 25, 25));

        // Labels for each input
        Label accuracyName = new Label("Accuracy:"); // Accuracy

        // Labels for each input
        Label precisionName = new Label("Precision:"); // Precision

        // Inputs
        TextField txtAcc = new TextField(); // txt for accuracy

        TextField txtPre = new TextField(); // txt for precision

        // follow the order in the example
        // accuracy
        myGrid.add(accuracyName, 0, 0);
        myGrid.add(txtAcc, 1, 0);

        // precision
        myGrid.add(precisionName, 0, 3);
        myGrid.add(txtPre, 1, 3);

        // set the table and each individual children function under vbox
        vbox.getChildren().addAll(tableView, myGrid);

        // use this to allow the program to out put the table
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        // set up the window for the Filter program
        primaryStage.setScene(scene);

        // display the program as a whole
        primaryStage.show();
    }

    // run thr program through this
    public static void main(String[] args) {
        launch(args);
    }
}
