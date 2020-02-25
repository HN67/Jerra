package jerra;

/**
 * Interface to specify that a class has a copy method.
 * Copyable has a generic type, which should usually be specified
 * to the same type as the implementing class.
 * Specifically, if a class implements Copyable<type>,
 * it has an instance method .copy() which returns 'type'.
 */
public interface Copyable<T> {

    /**
     * Returns a copy of the object
     * @return should be the same type as the object
     */
    public T copy();
    
}
