package com.example.tellmekeyparser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class HelloController {

    SpkParser spkParser = new SpkParser();
    c2sParser c2sParser = new c2sParser();

    int count = 0;

    private String SPKfilename;
    private String s2cfilename;

    public String getSPKfilename() {
        return SPKfilename;
    }

    public String getS2cfilename() {
        return s2cfilename;
    }

    @FXML
    private Label welcomeText0;
    @FXML
    private Label welcomeText1;
    @FXML
    private Label welcomeText2;
    @FXML
    private Label welcomeText3;
    @FXML
    private Label welcomeText4;
    @FXML
    private Label welcomeText5;
    @FXML
    private Label welcomeText6;
    @FXML
    private Label welcomeText7;
    @FXML
    private Label welcomeText8;
    @FXML
    private Label welcomeText9;
    @FXML
    private Label welcomeText10;
    @FXML
    private Label welcomeText11;
    @FXML
    private Label welcomeText12;
    @FXML
    private Label welcomeText13;

    @FXML
    protected void onHelloButtonClick() {

        spkParser.GetParametersFromSpkFile(getSPKfilename());
        welcomeText0.setText(spkParser.getKeyID());
        welcomeText1.setText(spkParser.getHDD());
        welcomeText2.setText(spkParser.getMotherBoard());
        welcomeText3.setText(spkParser.getCPU());
        welcomeText4.setText(spkParser.getNetwork());


        c2sParser.GetParametersFromc2sFile(getS2cfilename());
        welcomeText5.setText(c2sParser.getHDD());
        welcomeText6.setText(c2sParser.getMotherBoard());
        welcomeText7.setText(c2sParser.getCPU());
        welcomeText8.setText(c2sParser.getNetwork());


        if (spkParser.getHDD().equals(c2sParser.getHDD())){
            welcomeText9.setText("Valid key");
            welcomeText9.setTextFill(Color.GREEN);
        } else { welcomeText9.setText("Invalid key");
            welcomeText9.setTextFill(Color.RED);
            count++;
        }

        if (spkParser.getMotherBoard().equals(c2sParser.getMotherBoard())){
            welcomeText10.setText("Valid key");
            welcomeText10.setTextFill(Color.GREEN);
        } else { welcomeText10.setText("Invalid key");
            welcomeText10.setTextFill(Color.RED);
            count++;
        }

        if (spkParser.getCPU().equals(c2sParser.getCPU())){
            welcomeText11.setText("Valid key");
            welcomeText11.setTextFill(Color.GREEN);
        } else {
            welcomeText11.setText("Invalid key");
            welcomeText11.setTextFill(Color.RED);
            count++;
        }

        if (spkParser.getNetwork().equals(c2sParser.getNetwork())){
            welcomeText12.setText("Valid key");
            welcomeText12.setTextFill(Color.GREEN);
        } else {
            welcomeText12.setText("Invalid key");
            welcomeText12.setTextFill(Color.RED);
            count++;
        }


        if(count == 2){
            welcomeText13.setText("SPK Invalid");
            welcomeText13.setTextFill(Color.RED);
        } else if (count == 1) {
            welcomeText13.setText("SPK Valid");
            welcomeText13.setTextFill(Color.ORANGE);
        } else if (count == 0){
            welcomeText13.setText("SPK Valid");
            welcomeText13.setTextFill(Color.GREEN);
        }


    }


    @FXML    // Используем аннотацию FXML для привязки к элементам GUI
    private Label fileSPKPathLabel;

    @FXML
    private Button openSPKButton;

    @FXML   // Этот метод будет вызван при нажатии на кнопку
    public void handleOpenSPKFile() {
        FileChooser fileChooser = new FileChooser(); // Создаем новый FileChooser
        fileChooser.setTitle("SPK не выбран"); // Установка заголовка

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(".SPK only (*.SPK)", "*.SPK");
        fileChooser.getExtensionFilters().add(extFilter);

        // Открываем диалог выбора файла
        File file = fileChooser.showOpenDialog(getStage());

        // Проверяем, был ли выбран файл
        if (file != null) {
            fileSPKPathLabel.setText(file.getAbsolutePath()); // Обновляем метку
            this.SPKfilename = file.getAbsolutePath();
        } else {
            fileSPKPathLabel.setText("SPK не выбран"); // Если файл не выбран
        }
    }

    // Метод для получения текущего окна (Stage)
    private Stage getStage() {
        return (Stage) openSPKButton.getScene().getWindow();
    }


    @FXML    // Используем аннотацию FXML для привязки к элементам GUI
    private Label filec2sPathLabel;

    @FXML   // Этот метод будет вызван при нажатии на кнопку
    public void handleOpenc2sFile() {
        FileChooser fileChooser2 = new FileChooser(); // Создаем новый FileChooser
        fileChooser2.setTitle("c2s не выбран"); // Установка заголовка

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(".c2s only (*.c2s)", "*.c2s");
        fileChooser2.getExtensionFilters().add(extFilter);

        // Открываем диалог выбора файла
        File file2 = fileChooser2.showOpenDialog(getStage());

        // Проверяем, был ли выбран файл
        if (file2 != null) {
            filec2sPathLabel.setText(file2.getAbsolutePath()); // Обновляем метку
            this.s2cfilename = file2.getAbsolutePath();
        } else {
            filec2sPathLabel.setText("c2s не выбран"); // Если файл не выбран
        }
    }
}