package org.silentsoft.nullify;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Nullify is a utility class for null representations and assertions of objects.
 */
public class Nullify {

    private Nullify() { }

    /**
     * Returns the object itself if it is not null nor semantically empty (empty string, empty collection, empty map, etc.) otherwise null</p>
     * <p>Semantically empty cases are:</p>
     * <ul>
     *   <li>Length of {@link CharSequence} is 0</li>
     *   <li>{@link Character} is equal to {@link Character#MIN_VALUE}</li>
     *   <li>{@link Iterable} is empty</li>
     *   <li>{@link Iterable} is not empty but all elements are semantically empty</li>
     *   <li>{@link Map} is empty</li>
     *   <li>{@link Map} is not empty but all values are semantically empty except for keys</li>
     *   <li>Array is empty</li>
     *   <li>Array is not empty but all elements are semantically empty</li>
     * </ul>
     * Note that all primitive types except {@code char} are not considered as empty even if they are equal to their default values. (e.g. {@code 0}, {@code false}, etc.)
     *
     * @param object the object to be checked
     * @return null if the object is null or semantically empty, otherwise the object itself
     */
    @SuppressWarnings("rawtypes")
    public static <T> T of(T object) {
        if (object == null) {
            return null;
        } else if (object instanceof CharSequence) {
            return ((CharSequence) object).length() <= 0 ? null : object;
        } else if (object instanceof Character) {
            return (Character) object == 0 ? null : object;
        } else if (object instanceof Iterable) {
            if (object instanceof Collection && ((Collection) object).isEmpty()) {
                return null;
            }
            Iterable iterable = (Iterable) object;
            for (Object item : iterable) {
                if (of(item) != null) {
                    return object;
                }
            }
            return null;
        } else if (object instanceof Map) {
            Map map = (Map) object;
            return map.size() <= 0 || of(map.values()) == null ? null : object;
        } else if (object instanceof Object[]) {
            Object[] objects = ((Object[]) object);
            return objects.length <= 0 || Stream.of(objects).allMatch(o -> of(o) == null) ? null : object;
        } else if (object instanceof int[]) {
            return ((int[]) object).length <= 0 ? null : object;
        } else if (object instanceof long[]) {
            return ((long[]) object).length <= 0 ? null : object;
        } else if (object instanceof double[]) {
            return ((double[]) object).length <= 0 ? null : object;
        } else if (object instanceof float[]) {
            return ((float[]) object).length <= 0 ? null : object;
        } else if (object instanceof short[]) {
            return ((short[]) object).length <= 0 ? null : object;
        } else if (object instanceof byte[]) {
            return ((byte[]) object).length <= 0 ? null : object;
        } else if (object instanceof boolean[]) {
            return ((boolean[]) object).length <= 0 ? null : object;
        } else if (object instanceof char[]) {
            char[] chars = ((char[]) object);
            if (chars.length <= 0) {
                return null;
            }
            for (char c : chars) {
                if (of(c) != null) {
                    return object;
                }
            }
            return null;
        }
        return object;
    }

    /**
     * Returns the object itself if it is not null nor semantically empty (empty string, empty collection, empty map, etc.) otherwise {@code defaultValue}
     *
     * @param object the object to be checked
     * @param defaultValue the default value to be returned if the object is null or semantically empty
     * @return {@code defaultValue} if the object is null or semantically empty, otherwise the object itself
     * @see #of(Object)
     */
    public static <T> T of(T object, T defaultValue) {
        return isNull(object) ? defaultValue : object;
    }

    /**
     * Returns empty string if the object is null or semantically empty (empty string, empty collection, empty map, etc.) otherwise {@link Object#toString()} of the object
     *
     * @param object the object to be checked
     * @return a string representation of the object
     * @see Object#toString()
     */
    public static <T> String toString(T object) {
        return isNull(object) ? "" : object.toString();
    }

    /**
     * Returns {@code true} if the object is null or semantically empty (empty string, empty collection, empty map, etc.) otherwise {@code false}
     *
     * @param object the object to be checked
     * @return a boolean value indicating whether the object is null or semantically empty
     * @see #isNotNull(Object)
     * @see #of(Object)
     * @see #of(Object, Object)
     */
    public static <T> boolean isNull(T object) {
        return of(object) == null;
    }

    /**
     * Returns {@code true} if the object is not null and semantically not empty (not empty string, not empty collection, not empty map, etc.) otherwise {@code false}
     *
     * @param object the object to be checked
     * @return a boolean value indicating whether the object is not null and not semantically empty
     * @see #isNull(Object)
     * @see #of(Object)
     * @see #of(Object, Object)
     */
    public static <T> boolean isNotNull(T object) {
        return !isNull(object);
    }

}
