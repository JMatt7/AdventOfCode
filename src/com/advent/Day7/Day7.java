package com.advent.Day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {
    private String inputFile;
    private List<String> lines = new ArrayList<>();

    public Day7(String inputFile) {
        this.inputFile = inputFile;
    }

    public long run(int task){
        try{
            if(lines.size() == 0)
                loadFileToList();
        }catch (Exception e){
            System.out.println(e);
        }

        if(task == 1){
            long startTime = System.nanoTime();
            int res = calculateHorizontalPosition1();
            System.out.printf("Task %d duration: %f ms%n", task, (double) (System.nanoTime() - startTime)/1000000);
            return res;
        }else if(task == 2){
            long startTime = System.nanoTime();
            long res = calculateHorizontalPosition2();
            System.out.printf("Task %d duration: %f ms%n", task, (double) (System.nanoTime() - startTime)/1000000);
            return res;
        }


        return 0;
    }

    private void loadFileToList() throws IOException, NumberFormatException {
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String currentLine = null;

        while((currentLine = bufferedReader.readLine()) != null){
            lines.add(currentLine);
        }

        bufferedReader.close();
    }

    private int calculateHorizontalPosition1(){
        int[] crabs = Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

        int bestRes = Integer.MAX_VALUE;
        for(int i = 1; i < crabs.length + 1; i++){
            int tempSum = 0;
            for(int x : crabs){
                tempSum += Math.abs(i - x);
            }

            if(tempSum <= bestRes)
                bestRes = tempSum;
        }

        return bestRes;
    }

    private int calculateHorizontalPosition2(){
        int[] crabs = Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

        int bestRes = Integer.MAX_VALUE;
        for(int i = 1; i < crabs.length + 1; i++){
            int tempSum = 0;
            for(int x : crabs){
                int bound = Math.abs(i - x);

                for(int j = 1; j <= bound; j++){
                    tempSum += j;
                }
            }

            if(tempSum <= bestRes)
                bestRes = tempSum;
        }

        return bestRes;
    }
}
