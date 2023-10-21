package ru.spbu.apcyb.svp.tasks.atm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AtmTest {

  @Test
  void testInput() {
    InputStream input = new ByteArrayInputStream(("1 2 3 \n10").getBytes());
    AtmMain.run(input);
  }

  @Test
  void testSimple() {

    long target = 5;
    int[] nominals = {3, 2};

    Atm testAtm = new Atm(nominals);
    testAtm.calculateBanknoteCombinations(target);

    List<List<Integer>> actual = testAtm.getCurrentCombinations();
    long actualNumber = testAtm.getCurrentCombNumber();

    List<List<Integer>> expected = new ArrayList<>();
    expected.add(Arrays.stream(new int[]{2, 3}).boxed().toList());
    long expectedNumber = 1;

    Assertions.assertEquals(target, testAtm.getCurrentTarget());
    Assertions.assertEquals(Arrays.stream(nominals).boxed().collect(Collectors.toSet()),
        new HashSet<>(testAtm.getNominals()));
    Assertions.assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    Assertions.assertEquals(expectedNumber, actualNumber);
  }

  @Test
  void testSimple2() {

    long target = 4;
    int[] nominals = {2, 1};

    Atm testAtm = new Atm(nominals);
    testAtm.calculateBanknoteCombinations(target);

    List<List<Integer>> actual = testAtm.getCurrentCombinations();
    long actualNumber = testAtm.getCurrentCombNumber();

    List<List<Integer>> expected = new ArrayList<>();
    expected.add(Arrays.stream(new int[]{2, 2}).boxed().toList());
    expected.add(Arrays.stream(new int[]{1, 1, 2}).boxed().toList());
    expected.add(Arrays.stream(new int[]{1, 1, 1, 1}).boxed().toList());
    long expectedNumber = 3;

    Assertions.assertEquals(target, testAtm.getCurrentTarget());
    Assertions.assertEquals(Arrays.stream(nominals).boxed().collect(Collectors.toSet()),
        new HashSet<>(testAtm.getNominals()));
    Assertions.assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    Assertions.assertEquals(expectedNumber, actualNumber);
  }

  @Test
  void testSimple2ReversedNominals() {

    long target = 4;
    int[] nominals = {1, 2};

    Atm testAtm = new Atm(nominals);
    testAtm.calculateBanknoteCombinations(target);

    List<List<Integer>> actual = testAtm.getCurrentCombinations();
    long actualNumber = testAtm.getCurrentCombNumber();

    List<List<Integer>> expected = new ArrayList<>();
    expected.add(Arrays.stream(new int[]{2, 2}).boxed().toList());
    expected.add(Arrays.stream(new int[]{1, 1, 2}).boxed().toList());
    expected.add(Arrays.stream(new int[]{1, 1, 1, 1}).boxed().toList());
    long expectedNumber = 3;

    Assertions.assertEquals(target, testAtm.getCurrentTarget());
    Assertions.assertEquals(Arrays.stream(nominals).boxed().collect(Collectors.toSet()),
        new HashSet<>(testAtm.getNominals()));
    Assertions.assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    Assertions.assertEquals(expectedNumber, actualNumber);
  }

  @Test
  void testOneCoin() {

    long target = 1000;
    int[] nominals = {1};

    Atm testAtm = new Atm(nominals);
    testAtm.calculateBanknoteCombinations(target);

    List<List<Integer>> actual = testAtm.getCurrentCombinations();
    long actualNumber = testAtm.getCurrentCombNumber();

    List<List<Integer>> expected = new ArrayList<>();
    expected.add(new ArrayList<>());
    for (int i = 0; i < 1000; i++) {
      expected.get(0).add(1);
    }
    long expectedNumber = 1;

    Assertions.assertEquals(target, testAtm.getCurrentTarget());
    Assertions.assertEquals(Arrays.stream(nominals).boxed().collect(Collectors.toSet()),
        new HashSet<>(testAtm.getNominals()));
    Assertions.assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    Assertions.assertEquals(expectedNumber, actualNumber);
  }

  @Test
  void testOneLargeCoin() {

    long target = 100000000;
    int[] nominals = {100000000};

    Atm testAtm = new Atm(nominals);
    testAtm.calculateBanknoteCombinations(target);

    List<List<Integer>> actual = testAtm.getCurrentCombinations();
    long actualNumber = testAtm.getCurrentCombNumber();

    List<List<Integer>> expected = new ArrayList<>();
    expected.add(Arrays.stream(new int[]{100000000}).boxed().toList());
    long expectedNumber = 1;

    Assertions.assertEquals(target, testAtm.getCurrentTarget());
    Assertions.assertEquals(Arrays.stream(nominals).boxed().collect(Collectors.toSet()),
        new HashSet<>(testAtm.getNominals()));
    Assertions.assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    Assertions.assertEquals(expectedNumber, actualNumber);
  }

  @Test
  void testNoCombinations() {

    long target = 5;
    int[] nominals = {10, 6};

    Atm testAtm = new Atm(nominals);
    testAtm.calculateBanknoteCombinations(target);

    List<List<Integer>> actual = testAtm.getCurrentCombinations();
    long actualNumber = testAtm.getCurrentCombNumber();

    long expectedNumber = 0;

    Assertions.assertEquals(target, testAtm.getCurrentTarget());
    Assertions.assertEquals(Arrays.stream(nominals).boxed().collect(Collectors.toSet()),
        new HashSet<>(testAtm.getNominals()));
    Assertions.assertEquals(new HashSet<>(new ArrayList<>()), new HashSet<>(actual));
    Assertions.assertEquals(expectedNumber, actualNumber);

  }

  @Test
  void testEmptyNominals() {
    Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      int[] nominals = {};
      new Atm(nominals);
    });

    String expectedMessage = "Incorrect input: nominals are empty";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void testNegativeNominal() {
    Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      int[] nominals = {1, 2, -3, 6};
      new Atm(nominals);
    });

    String expectedMessage = "Incorrect input: all nominals should be positive";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void testZeroNominal() {
    Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      int[] nominals = {1, 2, 0, 6};
      new Atm(nominals);
    });

    String expectedMessage = "Incorrect input: all nominals should be positive";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void testNegativeTarget() {
    int[] nominals = {1, 2, 6};
    long targetSum = -3;
    Atm atm = new Atm(nominals);
    Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> atm.calculateBanknoteCombinations(targetSum));

    String expectedMessage = "Incorrect input: required sum should be positive";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void testZeroTarget() {
    int[] nominals = {1, 2, 6};
    long targetSum = 0;
    Atm atm = new Atm(nominals);
    Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> atm.calculateBanknoteCombinations(targetSum));

    String expectedMessage = "Incorrect input: required sum should be positive";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void testDuplicateNominals() {
    long target = 5;
    int[] nominals = {1, 1};

    Atm testAtm = new Atm(nominals);
    testAtm.calculateBanknoteCombinations(target);

    List<List<Integer>> actual = testAtm.getCurrentCombinations();
    long actualNumber = testAtm.getCurrentCombNumber();

    List<List<Integer>> expected = new ArrayList<>();
    expected.add(Arrays.stream(new int[]{1, 1, 1, 1, 1}).boxed().toList());
    long expectedNumber = 1;

    Assertions.assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    Assertions.assertEquals(expectedNumber, actualNumber);

  }

}
