package com.advent.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {
    private String inputFile;
    private List<String> lines = new ArrayList<>();
    private int flashes = 0;
    private int[][] octopuses;

    public Day11(String inputFile) {
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
            calculateAllFlashes();
            System.out.printf("Task %d duration: %f ms%n", task, (double) (System.nanoTime() - startTime)/1000000);
            return flashes;
        }else if(task == 2){
            long startTime = System.nanoTime();
            int res = synchronizedFlash();
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

    private void calculateAllFlashes(){
        int i = 0, j = 0;
        octopuses = new int[lines.size()][lines.get(0).length()];

        for(String row : lines){
            for(char c : row.toCharArray()){
                octopuses[i][j] = Character.getNumericValue(c);
                j++;
            }
            i++;
            j = 0;
        }

        for(int t = 0; t < 100; t++) {
            for (int x = 0; x < octopuses.length; x++) {
                for (int y = 0; y < octopuses[0].length; y++) {
                    octopuses[x][y] += 1;
                }
            }

            for (int x = 0; x < octopuses.length; x++) {
                for (int y = 0; y < octopuses[0].length; y++) {
                    if(octopuses[x][y] == 10){
                        flash(x, y);
                    }
                }
            }

            for (int x = 0; x < octopuses.length; x++) {
                for (int y = 0; y < octopuses[0].length; y++) {
                    if(octopuses[x][y] == -1){
                        octopuses[x][y] = 0;
                    }
                }
            }
        }
    }

    private int synchronizedFlash(){
        int i = 0, j = 0;
        int loop = 0;
        int allFlashed = 0;
        octopuses = new int[lines.size()][lines.get(0).length()];

        for(String row : lines){
            for(char c : row.toCharArray()){
                octopuses[i][j] = Character.getNumericValue(c);
                j++;
            }
            i++;
            j = 0;
        }
        boolean sync = false;
        while (!sync) {
            for (int x = 0; x < octopuses.length; x++) {
                for (int y = 0; y < octopuses[0].length; y++) {
                    octopuses[x][y] += 1;
                }
            }

            for (int x = 0; x < octopuses.length; x++) {
                for (int y = 0; y < octopuses[0].length; y++) {
                    if (octopuses[x][y] == 10) {
                        flash(x, y);
                    }
                }
            }

            for (int x = 0; x < octopuses.length; x++) {
                for (int y = 0; y < octopuses[0].length; y++) {
                    if (octopuses[x][y] == -1) {
                        octopuses[x][y] = 0;
                        allFlashed++;
                    }
                }
            }

            loop++;
            if(allFlashed == octopuses.length * octopuses[0].length){
                sync = true;
            }
            allFlashed = 0;
        }

        return loop;
    }

    private void flash(int x, int y){
        flashes += 1;
        octopuses[x][y] = -1;

        for(int dx : new int[]{-1, 0, 1}){
            for(int dy : new int[]{-1, 0, 1}){
                int xx = x + dx;
                int yy = y + dy;

                if(0 <= xx && xx < octopuses.length && 0 <= yy && yy < octopuses[0].length && octopuses[xx][yy] != -1){
                    octopuses[xx][yy] += 1;
                    if(octopuses[xx][yy] >= 10){
                        flash(xx, yy);
                    }
                }

            }
        }
    }
}
