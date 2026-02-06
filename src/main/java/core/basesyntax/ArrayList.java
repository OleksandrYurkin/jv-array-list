package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] objects = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        if (size == objects.length) {
            Object[] copy = new Object[(int) (objects.length * 1.5)];
            System.arraycopy(objects, 0, copy, 0, objects.length);
            objects = copy;
        }
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        if (size == objects.length) {
            Object[] copy = new Object[(int) (objects.length * 1.5)];
            System.arraycopy(objects, 0, copy, 0, objects.length);
            objects = copy;
        }
        for (int i = size; i > index; i--) {
            objects[i] = objects[i - 1];
        }

        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) objects[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index out of bound");
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            objects[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index out of bound");
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            final T removed = (T) objects[index];

            for (int i = index; i < size - 1; i++) {
                objects[i] = objects[i + 1];
            }

            objects[--size] = null; // уменьшили size и сразу очистили

            return removed;
        }
        throw new ArrayListIndexOutOfBoundsException("Index out of bound");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            boolean equals;

            if (element == null) {
                equals = objects[i] == null;
            } else {
                equals = element.equals(objects[i]);
            }

            if (equals) {
                final T removed = (T) objects[i];

                for (int j = i; j < size - 1; j++) {
                    objects[j] = objects[j + 1];
                }

                objects[--size] = null;

                return removed;
            }
        }
        throw new java.util.NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
