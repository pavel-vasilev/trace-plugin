# Trace
Library that let you trace method calls without changing its body. Works only on Android

## Installation

```groovy
plugins {
    id 'trace' version '1.0.0'
}

trace {
    enabled = true
}
```

## Usage

#### Before

```kotlin
fun methodUnderTrace() {
    Trace.beginSection("myEvent")
    // method body
    Trace.endSection()
}
```

#### After

```kotlin
@Trace("myEvent")
fun methodUnderTrace() {
    // method body
}
```