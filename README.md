# jackson-module-scala3-enum

![Build Status](https://github.com/pjfanning/jackson-module-scala3-enum/actions/workflows/ci.yml/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.pjfanning/jackson-module-scala3-enum_3/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pjfanning/jackson-module-scala3-enum_3)

Jackson support for Scala3 [enums](https://dotty.epfl.ch/docs/reference/enums/enums.html).

This was moved into the main jackson-module-scala lib in its 2.17.0 release (https://github.com/FasterXML/jackson-module-scala/issues/501). So there will be no further releases of this separate lib. DefaultScalaModule includes this module.

```
libraryDependencies += "com.github.pjfanning" %% "jackson-module-scala3-enum" % "2.16.0"
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

The code uses `toString` on the enum instance to get the serialized value and when deserializing, `valueOf(String)` on the derived companion object is used. It may be possible to use Jackson annotations to modify this behaviour but there have been some issues with how the Scala 3 compiler handles Java annotations (some fixed but some still open).
