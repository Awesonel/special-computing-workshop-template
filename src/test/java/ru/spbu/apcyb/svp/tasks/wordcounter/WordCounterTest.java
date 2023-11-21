package ru.spbu.apcyb.svp.tasks.wordcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class WordCounterTest {

  @Test
  void shortFileTest() throws IOException {
    Path pathToSave = Path.of("src/test/resources/word_counter_test/short_file_results");
    Path inputFile = Path.of("src/test/resources/word_counter_test/short_file.txt");
    Path expected = Path.of("src/test/resources/word_counter_test/short_file_expected.txt");

    WordCounter counter = new WordCounter(pathToSave);
    counter.countWords(inputFile, true);
    Path actual = Path.of("src/test/resources/word_counter_test/short_file_results/counts.txt");

    File[] resultFiles =
            (new File("src/test/resources/word_counter_test/short_file_results/results"))
                    .listFiles();

    Assertions.assertEquals(-1, Files.mismatch(expected, actual));
    Assertions.assertNotNull(resultFiles);
    Assertions.assertEquals(3, resultFiles.length);
  }

  @Test
  void bigFileTest() {
    Path pathToSave = Path.of("src/test/resources/word_counter_test/big_file_results");
    Path inputFile = Path.of("src/test/resources/word_counter_test/big_file.txt");

    WordCounter counter = new WordCounter(pathToSave);
    counter.countWords(inputFile, true);

    File[] resultFiles =
            (new File("src/test/resources/word_counter_test/big_file_results/results"))
                    .listFiles();

    Assertions.assertNotNull(resultFiles);
    Assertions.assertEquals(94, resultFiles.length);
  }

  @Test
  void wrongInputTest() {
    Path path = Path.of("ey54eah5 54e b");
    Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> new WordCounter(path));
    Assertions.assertEquals("Provided string isn't a directory!", ex.getMessage());
  }
}
