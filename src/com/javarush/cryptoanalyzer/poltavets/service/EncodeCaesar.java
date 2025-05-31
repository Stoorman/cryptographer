package com.javarush.cryptoanalyzer.poltavets.service;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

import javax.imageio.IIOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.javarush.cryptoanalyzer.poltavets.constants.Alphabet.RUSSIAN_SMALL_LETTERS;
import static com.javarush.cryptoanalyzer.poltavets.repository.ResultCode.OK;


public class EncodeCaesar implements Function {
    @Override
    public Result execute(String[] parameters) {

        try (FileReader reader = new FileReader(parameters[2]);
             FileWriter writer = new FileWriter(parameters[3])) {
            char[] buffer = new char[2048];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    buffer[i] = encryption(buffer[i], Integer.parseInt(parameters[1]));
                }
                writer.write(buffer, 0, real);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(OK);
    }


    private char encryption(char inChar, int key) {
        char outChar = inChar;
        int lengthArray = RUSSIAN_SMALL_LETTERS.length;
        for (int i = 0; i < lengthArray; i++) {
            if (inChar == RUSSIAN_SMALL_LETTERS[i]) {
                int newIndex = (i + key) % lengthArray;
                if (newIndex < 0) {
                    newIndex += lengthArray;
                }
                outChar = RUSSIAN_SMALL_LETTERS[newIndex];
                break;
            }
        }
        return outChar;
    }
}
