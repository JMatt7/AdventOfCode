package com.advent.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {
    private String inputFile;
    private List<String> lines = new ArrayList<>();
    private List<Integer> fishes = new ArrayList<>();

    public Day6(String inputFile) {
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
            int res = calculateTotalNumberOfFichesAfter80Days();
            System.out.printf("Task %d duration: %f ms%n", task, (double) (System.nanoTime() - startTime)/1000000);
            return res;
        }else if(task == 2){
            long startTime = System.nanoTime();
            long res = calculateTotalNumberOfFichesAfter256Days();
            System.out.printf("Task %d duration: %f ms%n", task, (double) (System.nanoTime() - startTime)/1000000);
            return res;
        }


        return 0;
    }

    private long calculateTotalNumberOfFichesAfter256Days() {

        Map<Integer, Long> fish = new HashMap<>();
        fishes = Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        for(Integer x : fishes){
            fish.put(x, fish.getOrDefault(x, 0L) + 1);
        }

        for(int day = 0; day < 256; day++){
            Map<Integer, Long> copyOfFish = new HashMap<>();

            for(int fishLife: fish.keySet()){
                if(fishLife > 0){
                    copyOfFish.put(fishLife - 1, copyOfFish.getOrDefault(fishLife - 1, 0L) + fish.get(fishLife));
                }else if(fishLife == 0){
                    copyOfFish.put(6, copyOfFish.getOrDefault(6, 0L) + fish.get(fishLife));
                    copyOfFish.put(8, fish.get(fishLife));
                }
            }

            fish = copyOfFish;
        }

        long sum = 0;

        for(long fishCount : fish.values())
            sum += fishCount;

        return sum;

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

    private int calculateTotalNumberOfFichesAfter80Days(){

        fishes = Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        for(int i = 0; i < 80; i++){
            int length = fishes.size();
            for(int j = 0; j < length; j++){
                fishes.set(j, fishes.get(j) - 1);
                if(fishes.get(j) < 0){
                    fishes.add(8);
                    fishes.set(j, 6);
                }
            }
        }

        return fishes.size();

    }
}
