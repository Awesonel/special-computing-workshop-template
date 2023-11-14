package ru.spbu.apcyb.svp.tasks.dirscan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility class for scanning directories.
 */
public class DirectoryScanner {

  private DirectoryScanner() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Scans a directory and writes its contents to a file scan_results.txt.
   *
   * @param pathToScan scan directory as a string
   * @param pathToSave directory for saving scan results as a string
   */
  public static void scanToFile(String pathToScan, String pathToSave) {

    Path scanPath = Path.of(pathToScan).toAbsolutePath();
    Path savePath = Path.of(pathToSave).toAbsolutePath();
    if (!(Files.isDirectory(scanPath) && Files.isDirectory(savePath))) {
      throw new IllegalArgumentException("Provided string is not a directory.");
    }
    scanToFile(scanPath, savePath);

  }

  /**
   * Scans a directory and writes its contents to a file scan_results.txt.
   *
   * @param pathToScan scan directory
   * @param pathToSave directory for saving scan results
   */
  public static void scanToFile(Path pathToScan, Path pathToSave) {
    String savePath = pathToSave + "/scan_results.txt";
    try (FileWriter output = new FileWriter(savePath)) {
      String result = getContent(pathToScan, new StringBuilder(), 0);
      output.write(result);
    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  /**
   * Returns a string - the contents of the directory. First the list of files, then subdirectories.
   * The contents of subdirectories are indented as "\t".
   *
   * @param pathToScan scan directory
   * @param result     stringBuilder for result string (new StringBuilder if root directory)
   * @param depth      depth at which the subdirectory is located (0 if root directory)
   * @return string - the content of the directory
   */
  private static String getContent(Path pathToScan, StringBuilder result, int depth) {
    Directory directory = new Directory(pathToScan);

    for (File file : directory.getFiles()) {
      result.append("\t".repeat(depth)).append(file.getName()).append("\n");
    }

    for (Directory subDirectory : directory.getSubDirectories()) {
      result.append("\t".repeat(depth)).append(subDirectory.getDirPath().getFileName())
          .append("\n");
      getContent(subDirectory.getDirPath(), result, depth + 1);
    }

    return result.toString();
  }

}
