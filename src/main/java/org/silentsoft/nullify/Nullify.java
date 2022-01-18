package org.silentsoft.nullify;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

public class Nullify {

    private Nullify() { }

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

    public static <T> T of(T object, T defaultValue) {
        return isNull(object) ? defaultValue : object;
    }

    public static <T> String toString(T object) {
        return isNull(object) ? "" : object.toString();
    }

    public static <T> boolean isNull(T object) {
        return of(object) == null;
    }

    public static <T> boolean isNotNull(T object) {
        return !isNull(object);
    }

}
