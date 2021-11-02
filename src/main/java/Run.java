import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.*;
import java.util.ArrayList;
import java.util.List;

public class Run extends Application {
    private long currentAthleteId = 1L;
    private String filename = null;
    private String path = "";
    private String savePath = "D:\\";
    private double samplingFrequency;

    static {
        nu.pattern.OpenCV.loadShared();
        // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
        samplingFrequency = 0.01;
        VBox root = new VBox(15.0);
        root.setAlignment(Pos.CENTER);
        Button button = new Button("Добавить спортсмена");
        Button button2 = new Button("Показать всех атлетов");
        Button button3 = new Button("Загрузить csv файл");
        button.setOnAction(this::addAthlete);
        button2.setOnAction(this::showAthletes);
        button3.setOnAction(this::addCsvFile);
        root.getChildren().add(button);
        root.getChildren().add(button2);
        root.getChildren().add(button3);

        Scene scene = new Scene(root, 400.0, 400.0);
        stage.setTitle("OpenCV " + Core.VERSION);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.show();
    }

    private void addAthlete(ActionEvent e) {
        List<Node> elements = new ArrayList<>();
        Stage newStage = new Stage();
        newStage.setTitle("Create new athlete");
        Group root = new Group();
        Scene newScene = new Scene(root, 600, 600);
        int textX = 10;
        int inputX = 110;

        Help.createText("Name: ", textX, 40,elements);
        Help.createText("Surname: ", textX, 80,elements);
        Help.createText("Patronymic: ", textX, 120,elements);
        Help.createText("Age: ", textX, 160,elements);
        Help.createText("Gender: ", textX, 195,elements);
        Help.createText("Sport: ", textX, 235,elements);
        Help.createText("Dominant hand: ", textX, 275,elements);
        Help.createText("Qualification: ", textX, 315,elements);

        TextField input1 = new TextField();
        input1.setLayoutX(inputX);
        input1.setLayoutY(20);
        elements.add(input1);

        TextField input2 = new TextField();
        input2.setLayoutX(inputX);
        input2.setLayoutY(60);
        elements.add(input2);

        TextField input3 = new TextField();
        input3.setLayoutX(inputX);
        input3.setLayoutY(100);
        elements.add(input3);

        TextField input4 = new TextField();
        input4.setLayoutX(inputX);
        input4.setLayoutY(140);
        elements.add(input4);

        RadioButton gender1 = new RadioButton(Gender.FEMALE.getTitle());
        RadioButton gender2 = new RadioButton(Gender.MALE.getTitle());
        gender1.setLayoutX(inputX);
        gender1.setLayoutY(180);
        gender2.setLayoutX(200);
        gender2.setLayoutY(180);
        ToggleGroup group = new ToggleGroup();
        gender1.setToggleGroup(group);
        gender2.setToggleGroup(group);
        elements.add(gender1);
        elements.add(gender2);

        TextField input6 = new TextField();
        input6.setLayoutX(inputX);
        input6.setLayoutY(220);
        elements.add(input6);

        RadioButton hand1 = new RadioButton(DominantHand.RIGHT.getTitle());
        RadioButton hand2 = new RadioButton(DominantHand.LEFT.getTitle());
        ToggleGroup group2 = new ToggleGroup();
        hand1.setToggleGroup(group2);
        hand2.setToggleGroup(group2);
        hand1.setLayoutX(inputX);
        hand1.setLayoutY(260);
        hand2.setLayoutX(200);
        hand2.setLayoutY(260);
        elements.add(hand1);
        elements.add(hand2);

        ObservableList<String> langs =
                FXCollections.observableArrayList(Qualification.getAllTitles());
        ComboBox<String> langsComboBox = new ComboBox<String>(langs);
        langsComboBox.setLayoutX(inputX);
        langsComboBox.setLayoutY(300);
        elements.add(langsComboBox);

        Button newButton = new Button();
        newButton.setLayoutX(130);
        newButton.setLayoutY(340);
        newButton.setText("OK");
        elements.add(newButton);

        root.getChildren().addAll(elements);
        newStage.setScene(newScene);
        newStage.show();
        System.out.println("www");
        newButton.setOnAction(ev -> {
            try {
                RadioButton selectionGender = (RadioButton) group.getSelectedToggle();
                RadioButton selectionHand = (RadioButton) group2.getSelectedToggle();
                Athlete athlete = new Athlete();
                athlete.setName(input1.getText());
                athlete.setSurname(input2.getText());
                athlete.setPatronymic(input3.getText());
                athlete.setAge(Integer.parseInt(input4.getText()));
                athlete.setGender(Gender.valueOf(selectionGender.getText()));
                athlete.setSport(input6.getText());
                athlete.setDominantHand(DominantHand.valueOf(selectionHand.getText()));
                athlete.setQualification(Qualification.getValueFromTitle((langsComboBox.getValue())));
                AthleteDAO.getInstance().create(athlete);
                newStage.close();
            } catch (Exception i) {
                System.out.println(i.getMessage());
            }
        });
    }

