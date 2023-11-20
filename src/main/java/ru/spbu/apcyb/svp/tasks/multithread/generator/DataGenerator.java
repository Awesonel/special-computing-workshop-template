package ru.spbu.apcyb.svp.tasks.multithread.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for generating data files.
 */
public class DataGenerator {

  private final File output;

  /**
   * Generator creation.
   *
   * @param file file to save the generation result
   */
  public DataGenerator(File file) {
    output = file;
  }

  /**
   * Fills the file with random numbers from -50 to 50.
   *
   * @param numberOfNumbers number of numbers to generate
   */
  public void generate(int numberOfNumbers) {
    try (FileWriter writer = new FileWriter(output)) {
      for (int i = 0; i < numberOfNumbers; i++) {
        double randomDouble = (Math.random() * 100) - 50;
        writer.write(randomDouble + "\n");
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
}
