import au.com.bytecode.opencsv.CSVWriter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import au.com.bytecode.opencsv.CSVReader;

import javax.sound.sampled.Line;
import java.io.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Run extends Application{
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
            final FileChooser fileChooser = new FileChooser();
            root.setAlignment(Pos.CENTER);
            Button button = new Button("Добавить спортсмена");
            Button button2 = new Button("Показать всех атлетов");
            Button button3 = new Button("Загрузить csv файл");
            Button button4 = new Button("Масштабирование");
            Button button7 = new Button("Поворот");
            Button button5 = new Button("Сдвиг");
            Button button6 = new Button("Вывод каждого канала изображения на экран");
            Button button8 = new Button("Выбрать изображение");
            Button button17 = new Button("Сохранить как");
            button.setOnAction(this::addAthlete);
            button2.setOnAction(this::showAthletes);
            button3.setOnAction(this::addCsvFile);
            button8.setOnAction(e -> {
                try {
                    start4(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            button17.setOnAction(e -> {
                File file1 = fileChooser.showSaveDialog(stage);
                String path1 = file1.getPath();
                path = file1.getParent();
                CvUtilsFX.saveImage(Imgcodecs.imread(filename), path1+".png");
            });
            root.getChildren().add(button);
            root.getChildren().add(button2);
            root.getChildren().add(button3);
            root.getChildren().add(button4);
            root.getChildren().add(button7);
            root.getChildren().add(button5);
            root.getChildren().add(button6);
            root.getChildren().add(button8);
            root.getChildren().add(button17);

            Scene scene = new Scene(root, 400.0, 400.0);
            stage.setTitle("OpenCV " + Core.VERSION);
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> Platform.exit());
            stage.show();
        }
        private void addAthlete(ActionEvent e){
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
                    athlete.setBirthday(LocalDate.parse(input4.getText()));
                    athlete.setGender(Gender.valueOf(input5.getText()));
                    athlete.setSport(input6.getText());
                    athlete.setDominantHand(DominantHand.valueOf(input7.getText()));
                    athlete.setQualification(input8.getText());
                    System.out.println(athlete.toString());
                    AthleteDAO.getInstance().create(athlete);
                }
                catch (Exception ignored){
                    System.out.println(ignored.getMessage());
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
            for (Athlete athlete:athletes) {
                text1 = new Text();
                text1.setText(athlete.toString());
                text1.setLayoutX(x);
                text1.setLayoutY(y);
                texts.add(text1);
                y+=40;
            }
            root.getChildren().addAll(texts);
            newStage.setScene(newScene);
            newStage.show();
        }
        private void addCsvFile2(ActionEvent e) {
            // Загружаем изображение из файла
            Mat img;
            try {
                img = Imgcodecs.imread(filename);
                if (img.empty()) {
                    throw new Exception("Не удалось загрузить изображение");
                }
            } catch (Exception e1){
                System.out.println(e1.getMessage());
                return;
            }
            Stage newStage = new Stage();
            newStage.setTitle("Chose parameters");
            Group root = new Group();
            Scene newScene = new Scene(root, 300, 130);
            Button newButton = new Button();
            newButton.setLayoutX(130);
            newButton.setLayoutY(100);
            newButton.setText("OK");

            Text text1 = new Text();
            text1.setText("X:");
            text1.setLayoutX(10);
            text1.setLayoutY(30);
            Text text2 = new Text();
            text2.setText("Y:");
            text2.setLayoutX(10);
            text2.setLayoutY(70);
            TextField input1 = new TextField();
            input1.setLayoutX(120);
            input1.setLayoutY(10);
            TextField input2 = new TextField();
            input2.setLayoutX(120);
            input2.setLayoutY(50);

            root.getChildren().addAll(newButton, input1, input2, text1, text2);
            newStage.setScene(newScene);
            newStage.show();
            newButton.setOnAction(event16 -> {
                try {
                    int movingX = Integer.parseInt(input1.getText());
                    int movingY = Integer.parseInt(input2.getText());
                    int movX = movingX;
                    if (movX <= 0) {
                        movX = 0;
                    }
                    int movY = movingY;
                    if (movY >= 0) {
                        movY = 0;
                    }
                    Mat mat = new Mat(2, 3, CvType.CV_32FC1);
                    mat.put(0, 0, 1, 0, movX, 0, 1, -movY
                    );
                    Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2BGRA);
                    Mat matImage2 = new Mat();
                    Imgproc.warpAffine(img, matImage2, mat,
                            new Size(img.width() + Math.abs(movingX), img.height() + Math.abs(movingY)), Imgproc.INTER_LINEAR, Core.BORDER_CONSTANT, new Scalar(0, 0, 0, 255));
                    // Отображаем в отдельном окне
                    CvUtilsFX.showImage(matImage2, "Смещение");
                    // Сохраняем изображение в файл
                    CvUtilsFX.saveImage(matImage2, path+"movingExample.jpg");
                } catch (NumberFormatException ignored){}
                newStage.close();
            });
        }

    private void addCsvFile(ActionEvent e){
        Stage stage = new Stage();
        stage.setTitle("add");
        final FileChooser fileChooser = new FileChooser();
        setFilters(fileChooser);
        filename = fileChooser.showOpenDialog(stage).toPath().toString();
        try {
            currentAthleteId  = 1;
            Athlete athlete = AthleteDAO.getInstance().findById(currentAthleteId);
            CSVReader reader = new CSVReader(new FileReader(filename));
            String csv = "D:\\"+athlete.getName()+athlete.getId()+"file.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
            List<String[]> allRows = reader.readAll();
            for(int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                writer.writeNext(row);
            }
            AthleteDAO.getInstance().addCsvFile(athlete.getId(), csv);
            writer.close();
        } catch (IOException ignored){
            System.out.println(ignored.getMessage());
        }
    }
        private void setFilters(FileChooser chooser) {
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV", "*.csv")
            );
        }

    public void start4(Stage primaryStage) throws Exception{
        List<Jump> list = CSVReader5.readJumps("AbalakovJump.csv");
        list.forEach(System.out::println);
       //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("JavaFX Chart (Series)");

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<>(x,y);
        numberLineChart.setCreateSymbols(false);
        numberLineChart.setTitle("Series");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("sin(x)");
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        double i = 0;
        for(Jump jump:list){
            System.out.println(i+" "+jump.getFx());
            datas.add(new XYChart.Data(i,jump.getFx()));
            i+=0.1;
        }
        series1.setData(datas);

        Scene scene = new Scene(numberLineChart, 600,600);
        numberLineChart.getData().add(series1);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    }
