import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.time.LocalDate;

public class Run extends Application{
        private String filename = null;
        private String path;
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
            Button button2 = new Button("Преобразование из RGB в Gray");
            Button button3 = new Button("Перемещение");
            Button button4 = new Button("Масштабирование");
            Button button7 = new Button("Поворот");
            Button button5 = new Button("Сдвиг");
            Button button6 = new Button("Вывод каждого канала изображения на экран");
            Button button8 = new Button("Выбрать изображение");
            Button button17 = new Button("Сохранить как");
            button.setOnAction(this::addAthlete);
            button2.setOnAction(this::convertingFromRGBtoGray);
            button3.setOnAction(this::moving);
            button4.setOnAction(this::scaling);
            button7.setOnAction(this::turn);
            button5.setOnAction(this::shift);
            button6.setOnAction(this::outputEachChannel);
            button8.setOnAction(e -> {
                setFilters(fileChooser);
                filename = fileChooser.showOpenDialog(stage).toPath().toString();
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

            int movingX = Integer.parseInt(input1.getText());
        }

        private void convertingFromRGBtoGray(ActionEvent e) {
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
            // Отображаем в отдельном окне
            Mat img2 = new Mat();
            Imgproc.cvtColor(img, img2, Imgproc.COLOR_BGR2GRAY);
            CvUtilsFX.showImage(img2, "Gray");
            // Сохраняем изображение в файл
            CvUtilsFX.saveImage(img2, path+"\\grayExample.jpg");
        }
        private void moving(ActionEvent e) {
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
        private void scaling(ActionEvent e) {
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
                    double movingX = Double.parseDouble(input1.getText());
                    double movingY = Double.parseDouble(input2.getText());
                    double movX = movingX;
                    if (movX <= 0) {
                        movX = 1;
                    }
                    double movY = movingY;
                    if (movY <= 0) {
                        movY = 1;
                    }
                    // Трансформация масштабирования
                    Mat M2 = new Mat(2, 3, CvType.CV_32FC1);
                    M2.put(0, 0,
                            movX, 0, 0,
                            0, movY, 0
                    );
                    Mat img3 = new Mat();
                    Imgproc.warpAffine(img, img3, M2,
                            new Size(img.width()*movX, img.height()*movY),
                            Imgproc.INTER_CUBIC, Core.BORDER_CONSTANT,
                            new Scalar(0, 0, 0, 0));
                    // Отображаем в отдельном окне
                    CvUtilsFX.showImage(img3, "Масштабирование");
                    // Сохраняем изображение в файл
                    CvUtilsFX.saveImage(img3, path+"scalingExample.jpg");
                } catch (NumberFormatException ignored){}
                newStage.close();
            });
        }
        private void turn(ActionEvent e) {
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
            Scene scene19 = new Scene(root, 300, 110);
            Button btn = new Button();
            btn.setLayoutX(130);
            btn.setLayoutY(50);
            btn.setText("OK");
            Text text = new Text();
            text.setText("angle:");
            text.setLayoutX(10);
            text.setLayoutY(30);
            TextField input = new TextField();
            input.setLayoutX(90);
            input.setLayoutY(10);
            root.getChildren().addAll(btn, input, text);
            newStage.setScene(scene19);
            newStage.show();
            btn.setOnAction(event12 -> {
                try {
                    int angle = Integer.parseInt(input.getText());
                    // Обрабатываем изображение
                    // Трансформация вращения
                    Mat M = Imgproc.getRotationMatrix2D(
                            new Point(img.width() / 2., img.height() / 2.), angle, 1);
                    Mat img2 = new Mat();
                    Imgproc.warpAffine(img, img2, M,
                            new Size(img.width()*2, img.height()*2),
                            Imgproc.INTER_LINEAR, Core.BORDER_TRANSPARENT,
                            new Scalar(0, 0, 0, 255));
                    // Отображаем в отдельном окне
                    CvUtilsFX.showImage(img2, "Вращение на ... градусов");
                    // Сохраняем изображение в файл
                    CvUtilsFX.saveImage(img2, path+"turnExample.jpg");
                } catch (NumberFormatException | CvException ignored) { }
                newStage.close();
            });
        }
        private void shift(ActionEvent e) {
            // Загружаем изображение из файла
            Mat  img;
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
            Scene scene19 = new Scene(root, 300, 110);
            Button btn = new Button();
            btn.setLayoutX(130);
            btn.setLayoutY(50);
            btn.setText("OK");
            Text text = new Text();
            text.setText("parameter:");
            text.setLayoutX(10);
            text.setLayoutY(30);
            TextField input = new TextField();
            input.setLayoutX(90);
            input.setLayoutY(10);
            root.getChildren().addAll(btn, input, text);
            newStage.setScene(scene19);
            newStage.show();
            btn.setOnAction(event12 -> {
                try {
                    int angle = Integer.parseInt(input.getText());
                    // Обрабатываем изображение
                    // Трансформация сдвига
                    Mat M3 = new Mat(2, 3, CvType.CV_32FC1);
                    M3.put(0, 0,
                            1, angle, 10,
                            0, 1, 10
                    );
                    Mat img4 = new Mat();
                    Imgproc.warpAffine(img, img4, M3,
                            new Size(img.width() * 1.5, img.height() * 1.5),
                            Imgproc.INTER_LINEAR, Core.BORDER_CONSTANT,
                            new Scalar(0, 0, 0, 0));
                    // Отображаем в отдельном окне
                    CvUtilsFX.showImage(img4, "Сдвиг");
                    // Сохраняем изображение в файл
                    CvUtilsFX.saveImage(img4, path+"shiftExample.jpg");
                } catch (NumberFormatException | CvException ignored) { }
                newStage.close();
            });
        }
        private void outputEachChannel(ActionEvent e) {
            // Загружаем изображение из файла
            Mat  img;
            try {
                img = Imgcodecs.imread(filename);
                if (img.empty()) {
                    throw new Exception("Не удалось загрузить изображение");
                }
            } catch (Exception e1){
                System.out.println(e1.getMessage());
                return;
            }
            int type = BufferedImage.TYPE_BYTE_GRAY;
            if (img.channels() > 1) {
                type = BufferedImage.TYPE_3BYTE_BGR;
            }
            BufferedImage image = new BufferedImage(img.cols(), img.rows(), type);

            img.get(0, 0, ((DataBufferByte)image.getRaster().getDataBuffer()).getData());
            // Обрабатываем изображение
            int width = image.getWidth();
            int height = image.getHeight();
            for(int y=0;y<height;y++) {
                for (int x = 0; x < width; x++) {
                    int p = image.getRGB(x, y);
                    int a = (p >> 24) & 0xff;
                    int g = (p >> 8) & 0xff;
                    p = (a << 24) | (0) | (g << 8);
                    image.setRGB(x, y, p);
                }
            }

            Mat im = CvUtilsFX.BufferedImageToMat(image);
            CvUtilsFX.showImage(im, "green" );
            // Сохраняем изображение в файл
            CvUtilsFX.saveImage(im, path+"exampleChanel"  + ".jpg");
            im.release();
            // Обрабатываем изображение
            int width1 = image.getWidth();
            int height1 = image.getHeight();
            for(int y=0;y<height1;y++) {
                for (int x = 0; x < width1; x++) {

                    int p = image.getRGB(x, y);
                    int a = (p >>24) & 0x0ff;
                    int g = (p>> 8) & 0x00ff;
                    p = (a << 16) | (0) | (g<<8);
                    image.setRGB(x, y, p);
                }
            }

            Mat im2 = CvUtilsFX.BufferedImageToMat(image);
            CvUtilsFX.showImage(im2, "Red");

// Обрабатываем изображение
            int width2 = image.getWidth();
            int height2 = image.getHeight();
            for(int y=0;y<height2;y++) {
                for (int x = 0; x < width2; x++) {

                    int p = image.getRGB(x, y);
                    int a = (p >>24) & 0x0ff;
                    int g = (p>> 8) & 0x00ff;
                    p = (a) | (0) | (g <<8);
                    image.setRGB(x, y, p);
                }
            }

            Mat im1 = CvUtilsFX.BufferedImageToMat(image);
            CvUtilsFX.showImage(im1, "Blue");
        }
        private void outputOnDisplay(ActionEvent e) {
            // Загружаем изображение из файла
            Mat  img;
            try {
                img = Imgcodecs.imread(filename);
                if (img.empty()) {
                    throw new Exception("Не удалось загрузить изображение");
                }
            } catch (Exception e1){
                System.out.println(e1.getMessage());
                return;
            }
            // Отображаем в отдельном окне
            CvUtilsFX.showImage(img, "Текст в заголовке окна");
        }
        private void setFilters(FileChooser chooser) {
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg")
            );
        }

    }
