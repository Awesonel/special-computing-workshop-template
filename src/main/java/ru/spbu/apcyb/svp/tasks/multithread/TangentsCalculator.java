package ru.spbu.apcyb.svp.tasks.multithread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Multithreaded tangent calculator.
 */
public class TangentsCalculator {

  public static final int NUMBER_OF_THREADS = 8;

  private TangentsCalculator() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Multithreads tangents from all numbers stored in a file. Outputs to another file.
   *
   * @param input  input file
   * @param output output file
   */
  public static void calculateMultiThreads(File input, File output) {
    List<Double> numbers = readFileData(input);
    writeListToFile(calculateTangents(numbers), output);
  }

  /**
   * Counts tangents from numbers stored in a file in a single thread. Outputs to another file.
   *
   * @param input  input file
   * @param output output file
   */
  public static void calculateOneThread(File input, File output) {
    List<Double> numbers = readFileData(input);
    writeListToFile(calculateTangentsOneThread(numbers), output);
  }

  /**
   * Reads all numbers from a file, writes them to List.
   *
   * @param inputFile input file
   * @return list of numbers
   */
  private static List<Double> readFileData(File inputFile) {

    List<Double> resultList = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
      String line = reader.readLine();
      while (line != null) {
        resultList.add(Double.parseDouble(line));
        line = reader.readLine();
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
    return resultList;
  }

  /**
   * Writes all numbers in the list to a file, separating them with "\n".
   *
   * @param data       list for output to file
   * @param outputFile output file
   */
  private static void writeListToFile(List<Double> data, File outputFile) {
    try (FileWriter writer = new FileWriter(outputFile)) {
      for (Double listElem : data) {
        writer.write(listElem + "\n");
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  /**
   * Counts the tangent from all numbers lying in data with multithreading.
   *
   * @param data initial data
   * @return calculation result - list of tangents from the given numbers
   */
  private static List<Double> calculateTangents(List<Double> data) {

    ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    try {

      Future<List<Double>> futuresList = executorService.submit(
              () -> data.parallelStream().map(Math::tan).toList());
      return futuresList.get();

    } catch (ExecutionException | InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(ex.getMessage());
    } finally {
      executorService.shutdown();
    }
  }

  /**
   * Counts the tangent from all numbers lying in data with one thread.
   *
   * @param data initial data
   * @return calculation result - list of tangents from the given numbers
   */
  private static List<Double> calculateTangentsOneThread(List<Double> data) {
    return data.stream().map(Math::tan).toList();
  }

}
