# jackson-module-scala3-enum

[![Build Status](https://travis-ci.com/pjfanning/jackson-module-scala3-enum.svg?branch=master)](https://travis-ci.com/pjfanning/jackson-module-scala3-enum)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.pjfanning/jackson-module-scala3-enum_3.0.0-RC2/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pjfanning/jackson-module-scala3-enum_3.0.0-RC2)

Jackson support for Scala3 [enums](https://dotty.epfl.ch/docs/reference/enums/enums.html).

```
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala3-enum" % "2.12.0"
```

```
import com.github.pjfanning.`enum`.EnumModule

val mapper = JsonMapper.builder()
  .addModule(DefaultScalaModule)
  .addModule(EnumModule)
  .build()

//old approach - will not work in Jackson 3
val mapper = new ObjectMapper()
mapper.registerModule(DefaultScalaModule)
mapper.registerModule(EnumModule)
```
