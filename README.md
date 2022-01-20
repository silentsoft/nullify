# Nullify

[![Maven Central](https://img.shields.io/maven-central/v/org.silentsoft/nullify)](https://search.maven.org/artifact/org.silentsoft/nullify)
[![Build Status](https://app.travis-ci.com/silentsoft/nullify.svg?branch=main)](https://app.travis-ci.com/silentsoft/nullify)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=silentsoft_nullify&metric=alert_status)](https://sonarcloud.io/dashboard?id=silentsoft_nullify)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=silentsoft_nullify&metric=coverage)](https://sonarcloud.io/dashboard?id=silentsoft_nullify)
[![Hits](https://hits.sh/github.com/silentsoft/nullify.svg)](https://hits.sh)

`Nullify` for null representations and assertions of objects.

## Philosophy
A semantic emptiness is a key concept used to indicate the absence of a value.
Even if an object is not actually referenced as `null`, it can be considered `null` if it is semantically filled with empty values.

### In the beginning there was...
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
        if (value != null && value.length() > 0) {
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

## Maven Central
```xml
<dependency>
    <groupId>org.silentsoft</groupId>
    <artifactId>nullify</artifactId>
    <version>1.0.1</version>
</dependency>
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please note we have a [CODE_OF_CONDUCT](https://github.com/silentsoft/nullify/blob/main/CODE_OF_CONDUCT.md), please follow it in all your interactions with the project.

## License
Please refer to [LICENSE](https://github.com/silentsoft/nullify/blob/main/LICENSE.txt).
