package ru.spbu.apcyb.svp.tasks.multithread;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.spbu.apcyb.svp.tasks.multithread.generator.DataGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class TangentsCalculatorTest {

  @ParameterizedTest
  @CsvSource({
          "src/test/resources/multithreading_test/elements_1.txt, 1",
//          "src/test/resources/multithreading_test/elements_100.txt, 100",
//          "src/test/resources/multithreading_test/elements_1000000.txt, 1000000"
  })
  public void testSimple(String path, int numberOfDoubles) throws IOException {
    Path input = Path.of(path);
    Path oneThreadOutput = Path.of("src/test/resources/multithreading_test/one_thread_result.txt");
    Path multiThreadsOutput = Path.of("src/test/resources/multithreading_test/multithreading_result.txt");
    DataGenerator generator = new DataGenerator(input.toFile());
    generator.generate(numberOfDoubles);

    TangentsCalculator.calculateOneThread(input.toFile(), oneThreadOutput.toFile());
    TangentsCalculator.calculateMultiThreads(input.toFile(), multiThreadsOutput.toFile());
    Assertions.assertEquals(-1, Files.mismatch(oneThreadOutput, multiThreadsOutput));
  }

  @Test
  public void performanceTest() {
    // TODO: 21.11.2023
    Assertions.assertTrue(true);
  }

}
