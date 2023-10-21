package ru.spbu.apcyb.svp.tasks.atm;

import java.util.Arrays;
import java.util.List;

/**
 * ATM Class.
 */
public class Atm {

  private final List<Integer> nominals;
  private long currentTarget = -1;
  private List<List<Integer>> currentCombinations = null;
  private long currentCombNumber = -1;

  /**
   * Creating an ATM with specified nominals.
   *
   * @param nominals - allowable nominals of banknotes in the ATM (not empty, all numbers are
   *                 positive)
   */
  public Atm(int[] nominals) {
    if (Arrays.equals(nominals, new int[]{})) {
      throw new IllegalArgumentException("Incorrect input: nominals are empty");
    }
    for (int nominal : nominals) {
      if (nominal <= 0) {
        throw new IllegalArgumentException("Incorrect input: all nominals should be positive");
      }
    }

    this.nominals = Arrays.stream(nominals).sorted().distinct().boxed().toList();
  }

  /**
   * Compute all representations of requiredSum as combinations of available nominals. All
   * combinations are non-repeating, sorted in descending order
   *
   * @param requiredSum required sum
   */
  public void calculateBanknoteCombinations(long requiredSum) {

    if (requiredSum <= 0) {
      throw new IllegalArgumentException("Incorrect input: required sum should be positive");
    }

    this.currentTarget = requiredSum;
    CombinationsCalculator calculator = new CombinationsCalculator(this.nominals, requiredSum);
    this.currentCombinations = calculator.getCombinations();
    this.currentCombNumber = calculator.getCombinationsNumber();

  }

  public List<Integer> getNominals() {
    return nominals;
  }

  public long getCurrentTarget() {
    return currentTarget;
  }

  public List<List<Integer>> getCurrentCombinations() {
    return currentCombinations;
  }

  public long getCurrentCombNumber() {
    return currentCombNumber;
  }
}

