package ru.spbu.apcyb.svp.tasks.atm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Calculator of combinations and their number.
 */
public class CombinationsCalculator {

  private final List<Integer> numbers;
  private final long targetSum;
  private final List<List<Integer>> combinations;
  private long combinationsNumber;

  /**
   * Create a calculator and calculate combinations for given nominals and target amount.
   *
   * @param nominals given nominals
   * @param target target sum
   */
  protected CombinationsCalculator(List<Integer> nominals, long target) {
    this.numbers = nominals;
    this.targetSum = target;
    this.combinationsNumber = 0;
    this.combinations = calculateCombinations();

  }

  private List<List<Integer>> calculateCombinations() {

    List<Integer> combination = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    Deque<StackData> todoStack = new ArrayDeque<>();
    todoStack.push(new StackData(numbers, targetSum, combination));

    while (!todoStack.isEmpty()) {

      StackData current = todoStack.pop();

      long s = 0;
      for (long x : current.partial) {
        s += x;
      }

      if (s == current.target) {
        result.add(current.partial);
        combinationsNumber++;
      }

      if (s >= current.target) {
        continue;
      }

      for (int i = 0; i < current.nominals.size(); i++) {
        ArrayList<Integer> remaining = new ArrayList<>();
        int n = current.nominals.get(i);
        for (int j = i; j < current.nominals.size(); j++) {
          remaining.add(current.nominals.get(j));
        }
        ArrayList<Integer> partialRec = new ArrayList<>(current.partial);

        partialRec.add(n);
        todoStack.push(new StackData(remaining, targetSum, partialRec));
      }
    }
    return result;
  }

  protected long getCombinationsNumber() {
    return combinationsNumber;
  }

  protected List<List<Integer>> getCombinations() {
    return combinations;
  }

  private record StackData(List<Integer> nominals, long target, List<Integer> partial) {

  }
}

