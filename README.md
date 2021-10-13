# mrz-parser

[![Build Status](https://travis-ci.com/Coppernic/mrz-parser.svg?branch=master)](https://travis-ci.com/Coppernic/mrz-parser)

MRZ parser in Kotlin

## Motivation

This library aims to provide an API in kotlin to parse MRZ of travel and identity documents.

## Download

Include instructions on how to integrate the library into your projects. For instance install in your build.gradle:

```groovy
repositories {
    maven { url "https://nexus.coppernic.fr/repository/libs-release" }
}
dependencies {
    implementation 'fr.coppernic.lib.mrz:mrz-parser:1.0.0'
}
```

## Usage

- Create parser

```kotlin
val parser = MrzParser()
```

- Parse

```kotlin
val string = """
            I<UTOD231458907<<<<<<<<<<<<<<<
            7408122F1204159UTO<<<<<<<<<<<6
            ERIKSSON<<ANNA<MARIA<<<<<<<<<<
    """.trimIndent()
val mrz = parser.parse(string)
```

- You can provide options to parser

```kotlin
val string = """
            I<UTOD231458907<<<<<<<<<<<<<<<
            7408122F1204159UTO<<<<<<<<<<<6
            ERIKSSON<<ANNA<MARIA<<<<<<<<<<
    """.trimIndent()
val options = MrzParserOptions(
    lenient = true
)
val mrz = parser.parse(string, options)
```

### Logs

This library uses [SLF4J](http://www.slf4j.org/) for logging. Please use android binding to
log into logcat. More info on [Android binding](http://www.slf4j.org/android/)

```groovy
dependencies {
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-android
    implementation 'org.slf4j:slf4j-android:1.7.30'
}
```

You can also bind SLF4J to timber. In this case please use this dependency

```groovy
dependencies {
    implementation 'com.arcao:slf4j-timber:3.1'
}
```

To activate verbose logging, please add this into your code :

```kotlin
LogDefines.verbose  = true
```

One `TAG` is used for all logging from lib. It would be easy to filter on this tag if you
want to disable all logging from lib. Filtering can be done with `Timber` and a `Tree`
from [Treessence](https://github.com/bastienpaulfr/Treessence)


```groovy
dependencies {
    implementation 'com.github.bastienpaulfr:Treessence:1.0.5'
}
```

## MRZ FOrmat

![image](docs/img/Fields_Distribution.png)

## License

    Copyright (C) 2021 Coppernic

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

