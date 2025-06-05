package com.javarush.cryptoanalyzer.poltavets.service;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.javarush.cryptoanalyzer.poltavets.constants.StaticAnaliticConstants.*;
import static com.javarush.cryptoanalyzer.poltavets.repository.ResultCode.OK;

public class StaticAnalitic implements Function {
    @Override
    public Result execute(String[] parameters) {
        HashMap<Character, Integer> encodeMap = frequencyAnalysis(parameters[2]);
        HashMap<Character, Integer> sampleMap = frequencyAnalysis(parameters[4]);
        HashMap<Character, Character> alphabetMap = pairChar(encodeMap, sampleMap);
        characterReplacement(alphabetMap, parameters);
        isFine(alphabetMap, parameters);



        return new Result(OK);

    }



    private HashMap<Character, Integer> frequencyAnalysis(String fileInPath) {
        //создаём HashMap и заносим туда новые символы. Если символ уже есть, то увеличиваем значение на 1.
        //На выходе получаем карту с частотой символов в файле
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        try (FileReader reader = new FileReader(fileInPath)) {
            char[] buffer = new char[2048];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    boolean search = frequencyMap.containsKey(buffer[i]);
                    if (search == true) {
                        int frequency = frequencyMap.get(buffer[i]);
                        frequency++;
                        frequencyMap.put(buffer[i], frequency);
                    } else {
                        frequencyMap.put(buffer[i], 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencyMap;
    }

    private char maxFrequency(HashMap<Character, Integer> frequencyMap) {
        int maxFrequency = 0;
        char maxChar = '@';
        for (Character key : frequencyMap.keySet()) {
            if (frequencyMap.get(key) > maxFrequency) {
                maxChar = key;
                maxFrequency = frequencyMap.get(key);
            }
        }
        return maxChar;
    }

    private void characterReplacement(HashMap<Character, Character> alphabetMap, String[] parameters) {
        try (FileReader reader = new FileReader(parameters[2]);
             FileWriter writer = new FileWriter(parameters[3])) {
            char[] buffer = new char[2048];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    if (alphabetMap.containsKey(buffer[i])) {
                        buffer[i] = alphabetMap.get(buffer[i]);
                    }
                }
                writer.write(buffer, 0, real);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<Character, Character> pairChar(HashMap<Character, Integer> encodeMap, HashMap<Character, Integer> sampleMap) {
        HashMap<Character, Character> alphabetMap = new HashMap<>();
        while (!encodeMap.isEmpty() || !sampleMap.isEmpty()) {
            char encodeChar = maxFrequency(encodeMap);
            char sampleChar = maxFrequency(sampleMap);
            alphabetMap.put(encodeChar, sampleChar);
            encodeMap.remove(encodeChar);
            sampleMap.remove(sampleChar);
        }
        return alphabetMap;
    }
    private void isFine(HashMap<Character, Character> alphabetMap, String[] parameters) {
        int isOk = 1;
        while(isOk != 2) {
            Scanner scanner = new Scanner(System.in);
            char firstChar = '\u2606';
            char secondChar = '\u2605';
            Character keyForFirst = null;
            Character keyForSecond = null;
            System.out.println(IS_FINE);
            isOk = scanner.nextInt();
            // TODO добавить проверну на int, иначе тут может вылетать ошибка
            scanner.nextLine();
            if(isOk == 2) {
                return;
            }
            System.out.println(CHAR_REPLACEMENT);
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (parts.length == 2 && parts[0].length() == 1 && parts[1].length() == 1) {
                firstChar = parts[0].charAt(0);
                secondChar = parts[1].charAt(0);
            } else {
                System.out.println(NOT_CHAR);
            }
            for (Map.Entry<Character, Character> entry : alphabetMap.entrySet()) {
                if (entry.getValue().equals(firstChar)) {
                    keyForFirst = entry.getKey();
                }
                if (entry.getValue().equals(secondChar)) {
                    keyForSecond = entry.getKey();
                }
            }
            if (keyForFirst != null && keyForSecond != null) {
                char firstValue = alphabetMap.get(keyForFirst);
                char secondValue = alphabetMap.get(keyForSecond);

                alphabetMap.put(keyForFirst, secondValue);
                alphabetMap.put(keyForSecond, firstValue);

            } else {
                System.out.println(NOT_CHAR_IN_MAP);
            }
            characterReplacement(alphabetMap,parameters);
        }

    }
}


//private void characterReplacement(HashMap<Character,Character> alphabetMap, String[] parameters) {
//
//    Path originalPath = Paths.get(parameters[3]);
//    Path tempPath = originalPath.resolveSibling("temporary.txt");
//
//    try {
//        if (!Files.exists(tempPath)) {
//            Files.createFile(tempPath);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//    try (FileReader reader = new FileReader(parameters[3]);
//         FileWriter writer = new FileWriter(tempPath.toFile())) {
//        char[] buffer = new char[2048];
//        while(reader.ready()){
//            int real = reader.read(buffer);
//            for (int i = 0; i < real; i++) {
//
//            }
//            writer.write(buffer, 0, real);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    try {
//        Files.delete(originalPath);
//        Files.move(tempPath, originalPath);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}

//private void copyContentFile(String[] parameters) {
//    try (FileReader reader = new FileReader(parameters[2]);
//         FileWriter writer = new FileWriter(parameters[3])) {
//        char[] buffer = new char[2048];
//        while (reader.ready()) {
//            int real = reader.read(buffer);
//            writer.write(buffer, 0, real);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}
