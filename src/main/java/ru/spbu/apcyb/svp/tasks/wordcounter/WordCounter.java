package ru.spbu.apcyb.svp.tasks.wordcounter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для подсчёта количества слов в файле.
 */
public class WordCounter {
  private final Path outputDirectory;

  /**
   * Создание объекта.
   *
   * @param outputDirectory директория для сохранения файлов с результатами
   */
  public WordCounter(Path outputDirectory) {
    if (!Files.isDirectory(outputDirectory)) {
      throw new IllegalArgumentException("Provided string isn't a directory!");
    }
    this.outputDirectory = outputDirectory;
  }

  /**
   * Подсчитывает слова в файле и выводит результат в файл counts.txt.
   *
   * @param inputFile       входной файл
   * @param createDirectory true, если нужно создать директорию
   *                       с файлами для каждого отедльного слова
   */
  public void countWords(Path inputFile, boolean createDirectory) {
    try (Stream<String> lines = Files.lines(inputFile);
         FileWriter writer = new FileWriter(outputDirectory.toString() + "/counts.txt")) {

      Map<String, Long> wordCounts = lines
              .flatMap(line -> Arrays.stream(line.split("[^a-zA-ZЁёА-я0-9]")))
              .filter(line -> !line.isEmpty())
              .map(String::toLowerCase)
              .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      for (Map.Entry<String, Long> wordCount : wordCounts.entrySet()) {
        String line = String.format("%s : %d%n", wordCount.getKey(), wordCount.getValue());
        writer.write(line);
      }

      if (createDirectory) {
        createDirectoryWithResultFiles(wordCounts);
      }

    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  /**
   * Создаёт директорию и заполняет её файлами для каждого отдельного слова.
   *
   * @param wordCounts хеш-мапа с подсчитанными словами
   */
  private void createDirectoryWithResultFiles(Map<String, Long> wordCounts) {
    Path newDirectory = Path.of(outputDirectory.toString() + "/results");
    try {
      Path directoryToSave = Files.createDirectories(newDirectory);
      wordCounts.forEach((word, number)
              -> CompletableFuture.runAsync(
                      () -> writeOneWordToFile(word, number, directoryToSave)));
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }

  }

  /**
   * Создаёт одноимённый файл для слова.
   *
   * @param word слово для создания файла
   * @param number количество вхождений этого слова в файл
   */
  private void writeOneWordToFile(String word, Long number, Path directoryToSave) {
    String pathToSave = directoryToSave.toString() + String.format("/%s.txt", word);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToSave))) {

      for (long i = 0; i < number; i++) {
        writer.write(word + " ");
      }

    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
}
