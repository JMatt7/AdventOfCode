package com.advent.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Day3 {

    private String inputFile;
    private List<String> lines = new ArrayList<>();

    public Day3(String inputFile) {
        this.inputFile = inputFile;
    }

    public int run(int task){
        try{
            if(lines.size() == 0)
                loadFileToList();
        }catch (Exception e){
            System.out.println(e);
        }

        if(task == 1){
            long startTime = System.nanoTime();
            String number = calculateFinalStringNumber();
            int res = calculateMultiOfGammaAndEpsilon(number);
            System.out.printf("Task %d duration: %f ms%n", task, (double) (System.nanoTime() - startTime)/1000000);
            return res;
        }else if(task == 2){
            long startTime = System.nanoTime();
            int res = calculateO2() * calculateCO2();
            System.out.printf("Task %d duration: %f ms%n", task, (double) (System.nanoTime() - startTime)/1000000);
            return res;
        }

        return 0;
    }

    private int calculateMultiOfGammaAndEpsilon(String number) {
        int maxVal = (int) Math.pow(2, number.length()) - 1;
        int gamma = Integer.parseInt(number, 2);
        int epsilon = maxVal - gamma;

        return gamma * epsilon;
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

    private String calculateFinalStringNumber(){
        StringBuilder res = new StringBuilder("");


        int[] nums = new int[lines.get(0).length()];
        for(String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '0') {
                    nums[i]--;
                } else {
                    nums[i]++;
                }
            }
        }

        for(int x : nums){
            if(x < 0){
                res.append('0');
            }else if(x > 0){
                res.append('1');
            }
        }
        return res.toString();
    }

    private int calculateO2(){
        List<String> o2Lines = new ArrayList<>(lines);

        int numIndicator = 0;
        int indexIndicator = 0;

        while(o2Lines.size() > 1){

            ListIterator<String> iter = o2Lines.listIterator();

            for(String line : o2Lines){

                if(line.charAt(indexIndicator) == '1'){
                    numIndicator++;
                }else {
                    numIndicator--;
                }
            }

            while(iter.hasNext()){
                if(numIndicator >= 0 && iter.next().charAt(indexIndicator) == '0'){
                    iter.remove();
                }else if(numIndicator < 0 && iter.next().charAt(indexIndicator) == '1'){
                    iter.remove();
                }
            }

            indexIndicator++;
            numIndicator = 0;
        }

        return Integer.parseInt(o2Lines.get(0), 2);
    }

    private int calculateCO2(){
        List<String> co2Lines = new ArrayList<>(lines);

        int numIndicator = 0;
        int indexIndicator = 0;

        while(co2Lines.size() > 1){

            ListIterator<String> iter = co2Lines.listIterator();

            for(String line : co2Lines){
                if(line.charAt(indexIndicator) == '1'){
                    numIndicator++;
                }else {
                    numIndicator--;
                }
            }

            while(iter.hasNext()){
                if(numIndicator >= 0 && iter.next().charAt(indexIndicator) == '1'){
                    iter.remove();
                }else if(numIndicator < 0 && iter.next().charAt(indexIndicator) == '0'){
                    iter.remove();
                }
            }

            indexIndicator++;
            numIndicator = 0;
        }

        return Integer.parseInt(co2Lines.get(0), 2);
    }

}
