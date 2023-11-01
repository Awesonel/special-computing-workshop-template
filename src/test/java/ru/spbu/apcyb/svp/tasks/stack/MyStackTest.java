package ru.spbu.apcyb.svp.tasks.stack;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyStackTest {
  
  @Test
  void testEquals() {
    MyStack<Integer> stack1 = new MyStack<>(new Integer[]{1, 2, 3});
    MyStack<Integer> stack2 = new MyStack<>(new Integer[]{1, 2, 3});
    MyStack<Integer> stack3 = new MyStack<>(new Integer[]{1, 2, 4});
    MyStack<Integer> stack4 = new MyStack<>(new Integer[]{});
    MyStack<Integer> stack5 = new MyStack<>();

    Assertions.assertEquals(stack1, stack2);
    Assertions.assertNotEquals(stack1, stack3);
    Assertions.assertNotEquals(stack1, stack4);
    Assertions.assertEquals(stack4, stack5);
  }
  
  @Test
  void testPush() {
    MyStack<Integer> actual = new MyStack<>();
    MyStack<Integer> expected1 = new MyStack<>(new Integer[]{null});
    MyStack<Integer> expected2 = new MyStack<>(new Integer[]{null, 2, 3});

    actual.push(null);
    Assertions.assertEquals(expected1, actual);

    actual.push(2);
    actual.push(3);
    Assertions.assertEquals(expected2, actual);
  }

  @Test
  void testManyPushes() {
    MyStack<Integer> actual = new MyStack<>(new Integer[]{});
    Integer[] expectedArray = new Integer[200];

    for (int i = 0; i < 200; i++) {
      actual.push(i);
      expectedArray[i] = i;
    }

    MyStack<Integer> expected = new MyStack<>(expectedArray);
    Assertions.assertEquals(expected, actual);
  }
  
  @Test
  void testPop() {
    MyStack<Integer> testStack = new MyStack<>();
    MyStack<Integer> expected = new MyStack<>(new Integer[]{3});
    Assertions.assertThrows(EmptyStackException.class, testStack::pop);

    testStack.push(3);
    testStack.push(5);
    Assertions.assertEquals(5, testStack.pop());

    Assertions.assertEquals(expected, testStack);
  }
  
  @Test
  void testPeek() {
    MyStack<Integer> testStack = new MyStack<>();
    Assertions.assertNull(testStack.peek());

    testStack.push(1);
    Assertions.assertEquals(1, testStack.peek());

    testStack.push(3);
    Assertions.assertEquals(3, testStack.peek());
    Assertions.assertEquals(3, testStack.peek());

  }
  
  @Test
  void testIsEmpty() {
    MyStack<Integer> stack1 = new MyStack<>();
    MyStack<Integer> stack2 = new MyStack<>(new Integer[0]);
    MyStack<Integer> stack3 = new MyStack<>(new Integer[]{});
    MyStack<Integer> stack4 = new MyStack<>(new Integer[5]);
    MyStack<Integer> stack5 = new MyStack<>(new Integer[]{3, -1});

    Assertions.assertTrue(stack1.isEmpty());
    Assertions.assertTrue(stack2.isEmpty());
    Assertions.assertTrue(stack3.isEmpty());
    Assertions.assertFalse(stack4.isEmpty());
    Assertions.assertFalse(stack5.isEmpty());

  }

}
