package com.advent.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 {

    private String inputFile;
    private List<String> lines = new ArrayList<>();

    public Day10(String inputFile) {
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
            int res = calculateIncorrectChars();
            System.out.printf("Task %d duration: %f ms%n", task, (double) (System.nanoTime() - startTime)/1000000);
            return res;
        }else if(task == 2){
            long startTime = System.nanoTime();
            long res = autoCompleteValue();
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

    private int calculateIncorrectChars(){
        Map<Character, Integer> charValues = new HashMap<>();
        Map<Character, Character> openCloseChars = new HashMap<>();
        Stack<Character> characterStack = new Stack<>();
        int res = 0;

        charValues.put(')', 3);
        charValues.put(']', 57);
        charValues.put('}', 1197);
        charValues.put('>', 25137);
        openCloseChars.put(')', '(');
        openCloseChars.put('}', '{');
        openCloseChars.put('>', '<');
        openCloseChars.put(']', '[');

        for(String str : lines){
            characterStack.clear();
            for(char c : str.toCharArray()){
                if(c == '(' || c == '[' || c == '{' || c == '<'){
                    characterStack.add(c);
                    continue;
                }

                char openChar = characterStack.pop();
                if(!openCloseChars.isEmpty() && openCloseChars.get(c) != openChar) {
                    res += charValues.get(c);
                    break;
                }
            }
        }

        return  res;
    }

    private long autoCompleteValue(){
        Map<Character, Integer> charValues = new HashMap<>();
        Stack<Character> characterStack = new Stack<>();
        List<Long> values = new ArrayList<>();
        long res = 0;

        charValues.put('(', 1);
        charValues.put('[', 2);
        charValues.put('{', 3);
        charValues.put('<', 4);

        for(String str : lines){
            for(char c : str.toCharArray()){
                if(c == '(' || c == '[' || c == '{' || c == '<'){
                    characterStack.add(c);
                }else {
                    characterStack.pop();
                }
            }

            while (!characterStack.isEmpty()){
                res *= 5;
                res += charValues.get(characterStack.pop());
            }
            values.add(res);
            res = 0;
        }

        values.sort(Long::compareTo);
        return values.get(values.size() / 2);

    }


}
/*
[({(<(())[]>[[{[]{<()<>>
123456776885
 */