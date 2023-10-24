package ru.spbu.apcyb.svp.tasks.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * Class to run ATM in the console.
 */
public class AtmMain {

  static Logger log = Logger.getLogger(AtmMain.class.getName());

  /**
   * Starting ATM in the console.
   */
  public static void main(String[] args) {
    run(System.in);
  }

  /**
   * Run method.
   *
   * @param in input stream
   */
  public static void run(InputStream in) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    try {
      System.out.println("ATM");
      System.out.print("Enter the nominals lying in the ATM through the space: ");
      List<Integer> nominalsList = Arrays.stream(reader.readLine().split(" "))
          .map(Integer::parseInt).toList();

      int[] nominals = new int[nominalsList.size()];
      IntStream.range(0, nominalsList.size()).forEach(i -> nominals[i] = nominalsList.get(i));
      Atm atm = new Atm(nominals);

      System.out.print("Enter the target amount: ");
      long targetSum = Long.parseLong(reader.readLine());

      atm.calculateBanknoteCombinations(targetSum);

      System.out.println("Number of combinations: " + atm.getCurrentCombNumber());
      System.out.println("Combinations:");
      for (List<Integer> combination : atm.getCurrentCombinations()) {
        System.out.println(combination);
      }
    } catch (IOException ex) {
      log.severe(ex.getMessage());
    }
  }


}
