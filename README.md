# jackson-module-scala3-enum

![Build Status](https://github.com/pjfanning/jackson-module-scala3-enum/actions/workflows/ci.yml/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.pjfanning/jackson-module-scala3-enum_3/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pjfanning/jackson-module-scala3-enum_3)

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
