package ru.spbu.apcyb.svp.tasks.dirscan;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class Directory {

  private final Path dirPath;
  private List<Directory> subDirectories;
  private List<File> files;

  public Directory(String path) {

    try {
      Path providedPath = Path.of(path).toAbsolutePath();
      if (!Files.isDirectory(providedPath)) {
        throw new IllegalArgumentException("Provided path is not a directory.");
      }
      dirPath = providedPath;
      readContent();
    } catch (InvalidPathException ex) {
      throw new IllegalArgumentException("Provided string is not a path.");
    }

  }

  public Directory(Path path) {
    if (!Files.isDirectory(path)) {
      throw new IllegalArgumentException("Provided path is not a directory.");
    }
    dirPath = path;
    readContent();
  }

  private void readContent() {
    // TODO: 03.11.2023 считать директорию, заполнить списки файлов и директорий
  }

  public List<Directory> getSubDirectories() {
    return subDirectories;
  }

  public List<File> getFiles() {
    return files;
  }
}
