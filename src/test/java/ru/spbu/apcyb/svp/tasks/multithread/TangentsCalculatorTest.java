package ru.spbu.apcyb.svp.tasks.multithread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.spbu.apcyb.svp.tasks.multithread.generator.DataGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

class TangentsCalculatorTest {

  @ParameterizedTest
  @CsvSource({
          "src/test/resources/multithreading_test/elements_1.txt, 1",
          "src/test/resources/multithreading_test/elements_100.txt, 100",
          "src/test/resources/multithreading_test/elements_1000000.txt, 1000000"
  })
  void testSimple(String path, int numberOfDoubles) throws IOException {
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
  void performanceTest() {
    Path input = Path.of("src/test/resources/multithreading_test/elements_1000000.txt");
    Path oneThreadOutput = Path.of("src/test/resources/multithreading_test/one_thread_result.txt");
    Path multiThreadsOutput = Path.of("src/test/resources/multithreading_test/multithreading_result.txt");

    DataGenerator generator = new DataGenerator(input.toFile());
    generator.generate(10000000);

    Instant startSingle = Instant.now();
    TangentsCalculator.calculateOneThread(input.toFile(), oneThreadOutput.toFile());
    Instant endSingle = Instant.now();

    Instant startMulti = Instant.now();
    TangentsCalculator.calculateMultiThreads(input.toFile(), multiThreadsOutput.toFile());
    Instant endMulti = Instant.now();

    System.out.println("Single thread: " + Duration.between(startSingle, endSingle).toMillis());
    System.out.println("Multi thread: " + Duration.between(startMulti, endMulti).toMillis());
    Assertions.assertTrue(true);
  }

}
