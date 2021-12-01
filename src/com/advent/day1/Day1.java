package com.advent.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    private String inputFileName;
    private int[] inputArr;

    public Day1(String fileName){
        this.inputFileName = fileName;
    }

    public int[] getInputArr() {
        return inputArr;
    }

    public int run(int task){
        try {
            loadFileToIntArray();
        }catch (Exception e){
            System.out.println(e);
        }
        if(task == 1) {
            return numberOfDepthIncreased();
        }else if(task == 2){
            return numberOf3DepthIncreased();
        }

        return 0;
    }

    private void loadFileToIntArray() throws IOException, NumberFormatException {
        FileReader fileReader = new FileReader(inputFileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Integer> lines = new ArrayList<>();
        String currentLine = null;

        while((currentLine = bufferedReader.readLine()) != null){
            lines.add(Integer.parseInt(currentLine));
        }

        bufferedReader.close();
        inputArr = lines.stream().mapToInt(i -> i).toArray();
    }

    private int numberOfDepthIncreased(){
        int result = 0;
        for(int i = 1; i < inputArr.length; i++){
            if(inputArr[i] > inputArr[i-1])
                result++;
        }

        return result;
    }

    private int numberOf3DepthIncreased(){
        int result = 0;
        for(int i = 0; i < inputArr.length - 3; i++){
            if(inputArr[i] + inputArr[i+1] + inputArr[i+2] < inputArr[i+1] + inputArr[i+2] + inputArr[i+3])
                result++;
        }

        return result;
    }

}
