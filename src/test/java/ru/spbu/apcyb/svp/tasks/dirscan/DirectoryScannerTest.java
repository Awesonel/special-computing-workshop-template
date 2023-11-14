package ru.spbu.apcyb.svp.tasks.dirscan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectoryScannerTest {

  @Test
  void simpleTest() throws IOException {
    String pathToScan = "src/test/resources/directory_scan_test/test_directory";
    String pathToSave = "src/test/resources/directory_scan_test/result_dir";

    String expected = """
        2.txt
        sub_dir_1
        \t3.txt
        sub_dir_2
        \t1.txt
        \tsub_sub_dir
        \t\t3.gif
        """;

    DirectoryScanner.scanToFile(pathToScan, pathToSave);
    String actual = Files.readString(Path.of(pathToSave + "/scan_results.txt"));

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void emptyDirectoryTest() throws IOException {
    String pathToScan = "src/test/resources/directory_scan_test/empty_dir";
    String pathToSave = "src/test/resources/directory_scan_test/result_dir";

    DirectoryScanner.scanToFile(pathToScan, pathToSave);
    String actual = Files.readString(Path.of(pathToSave + "/scan_results.txt"));

    Assertions.assertEquals("", actual);
  }

  @Test
  void emptySubDirectoryTest() throws IOException {
    String pathToScan = "src/test/resources/directory_scan_test/empty_sub_dir";
    String pathToSave = "src/test/resources/directory_scan_test/result_dir";

    String expected = """
        any_file.png
        empty_dir
        """;

    DirectoryScanner.scanToFile(pathToScan, pathToSave);
    String actual = Files.readString(Path.of(pathToSave + "/scan_results.txt"));

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void scanStringIsNotDirectoryTest() {
    String pathToScan = "src/test/resources/directory_scan_test/just_a_file.txt";
    String pathToSave = "src/test/resources/directory_scan_test/result_dir";

    Exception e = Assertions.assertThrows(RuntimeException.class,
        () -> DirectoryScanner.scanToFile(pathToScan, pathToSave));

    Assertions.assertEquals("Provided string is not a directory.",
        e.getMessage());
  }

  @Test
  void saveStringIsNotDirectoryTest() {
    String pathToScan = "src/test/resources/directory_scan_test/test_directory";
    String pathToSave = ";w4;5buy";

    Exception e = Assertions.assertThrows(RuntimeException.class,
        () -> DirectoryScanner.scanToFile(pathToScan, pathToSave));

    Assertions.assertEquals("Provided string is not a directory.",
        e.getMessage());
  }
}
