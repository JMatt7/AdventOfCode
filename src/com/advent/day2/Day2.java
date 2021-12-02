package com.advent.day2;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Day2 {

    private String inputFile;
    private List<String> lines = new ArrayList<>();
    private int horizontalPosition = 0;
    private int depthPosition = 0;
    private int aim = 0;

    public Day2(String inputFile) {
        this.inputFile = inputFile;
    }

    public int run(int task){
        int res = 0;
        try{
            if(lines.size() == 0)
                loadFileToIntArray();

            if(task == 1)
                res = calculateFinalPosition();
            else if(task == 2){
                res = calculateFinalPositionWithAim();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        eraseValues();
        return res;
    }

    private void eraseValues(){
        horizontalPosition = 0;
        depthPosition = 0;
    }

    private void loadFileToIntArray() throws IOException, NumberFormatException {
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String currentLine = null;

        while((currentLine = bufferedReader.readLine()) != null){
            lines.add(currentLine);
        }

        bufferedReader.close();
    }

    private int calculateFinalPosition(){

        lines.stream().forEach(line -> {
            String[] arr = line.split(" ", -1);

            String direction = arr[0];
            int value = Integer.parseInt(arr[1]);

            switch (direction) {
                case "forward":
                    horizontalPosition += value;
                    break;
                case "down":
                    depthPosition += value;
                    break;
                case "up":
                    depthPosition -= value;
                    break;
            }
        });

        return depthPosition * horizontalPosition;
    };

    private int calculateFinalPositionWithAim(){

        lines.stream().forEach(line -> {
            String[] arr = line.split(" ", -1);

            String direction = arr[0];
            int value = Integer.parseInt(arr[1]);

            switch (direction) {
                case "forward":
                    horizontalPosition += value;
                    depthPosition += aim*value;
                    break;
                case "down":
                    aim += value;
                    break;
                case "up":
                    aim -= value;
                    break;
            }
        });

        return depthPosition * horizontalPosition;
    };
}
