package ru.spbu.apcyb.svp.tasks.arraylist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * Array-based implementation of a linear list.
 *
 * @param <T> type of stored objects
 */
public class MyArrayList<T> implements List<T> {

  private static final int INITIAL_SIZE = 10;
  public static final String NOT_IMPLEMENTED = "Method not implemented";
  private Object[] data;
  private int currentMaxSize;
  private int size;

  /**
   * Create an empty list.
   */
  public MyArrayList() {
    data = new Object[INITIAL_SIZE];
    currentMaxSize = data.length;
    size = 0;
  }

  /**
   * Create a list based on the src array.
   *
   * @param src source array
   */
  public MyArrayList(T[] src) {
    data = Arrays.copyOf(src, src.length);
    currentMaxSize = data.length;
    size = src.length;
  }

  /**
   * Increases the data array by a factor of two.
   */
  private void resize() {
    Object[] newData;
    if (data.length == 0) {
      newData = new Object[10];
    } else if (data.length > Integer.MAX_VALUE / 2) {
      newData = new Object[Integer.MAX_VALUE];
    } else {
      newData = new Object[data.length * 2];
    }

    System.arraycopy(data, 0, newData, 0, data.length);
    data = newData;
    currentMaxSize = data.length;
  }

  @Override
  public boolean add(T element) {
    if (size == currentMaxSize) {
      resize();
    }

    data[size] = element;
    size++;
    return true;
  }

  @Override
  public void add(int index, T element) {

    checkIndex(index);
    if (size == currentMaxSize) {
      resize();
    }

    System.arraycopy(data, index, data, index + 1, size - index);
    data[index] = element;
    size++;
  }

  @Override
  public boolean contains(Object o) {

    for (int i = 0; i < size; i++) {
      if (Objects.equals(data[i], o)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }


  @Override
  @SuppressWarnings("unchecked")
  public T get(int index) {
    checkIndex(index);
    return (T) data[index];
  }

  @Override
  @SuppressWarnings("unchecked")
  public T set(int index, T element) {
    checkIndex(index);
    T prevElement = (T) data[index];
    data[index] = element;
    return prevElement;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T remove(int index) {
    checkIndex(index);
    T prevElement = (T) data[index];
    System.arraycopy(data, index + 1, data, index, size - index - 1);
    size--;
    return prevElement;
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    if (size != ((MyArrayList<T>) obj).size) {
      return false;
    }

    for (int i = 0; i < size; i++) {
      if (!Objects.equals(data[i], ((MyArrayList<T>) obj).data[i])) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks whether index is between 0 (inclusive) and size (exclusive).
   *
   * @param index index for validation
   * @throws IndexOutOfBoundsException if the index is outside the boundaries
   */
  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index outside the list boundaries");
    }
  }

  @Override
  public int hashCode() {
    return size;
  }

  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public ListIterator<T> listIterator() {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException(NOT_IMPLEMENTED);
  }

}
