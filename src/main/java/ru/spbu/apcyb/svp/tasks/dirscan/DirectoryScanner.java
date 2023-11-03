package ru.spbu.apcyb.svp.tasks.dirscan;

import java.nio.file.Path;

public class DirectoryScanner {

  private DirectoryScanner() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Сканирует директорию и записывает её содержимое в файл scan_results.txt.
   *
   * @param pathToScan директория для сканирования
   * @param pathToSave директория для сохранения результатов сканирования
   */
  public static void scanToFile(Path pathToScan, Path pathToSave) {
    // TODO: 03.11.2023 сделать!)
  }

  /**
   * Возвращает строку - содержимое директории. Сначала список файлов, затем поддиректории.
   * Содержимое поддиректорий записывается с отступом в виде знака ">".
   *
   * @return строка-содержимое директории
   */
  private static String getContent(Path pathToScan) {
    // TODO: 03.11.2023 вернуть строку - содержимое директории
    return null;
  }

}
