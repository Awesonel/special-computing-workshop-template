package ru.spbu.apcyb.svp.tasks.dirscan;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Directory Class.
 */
public class Directory {

  private final Path dirPath;
  private final List<Directory> subDirectories = new ArrayList<>();
  private final List<File> files = new ArrayList<>();

  /**
   * Creates a directory object at the given path.
   *
   * @param path directory path as a string
   */
  public Directory(String path) {
      this(Path.of(path).toAbsolutePath());
  }

  /**
   * Creates a directory object at the given path.
   *
   * @param path directory path
   */
  public Directory(Path path) {
    if (!Files.isDirectory(path)) {
      throw new IllegalArgumentException("Provided string is not a directory.");
    }
    dirPath = path;
    readContent();
  }

  private void readContent() {
    try (DirectoryStream<Path> content = Files.newDirectoryStream(dirPath)) {

      for (Path subPath : content) {
        if (Files.isDirectory(subPath)) {
          subDirectories.add(new Directory(subPath));
        } else {
          files.add(subPath.toFile());
        }
      }

    } catch (IOException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  public Path getDirPath() {
    return dirPath;
  }

  public List<Directory> getSubDirectories() {
    return subDirectories;
  }

  public List<File> getFiles() {
    return files;
  }
}
