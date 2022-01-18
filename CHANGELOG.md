# 1.0.0 (19 Jan 2022)

## Philosophy
A semantic emptiness is a key concept used to indicate the absence of a value.
Even if an object is not actually referenced as `null`, it can be considered `null` if it is semantically filled with empty values.

### In the beginning there was ...
You had to convert an empty value to `null` to persist it in a database.
Also you had to convert `null` to an empty string to display it in a GUI or something elsewhere like Stream or Writer.
If you don't want to use `null` at all, you had to write a code to getting default value/object. like this:

```java
if (value != null && value.length() <= 0) {
    entity.setValue(null);
} else {
    entity.setValue(value);
}
```

or

```java
entity.setValue(value == null || value.length() <= 0 ? null : value);
```

### Okay, That's totally fine. But how about this?
```java
entity.setValue(Nullify.of(value));
```

in some cases, you might want to use empty string(`""`) instead of `null`:

```java
Nullify.toString(value);
```

### Further, checking for semantically empty
```java
if (collection == null || collection.size() <= 0) {
    collection = Arrays.asList("default_value");
} else {
    boolean isSemanticallyEmpty = true;
    for (String value : collection) {
        if (value == null || value.length() <= 0) {
            isSemanticallyEmpty = false;
            break;
        }
    }
    if (isSemanticallyEmpty) {
        collection = Arrays.asList("default_value");
    }
}
```

above code can also be replaced with:

```java
Nullify.of(collection, Arrays.asList("default_value"));
```

## Predicates
- `Nullify.isNull(object)`
- `Nullify.isNotNull(object)`