    private void upgradeAthlete(ActionEvent e) {
        Athlete athlete = AthleteDAO.getInstance().findById(currentAthleteId);
        List<Node> elements = new ArrayList<>();
        Stage newStage = new Stage();
        newStage.setTitle("Create new athlete");
        Group root = new Group();
        Scene newScene = new Scene(root, 600, 600);
        int textX = 10;
        int inputX = 110;

        Help.createText("Name: ", textX, 40,elements);
        Help.createText("Surname: ", textX, 80,elements);
        Help.createText("Patronymic: ", textX, 120,elements);
        Help.createText("Age: ", textX, 160,elements);
        Help.createText("Gender: ", textX, 195,elements);
        Help.createText("Sport: ", textX, 235,elements);
        Help.createText("Dominant hand: ", textX, 275,elements);
        Help.createText("Qualification: ", textX, 315,elements);

        TextField input1 = new TextField();
        input1.setLayoutX(inputX);
        input1.setLayoutY(20);
        input1.setText(athlete.getName());
        elements.add(input1);

        TextField input2 = new TextField();
        input2.setLayoutX(inputX);
        input2.setLayoutY(60);
        input2.setText(athlete.getSurname());
        elements.add(input2);

        TextField input3 = new TextField();
        input3.setLayoutX(inputX);
        input3.setLayoutY(100);
        input3.setText(athlete.getPatronymic());
        elements.add(input3);

        TextField input4 = new TextField();
        input4.setLayoutX(inputX);
        input4.setLayoutY(140);
        input4.setText(String.valueOf(athlete.getAge()));
        elements.add(input4);

        RadioButton gender1 = new RadioButton(Gender.FEMALE.getTitle());
        RadioButton gender2 = new RadioButton(Gender.MALE.getTitle());
        gender1.setLayoutX(inputX);
        gender1.setLayoutY(180);
        gender2.setLayoutX(200);
        gender2.setLayoutY(180);
        ToggleGroup group = new ToggleGroup();

        gender1.setSelected(true);
        gender1.setToggleGroup(group);
        gender2.setToggleGroup(group);
        (athlete.getGender().equals(Gender.FEMALE)?gender1:gender2).setSelected(true);
        elements.add(gender1);
        elements.add(gender2);

        TextField input6 = new TextField();
        input6.setLayoutX(inputX);
        input6.setLayoutY(220);
        input6.setText(athlete.getSport());
        elements.add(input6);

        RadioButton hand1 = new RadioButton(DominantHand.RIGHT.getTitle());
        RadioButton hand2 = new RadioButton(DominantHand.LEFT.getTitle());
        ToggleGroup group2 = new ToggleGroup();
        hand1.setToggleGroup(group2);
        hand2.setToggleGroup(group2);
        (athlete.getDominantHand().equals(DominantHand.RIGHT)?hand1:hand2).setSelected(true);
        hand1.setLayoutX(inputX);
        hand1.setLayoutY(260);
        hand2.setLayoutX(200);
        hand2.setLayoutY(260);
        elements.add(hand1);
        elements.add(hand2);

        ObservableList<String> langs =
                FXCollections.observableArrayList(Qualification.getAllTitles());
        ComboBox<String> langsComboBox = new ComboBox<String>(langs);
        langsComboBox.setLayoutX(inputX);
        langsComboBox.setLayoutY(300);
        langsComboBox.setValue(athlete.getQualification().getTitle());
        elements.add(langsComboBox);

        Button newButton = new Button();
        newButton.setLayoutX(130);
        newButton.setLayoutY(340);
        newButton.setText("OK");
        elements.add(newButton);

        root.getChildren().addAll(elements);
        newStage.setScene(newScene);
        newStage.show();
        System.out.println("www");
        newButton.setOnAction(ev -> {
            try {
                RadioButton selectionGender = (RadioButton) group.getSelectedToggle();
                RadioButton selectionHand = (RadioButton) group2.getSelectedToggle();
                Athlete athlete1 = new Athlete();
                athlete1.setId(athlete.getId());
                athlete1.setName(input1.getText());
                athlete1.setSurname(input2.getText());
                athlete1.setPatronymic(input3.getText());
                athlete1.setAge(Integer.parseInt(input4.getText()));
                athlete1.setGender(Gender.valueOf(selectionGender.getText()));
                athlete1.setSport(input6.getText());
                athlete1.setDominantHand(DominantHand.valueOf(selectionHand.getText()));
                athlete1.setQualification(Qualification.getValueFromTitle((langsComboBox.getValue())));
                AthleteDAO.getInstance().update(athlete1);
                newStage.close();
            } catch (Exception i) {
                System.out.println(i.getMessage());
            }
        });
    }

