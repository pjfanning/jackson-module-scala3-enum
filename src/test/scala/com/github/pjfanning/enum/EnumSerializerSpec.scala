package com.github.pjfanning.`enum`

import com.fasterxml.jackson.databind.json.JsonMapper
import  com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EnumSerializerSpec extends AnyWordSpec with Matchers {
  "EnumModule" should {
    "serialize ColorEnum" in {
      val mapper = JsonMapper.builder().addModule(EnumModule).build()
      mapper.writeValueAsString(ColorEnum.Red) shouldEqual (s""""${ColorEnum.Red}"""")
    }
    "serialize ColorEnum with non-singleton EnumModule" in {
      val mapper = JsonMapper.builder().addModule(new EnumModule).build()
      mapper.writeValueAsString(ColorEnum.Red) shouldEqual (s""""${ColorEnum.Red}"""")
    }
    "serialize JavaCompatibleColorEnum" in {
      val mapper = JsonMapper.builder().addModule(EnumModule).build()
      mapper.writeValueAsString(ColorEnum.Red) shouldEqual (s""""${ColorEnum.Red}"""")
    }
    "serialize Car with ColorEnum" in {
      val mapper = JsonMapper.builder().addModule(DefaultScalaModule).addModule(EnumModule).build()
      mapper.writeValueAsString(Car("Perodua", ColorEnum.Green)) shouldEqual (s"""{"make":"Perodua","color":"${ColorEnum.Green}"}""")
    }
  }
}
