package com.advent;

import com.advent.day1.Day1;

public class Main {

    public static void main(String[] args) {
        Day1 day1 = new Day1("src\\com\\advent\\day1\\input.txt");
        System.out.println("Result of first task for my input: " + day1.run(1));
        System.out.println("Result of second task for my input: " + day1.run(2));

    }
}
