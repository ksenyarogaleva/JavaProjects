package com.rogaleva.lab3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket clientAccepted = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            System.out.println("Server starting...");
            serverSocket = new ServerSocket(2525);
            clientAccepted = serverSocket.accept();
            System.out.println("Connection established...");
            outputStream = new ObjectOutputStream(clientAccepted.getOutputStream());
            inputStream = new ObjectInputStream(clientAccepted.getInputStream());

            float[][] matrix=(float[][])inputStream.readObject();
            System.out.println("Matrix received:");
            printMatrix(matrix);

            matrix=inverseMatrix(matrix);
            outputStream.writeObject(matrix);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try {
                inputStream.close();
                outputStream.close();
                clientAccepted.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static float[][] inverseMatrix(float[][] sourceMatrix){
        int size = sourceMatrix.length;
        float[][] tempMatrix = new float[size][size];
        float temp;
        for (int i = 0; i <size; i++)
            for(int j = 0;j < size;j++){
                tempMatrix[i][j] = 0f;
                if(i == j)
                    tempMatrix[i][j] = 1f;
            }

        for(int i = 0;i < size;i++){
            temp = sourceMatrix[i][i];      //[0][0] элемент матрицы
            for(int j = 0; j < size; j++){
                sourceMatrix[i][j]/=temp;   //получаем [0][0] элементом 1
                tempMatrix[i][j]/=temp;
            }

            for(int j = i+1;j < size;j++){
                temp=sourceMatrix[j][i];        //[1][0] элемент матрицы
                for(int k=0;k<size;k++){
                    sourceMatrix[j][k] -= sourceMatrix[i][k] * temp; //от 1-ой строки отнимаем 0ую умнож. на arr[1][0]
                    tempMatrix[j][k] -= tempMatrix[i][k] * temp;
                }
            }
        }

        temp=sourceMatrix[0][1];
        for(int j=0;j<size;j++){
            sourceMatrix[0][j]-=sourceMatrix[1][j]*temp;
            tempMatrix[0][j]-=tempMatrix[1][j]*temp;
        }

        for(int j=0; j<size;j++){
            temp=sourceMatrix[0][2];
            float temp1=sourceMatrix[1][2];
            sourceMatrix[0][j]-=sourceMatrix[2][j] * temp;
            tempMatrix[0][j]-=tempMatrix[2][j] * temp;
            sourceMatrix[1][j]-=sourceMatrix[2][j]*temp1;
            tempMatrix[1][j]-=tempMatrix[2][j]*temp1;
        }

        for(int i = 0; i < size; i++){
            for(int j = 0; j< size; j++){
                sourceMatrix[i][j]=tempMatrix[i][j];
            }
        }

        return sourceMatrix;

    }

    private static void printMatrix(float[][]matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
