package com.javarush.cryptoanalyzer.poltavets.service;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;
import com.javarush.cryptoanalyzer.poltavets.exception.ApplicationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.javarush.cryptoanalyzer.poltavets.constants.Alphabet.RUSSIAN_SMALL_LETTERS;
import static com.javarush.cryptoanalyzer.poltavets.constants.BruteForceCoctants.FOUND_KEY;
import static com.javarush.cryptoanalyzer.poltavets.constants.BruteForceCoctants.KEY_WORDS;
import static com.javarush.cryptoanalyzer.poltavets.repository.ResultCode.ERROR;


public class BruteForce implements Function {

    @Override
    public Result execute(String[] parameters) {
        int key = 0;
        int counterKeywordsMax = 0;
        DecodeCaesar decodeCaesar = new DecodeCaesar();

        try {
            for (int i = 1; i < RUSSIAN_SMALL_LETTERS.length; i++) {
                int counterKeywords = 0;
                parameters[1] = Integer.toString(i);
                Result result = decodeCaesar.execute(parameters);
                try (BufferedReader reader = Files.newBufferedReader(Paths.get(parameters[3]))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] words = line.split(" +");
                        for (String w : words) {
                            for (String keyWords : KEY_WORDS) {
                                if (w.equalsIgnoreCase(keyWords)) {
                                    counterKeywords++;

                                }
                            }

                        }
                    }
                    System.out.println(i);
                    System.out.println(counterKeywords);
                    if (counterKeywords > counterKeywordsMax) {
                        key = i;
                        counterKeywordsMax = counterKeywords;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (RuntimeException e) {
            return new Result(ERROR, new ApplicationException("Расшифровка не удалась из-за ошибки", e));
        }
        parameters[1] = Integer.toString(key);
        System.out.println(FOUND_KEY + key);

        return decodeCaesar.execute(parameters);
    }
}

