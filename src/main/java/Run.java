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
        int y = 300;
        langsComboBox.setLayoutY(300);
       elements.add(langsComboBox);


        y+=40;

        Button newButton = new Button();
        newButton.setLayoutX(130);
        newButton.setLayoutY(y);
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
                System.out.println("qqq");
                System.out.println(input1.getText());
                Athlete athlete = new Athlete();
                athlete.setName(input1.getText());
                athlete.setSurname(input2.getText());
                athlete.setPatronymic(input3.getText());
                athlete.setAge(Integer.parseInt(input4.getText()));
                athlete.setGender(Gender.valueOf(selectionGender.getText()));
                athlete.setSport(input6.getText());
                athlete.setDominantHand(DominantHand.valueOf(selectionHand.getText()));
                athlete.setQualification(Qualification.getValueFromTitle((langsComboBox.getValue())));
                System.out.println(athlete.toString());
                AthleteDAO.getInstance().create(athlete);
            } catch (Exception i) {
                System.out.println(i.getMessage());
            }
        });
    }
/*
    private void upgradeAthlete(ActionEvent e) {
        List<Node> elements = new ArrayList<>();
        Stage newStage = new Stage();
        newStage.setTitle("Chose parameters");
        Group root = new Group();
        Scene newScene = new Scene(root, 600, 600);

        Text text1 = new Text();
        text1.setText("Имя:");
        text1.setLayoutX(10);
        text1.setLayoutY(30);
        elements.add(text1);

        Text text2 = new Text();
        text2.setText("Фамилия:");
        text2.setLayoutX(10);
        text2.setLayoutY(70);
        elements.add(text2);

        Text text3 = new Text();
        text3.setText("Отчество:");
        text3.setLayoutX(10);
        text3.setLayoutY(110);
        elements.add(text3);

        Text text4 = new Text();
        text4.setText("Возраст:");
        text4.setLayoutX(10);
        text4.setLayoutY(150);
        elements.add(text4);

        Text text5 = new Text();
        text5.setText("Пол:");
        text5.setLayoutX(10);
        text5.setLayoutY(190);
        elements.add(text5);

        Text text6 = new Text();
        text6.setText("Вид спорта:");
        text6.setLayoutX(10);
        text6.setLayoutY(230);
        elements.add(text6);

        Text text7 = new Text();
        text7.setText("Ведущая рука:");
        text7.setLayoutX(10);
        text7.setLayoutY(270);
        elements.add(text7);

        TextField input1 = new TextField();
        input1.setLayoutX(120);
        input1.setLayoutY(30);
        elements.add(input1);

        TextField input2 = new TextField();
        input2.setLayoutX(120);
        input2.setLayoutY(70);
        elements.add(input2);

        TextField input3 = new TextField();
        input3.setLayoutX(120);
        input3.setLayoutY(110);
        elements.add(input3);

        TextField input4 = new TextField();
        input4.setLayoutX(120);
        input4.setLayoutY(150);
        elements.add(input4);


        RadioButton gender1 = new RadioButton(Gender.FEMALE.getTitle());
        RadioButton gender2 = new RadioButton(Gender.MALE.getTitle());
        gender1.setLayoutX(120);
        gender1.setLayoutY(190);
        gender2.setLayoutX(200);
        gender2.setLayoutY(190);
        ToggleGroup group = new ToggleGroup();
        gender1.setToggleGroup(group);
        gender2.setToggleGroup(group);
        elements.add(gender1);
        elements.add(gender2);

        TextField input6 = new TextField();
        input6.setLayoutX(120);
        input6.setLayoutY(230);
        elements.add(input6);

        RadioButton hand1 = new RadioButton(DominantHand.RIGHT.getTitle());
        RadioButton hand2 = new RadioButton(DominantHand.LEFT.getTitle());
        ToggleGroup group2 = new ToggleGroup();
        hand1.setToggleGroup(group2);
        hand2.setToggleGroup(group2);
        hand1.setLayoutX(120);
        hand1.setLayoutY(270);
        hand2.setLayoutX(200);
        hand2.setLayoutY(270);
        elements.add(hand1);
        elements.add(hand2);

      //  ObservableList<String> langs = FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python");
      //  ComboBox<String> langsComboBox = new ComboBox<String>(langs);
      //  langsComboBox.setValue("Java"); // устанавливаем выбранный элемент по умолчанию

       // Label lbl = new Label();

        // получаем выбранный элемент
     //   langsComboBox.setOnAction(event -> lbl.setText(langsComboBox.getValue()));

    //    FlowPane root = new FlowPane(10, 10, langsComboBox, lbl);
      //  ToggleGroup group3 = new ToggleGroup();
        ObservableList<String> langs =
                FXCollections.observableArrayList(Qualification.getAllTitles());
        ComboBox<String> langsComboBox = new ComboBox<String>(langs);
        langsComboBox.setLayoutX(120);
        int y = 310;
        langsComboBox.setLayoutY(310);
      //  Label lbl1 = new Label();
      //   langsComboBox.setOnAction(event -> lbl1.setText(langsComboBox.getValue()));
        elements.add(langsComboBox);
     // elements.add(lbl1);
       // FlowPane root = new FlowPane(10, 10, langsComboBox, lbl);
      //  int y = 310;
     /*   for(Qualification q:mas){
            RadioButton qButton = new RadioButton(q.getTitle());
            qButton.setToggleGroup(group3);
            qButton.setLayoutX(120);
            qButton.setLayoutY(y);
            y+=40;
            elements.add(qButton);
        }
        Text text8 = new Text();
        text8.setText("Квалификация:");
        text8.setLayoutX(10);
        text8.setLayoutY(y);
        elements.add(text8);

      //  TextField input8 = new TextField();
     //   input8.setLayoutX(120);
     //   input8.setLayoutY(y);
      //  elements.add(input8);

        y+=40;

        Button newButton = new Button();
        newButton.setLayoutX(130);
        newButton.setLayoutY(y);
        newButton.setText("OK");
        elements.add(newButton);

        //   root.getChildren().addAll(newButton, input1, input2, input3, input4,  input6,  input8,
        //       text1, text2, text3, text4, text5, text6, text7,text8,gender1,gender2,hand1,hand2,
        //      buttons.);
        root.getChildren().addAll(elements);
        newStage.setScene(newScene);
        newStage.show();
        System.out.println("www");
        newButton.setOnAction(ev -> {
            try {
               // langsComboBox.setOnAction(event -> lbl1.setText(langsComboBox.getValue()));
                RadioButton selectionGender = (RadioButton) group.getSelectedToggle();
                RadioButton selectionHand = (RadioButton) group2.getSelectedToggle();
                System.out.println("qqq");
                System.out.println(input1.getText());
                Athlete athlete = new Athlete();
                athlete.setName(input1.getText());
                athlete.setSurname(input2.getText());
                athlete.setPatronymic(input3.getText());
                athlete.setAge(Integer.parseInt(input4.getText()));
                athlete.setGender(Gender.valueOf(selectionGender.getText()));
                athlete.setSport(input6.getText());
                athlete.setDominantHand(DominantHand.valueOf(selectionHand.getText()));
             //   athlete.setQualification(Qualification.valueOf(input8.getText()));
                System.out.println(athlete.toString());
                AthleteDAO.getInstance().create(athlete);
            } catch (Exception i) {
                System.out.println(i.getMessage());
            }
        });
    }
*/
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