    private void showAthletes(ActionEvent e) {
        Stage newStage = new Stage();
        newStage.setTitle("All athletes");
        Group root = new Group();
        Scene newScene = new Scene(root, 400, 400);
        List<Athlete> athletes = AthleteDAO.getInstance().findAll();
        Text text1;
        int x = 30;
        int y = 30;
        List<Text> texts = new ArrayList<>();
        for (Athlete athlete : athletes) {
            text1 = new Text();
            text1.setText(athlete.toString());
            text1.setLayoutX(x);
            text1.setLayoutY(y);
            texts.add(text1);
            y += 40;
        }
        root.getChildren().addAll(texts);
        newStage.setScene(newScene);
        newStage.show();
    }

    private void addCsvFile(ActionEvent e) {
        Stage stage = new Stage();
        stage.setTitle("add");
        final FileChooser fileChooser = new FileChooser();
        setFilters(fileChooser);
        // fileChooser.setInitialDirectory(new File(savePath+AthleteDAO.getInstance().findById(currentAthleteId).getSurname()+"\\"));
        filename = fileChooser.showOpenDialog(stage).toPath().toString();
        try {
            System.out.println(filename);
        /*    List<String> csvFiles = AthleteDAO.getInstance().findAllCsvFiles();
            boolean isAlreadyExisted = false;
            for(String s:csvFiles){
                if(s.equals(filename)){
                    isAlreadyExisted = true;
                }
            }
            if(!isAlreadyExisted){
                Athlete athlete = AthleteDAO.getInstance().findById(currentAthleteId);
            CSVReader reader = new CSVReader(new FileReader(filename));
            String csv = "D:\\" + athlete.getName() + athlete.getId() + "file.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
            List<String[]> allRows = reader.readAll();
            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                writer.writeNext(row);
            }
            AthleteDAO.getInstance().addCsvFile(athlete.getId(), csv);
            writer.close();
            }*/

            start4(stage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
    }

    private void start4(Stage primaryStage) throws Exception {
        //Athlete athlete = AthleteDAO.getInstance().findById(currentAthleteId);
        primaryStage.setTitle("Interface of \"Start Analyzing\" button");

        Group root = new Group();
        Text text1 = new Text();
        //  text1.setText(athlete.getSurname()+", Exercise name");
        text1.setLayoutX(10);
        text1.setLayoutY(20);

        LineChart<Number, Number> numberLineChart = new LineChart<>(new NumberAxis(), new NumberAxis());
        numberLineChart.setCreateSymbols(false);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Fz");
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Isoline");
        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();

        double i = 0;
        double step = samplingFrequency;
        ArrayList<Double> fzList = CSVReader5.readFz(filename);
        fzList = CSVReader5.cutFz(fzList);
        double[] arr = CSVReader5.findMas(fzList);
        double mas = arr[0];
        Double isoLine = arr[1];
        for (Double fz : fzList) {
            datas.add(new XYChart.Data(i, fz));
            datas2.add(new XYChart.Data(i, isoLine));
            i += step;
        }
        series1.setData(datas);
        series2.setData(datas2);

        Text text2 = new Text();
        text2.setText("Athlete weight, kg: "+mas);
        text2.setLayoutX(150);
        text2.setLayoutY(20);

        Text text3 = new Text();
        text3.setText("Sampling frequency, Hz: ");
        text3.setLayoutX(320);
        text3.setLayoutY(20);

        TextField input3 = new TextField();
        input3.setLayoutX(460);
        input3.setLayoutY(5);

        Button upgradeButton = new Button();
        upgradeButton.setLayoutX(620);
        upgradeButton.setLayoutY(5);
        upgradeButton.setText("Upgrade");

        numberLineChart.getData().addAll(series1, series2);
        numberLineChart.setLayoutY(40);
        numberLineChart.setMinSize(700,400);
        root.getChildren().addAll(text1,text2, text3, input3,upgradeButton, numberLineChart);
        Scene scene = new Scene( root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
        upgradeButton.setOnAction(ev -> {
            samplingFrequency = Double.parseDouble(input3.getText());
            try {
                start4(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

