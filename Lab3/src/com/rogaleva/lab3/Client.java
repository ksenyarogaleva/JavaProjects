package com.rogaleva.lab3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DecimalFormat;

public class Client extends Application {

    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;
    private static float[][] matrix;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Matrix");
        stage.setScene(new Scene(createMainScreen(),300,300));
        stage.show();
    }

    private GridPane createMainScreen(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        //grid.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Enter elements of 3x3 matrix!");
        sceneTitle.setTextAlignment(TextAlignment.CENTER);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        grid.add(sceneTitle, 0, 0, 2, 1);

        TextField element00=new TextField();
        TextField element01=new TextField();
        TextField element02=new TextField();
        TextField element10=new TextField();
        TextField element11=new TextField();
        TextField element12=new TextField();
        TextField element20=new TextField();
        TextField element21=new TextField();
        TextField element22=new TextField();

        grid.getColumnConstraints().add(new ColumnConstraints(50));
        grid.getColumnConstraints().add(new ColumnConstraints(50));
        grid.getColumnConstraints().add(new ColumnConstraints(50));

        grid.getRowConstraints().add(new RowConstraints(30));
        grid.getRowConstraints().add(new RowConstraints(10));
        grid.getRowConstraints().add(new RowConstraints(10));
        grid.getRowConstraints().add(new RowConstraints(10));
        grid.getRowConstraints().add(new RowConstraints(30));


        grid.add(element00,0,1);
        grid.add(element01,1,1);
        grid.add(element02,2,1);
        grid.add(element10,0,2);
        grid.add(element11,1,2);
        grid.add(element12,2,2);
        grid.add(element20,0,3);
        grid.add(element21,1,3);
        grid.add(element22,2,3);

        Button button=new Button("Get inverse matrix!");
        button.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
       HBox hbButton = new HBox(10);
        hbButton.setAlignment(Pos.BOTTOM_CENTER);
        hbButton.getChildren().add(button);
       grid.add(hbButton,0,4,3,2);


        button.setOnAction(actionEvent -> {
            matrix=new float[3][3];
            matrix[0][0]=Float.parseFloat(element00.getText());
            matrix[0][1]=Float.parseFloat(element01.getText());
            matrix[0][2]=Float.parseFloat(element02.getText());
            matrix[1][0]=Float.parseFloat(element10.getText());
            matrix[1][1]=Float.parseFloat(element11.getText());
            matrix[1][2]=Float.parseFloat(element12.getText());
            matrix[2][0]=Float.parseFloat(element20.getText());
            matrix[2][1]=Float.parseFloat(element21.getText());
            matrix[2][2]=Float.parseFloat(element22.getText());

            float[][] inverseMatrix=new float[3][3];
            inverseMatrix=parseServerResponse(matrix);
            button.getScene().setRoot(createSecondScreen(inverseMatrix));
        });

        return grid;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Server connecting...");
            Socket clientSocket = new Socket("127.0.0.1", 2525);
            System.out.println("Connection established...");
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());

            launch(args);

            inputStream.close();
            outputStream.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static float[][] parseServerResponse(float[][] matrix){
        float[][] inverseMatrix=new float[3][3];
        try{
            outputStream.writeObject(matrix);
            inverseMatrix=(float[][])inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return inverseMatrix;
    }

    private static Label createSecondScreen(float[][]matrix){
        Label label=new Label(printMatrix(matrix));
        label.setAlignment(Pos.CENTER);

        return label;
    }

    private static String printMatrix(float[][] matrix) {
        DecimalFormat format=new DecimalFormat("###.##");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                stringBuilder.append(format.format(matrix[i][j]));
                stringBuilder.append("   s");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}
