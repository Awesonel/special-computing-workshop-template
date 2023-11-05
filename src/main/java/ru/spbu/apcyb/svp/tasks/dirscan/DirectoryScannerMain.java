package ru.spbu.apcyb.svp.tasks.dirscan;

/**
 * Main class for DirectoryScanner.
 */
public class DirectoryScannerMain {

  public static void main(String[] args) {
    DirectoryScanner.scanToFile(
        "src/test/resources/directory_scan_test/test_directory",
        "src/test/resources/directory_scan_test/result_dir");
  }

}
