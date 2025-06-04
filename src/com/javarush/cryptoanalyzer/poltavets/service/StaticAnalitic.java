package com.javarush.cryptoanalyzer.poltavets.service;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.javarush.cryptoanalyzer.poltavets.repository.ResultCode.OK;

public class StaticAnalitic implements Function {
    @Override
    public Result execute(String[] parameters) {
        HashMap<Character, Integer> encodeMap = frequencyAnalysis(parameters[2]);
        HashMap<Character, Integer> sampleMap = frequencyAnalysis(parameters[4]);
        copyContentFile(parameters);

        while (!encodeMap.isEmpty() || !sampleMap.isEmpty()) {
            char encodeChar = maxFrequency(encodeMap);
            char sampleChar = maxFrequency(sampleMap);
            encodeMap.remove(encodeChar);
            sampleMap.remove(sampleChar);
            characterReplacement(encodeChar, sampleChar, parameters);

        }
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

    private void copyContentFile(String[] parameters) {
        try (FileReader reader = new FileReader(parameters[2]);
             FileWriter writer = new FileWriter(parameters[3])) {
            char[] buffer = new char[2048];
            while (reader.ready()) {
                int real = reader.read(buffer);
                writer.write(buffer, 0, real);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void characterReplacement(char encodeChar, char sampleChar, String[] parameters) {

        Path originalPath = Paths.get(parameters[3]);
        Path tempPath = originalPath.resolveSibling("temporary.txt");

        try {
            if (!Files.exists(tempPath)) {
                Files.createFile(tempPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader(parameters[3]);
             FileWriter writer = new FileWriter(tempPath.toFile())) {
            char[] buffer = new char[2048];
            while(reader.ready()){
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {

                }
                writer.write(buffer, 0, real);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.delete(originalPath);
            Files.move(tempPath, originalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


