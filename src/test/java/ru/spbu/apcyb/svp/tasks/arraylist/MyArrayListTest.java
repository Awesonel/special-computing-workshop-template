package ru.spbu.apcyb.svp.tasks.arraylist;

import java.util.ArrayList;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyArrayListTest {

  @Test
  void testEquals() {
    MyArrayList<Integer> list1 = new MyArrayList<>(new Integer[]{1, 2, 3});
    MyArrayList<Integer> list2 = new MyArrayList<>(new Integer[]{1, 2, 3});
    MyArrayList<Integer> list3 = new MyArrayList<>(new Integer[]{1, 2, 4});
    MyArrayList<Integer> list4 = new MyArrayList<>(new Integer[]{});
    MyArrayList<Integer> list5 = new MyArrayList<>();
    ArrayList<Integer> list6 = new ArrayList<>(
        IntStream.range(1, 4).boxed().toList());

    Assertions.assertEquals(list1, list2);
    Assertions.assertNotEquals(list1, list3);
    Assertions.assertNotEquals(list1, list4);
    Assertions.assertEquals(list4, list5);
    Assertions.assertNotEquals(list1, list6);

  }

  @Test
  void testAdd() {
    MyArrayList<Integer> actual = new MyArrayList<>(new Integer[]{});

    Integer[] expectedArray = new Integer[200];
    for (int i = 0; i < 200; i++) {
      expectedArray[i] = i;
      actual.add(i);
    }
    MyArrayList<Integer> expected = new MyArrayList<>(expectedArray);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testAddByIndex() {
    MyArrayList<Integer> actual = new MyArrayList<>(new Integer[]{1, 3, 4});
    actual.add(1, 2);
    MyArrayList<Integer> expected = new MyArrayList<>(new Integer[]{1, 2, 3, 4});

    Assertions.assertEquals(expected, actual);
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.add(8, 10));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.add(-1, 10));
  }

  @Test
  void testContains() {
    MyArrayList<Double> testList = new MyArrayList<>(new Double[]{1.0, -10.1, 13.0, 0.0, 0.0});

    Assertions.assertTrue(testList.contains(-10.1));
    Assertions.assertFalse(testList.contains(-10.0));
    Assertions.assertFalse(testList.contains("a"));

  }

  @Test
  void testRemoveByIndex() {
    MyArrayList<Integer> expected = new MyArrayList<>(new Integer[]{2, 3});
    MyArrayList<Integer> actual = new MyArrayList<>();
    actual.add(1);
    actual.add(2);
    actual.add(3);

    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.remove(-1));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.remove(5));
    actual.remove(0);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testEmpty() {
    MyArrayList<Double> notEmptyList = new MyArrayList<>(new Double[]{3.0});
    MyArrayList<Double> emptyList1 = new MyArrayList<>(new Double[]{});
    MyArrayList<Double> emptyList2 = new MyArrayList<>();
    MyArrayList<Double> emptyList3 = new MyArrayList<>(new Double[]{1.0});
    emptyList3.remove(0);

    Assertions.assertTrue(emptyList1.isEmpty());
    Assertions.assertTrue(emptyList2.isEmpty());
    Assertions.assertTrue(emptyList3.isEmpty());
    Assertions.assertFalse(notEmptyList.isEmpty());

  }

  @Test
  void testGet() {
    MyArrayList<Integer> testList = new MyArrayList<>(new Integer[]{0, 1, 2, 3});

    for (int i = 0; i < 4; i++) {
      Assertions.assertEquals(i, testList.get(i));
    }
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.get(-1));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.get(5));

  }

  @Test
  void testSet() {
    MyArrayList<Integer> actual = new MyArrayList<>(new Integer[]{1, 2, 3, 4});
    MyArrayList<Integer> expected = new MyArrayList<>(new Integer[]{1, 2, -3, 4});
    Integer actualRemovedElem = actual.set(2, -3);
    int expectedRemovedElem = 3;

    Assertions.assertEquals(expectedRemovedElem, actualRemovedElem);
    Assertions.assertEquals(expected, actual);

    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.set(-1, 2));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> actual.set(4, 2));
  }

  @Test
  void testSize() {
    MyArrayList<Integer> testList = new MyArrayList<>();

    Assertions.assertEquals(0, testList.size());
    testList.add(1);
    testList.add(2);
    Assertions.assertEquals(2, testList.size());
    testList.remove(testList.size() - 1);
    Assertions.assertEquals(1, testList.size());
  }
}
