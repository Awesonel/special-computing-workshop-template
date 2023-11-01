package ru.spbu.apcyb.svp.tasks.stack;

import java.util.EmptyStackException;
import java.util.Stack;
import ru.spbu.apcyb.svp.tasks.arraylist.MyArrayList;

/**
 * Stack's own implementation.
 *
 * @param <T> type of stored data
 */
public class MyStack<T> extends Stack<T> {

  transient MyArrayList<T> data;

  /**
   * Creating an empty stack.
   */
  public MyStack() {
    data = new MyArrayList<>();
  }

  /**
   * Creating a stack based on the src array.
   *
   * @param src array on the basis of which the stack will be created
   */
  public MyStack(T[] src) {
    data = new MyArrayList<>(src);
  }

  @Override
  public T push(T item) {
    data.add(item);
    return item;
  }

  @Override
  public synchronized T pop() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return data.remove(data.size() - 1);
  }

  @Override
  public synchronized T peek() {
    if (isEmpty()) {
      return null;
    }
    return data.get(data.size() - 1);
  }

  @Override
  public synchronized boolean isEmpty() {
    return data.isEmpty();
  }

  @Override
  public synchronized boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    return data.equals(((MyStack<?>) obj).data);
  }

  @Override
  public synchronized int hashCode() {
    return data.size();
  }
}
