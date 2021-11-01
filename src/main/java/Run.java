import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    static {
        nu.pattern.OpenCV.loadShared();
        // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
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
        Stage newStage = new Stage();
        newStage.setTitle("Chose parameters");
        Group root = new Group();
        Scene newScene = new Scene(root, 400, 400);
        Button newButton = new Button();
        newButton.setLayoutX(130);
        newButton.setLayoutY(350);
        newButton.setText("OK");

        Text text1 = new Text();
        text1.setText("Имя:");
        text1.setLayoutX(10);
        text1.setLayoutY(30);
        Text text2 = new Text();
        text2.setText("Фамилия:");
        text2.setLayoutX(10);
        text2.setLayoutY(70);
        Text text3 = new Text();
        text3.setText("Отчество:");
        text3.setLayoutX(10);
        text3.setLayoutY(110);
        Text text4 = new Text();
        text4.setText("День рождения:");
        text4.setLayoutX(10);
        text4.setLayoutY(150);
        Text text5 = new Text();
        text5.setText("Пол:");
        text5.setLayoutX(10);
        text5.setLayoutY(190);
        Text text6 = new Text();
        text6.setText("Вид спорта:");
        text6.setLayoutX(10);
        text6.setLayoutY(230);
        Text text7 = new Text();
        text7.setText("Ведущая рука:");
        text7.setLayoutX(10);
        text7.setLayoutY(270);
        Text text8 = new Text();
        text8.setText("Квалификация:");
        text8.setLayoutX(10);
        text8.setLayoutY(310);

        TextField input1 = new TextField();
        input1.setLayoutX(120);
        input1.setLayoutY(30);
        TextField input2 = new TextField();
        input2.setLayoutX(120);
        input2.setLayoutY(70);
        TextField input3 = new TextField();
        input3.setLayoutX(120);
        input3.setLayoutY(110);
        TextField input4 = new TextField();
        input4.setLayoutX(120);
        input4.setLayoutY(150);
        TextField input5 = new TextField();
        input5.setLayoutX(120);
        input5.setLayoutY(190);
        TextField input6 = new TextField();
        input6.setLayoutX(120);
        input6.setLayoutY(230);
        TextField input7 = new TextField();
        input7.setLayoutX(120);
        input7.setLayoutY(270);
        TextField input8 = new TextField();
        input8.setLayoutX(120);
        input8.setLayoutY(310);

        root.getChildren().addAll(newButton, input1, input2, input3, input4, input5, input6, input7, input8,
                text1, text2, text3, text4, text5, text6, text7, text8);
        newStage.setScene(newScene);
        newStage.show();
        System.out.println("www");
        newButton.setOnAction(ev -> {
            try {
                System.out.println("qqq");
                System.out.println(input1.getText());
                Athlete athlete = new Athlete();
                athlete.setName(input1.getText());
                athlete.setSurname(input2.getText());
                athlete.setPatronymic(input3.getText());
                athlete.setAge(Integer.parseInt(input4.getText()));
                athlete.setGender(Gender.valueOf(input5.getText()));
                athlete.setSport(input6.getText());
                athlete.setDominantHand(DominantHand.valueOf(input7.getText()));
                athlete.setQualification(Qualification.valueOf(input8.getText()));
                System.out.println(athlete.toString());
                AthleteDAO.getInstance().create(athlete);
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
        Text text1 = new Text();
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
        filename = fileChooser.showOpenDialog(stage).toPath().toString();
        try {
            System.out.println(filename);
           // Athlete athlete = AthleteDAO.getInstance().findById(currentAthleteId);
          /*  CSVReader reader = new CSVReader(new FileReader(filename));
            String csv = "D:\\" + athlete.getName() + athlete.getId() + "file.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
            List<String[]> allRows = reader.readAll();
            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                writer.writeNext(row);
            }
            AthleteDAO.getInstance().addCsvFile(athlete.getId(), csv);
            writer.close();*/
          start4(stage);
       // } catch (IOException ignored) {
       //     System.out.println(ignored.getMessage());
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
        Athlete athlete = AthleteDAO.getInstance().findById(currentAthleteId);
        primaryStage.setTitle("Interface of \"Start Analyzing\" button");

        Group root = new Group();
        Text text1 = new Text();
        text1.setText(athlete.getSurname()+", Exercise name");
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
        double step = 0.01;
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
    }
}
