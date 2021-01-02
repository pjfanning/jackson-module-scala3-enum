package com.github.pjfanning.`enum`

import com.fasterxml.jackson.databind.json.JsonMapper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EnumDeserializerSpec extends AnyWordSpec with Matchers {
  "EnumModule" should {
    "deserialize ColorEnum" in {
      val mapper = JsonMapper.builder().addModule(EnumModule).build()
      val red = s""""${ColorEnum.Red}""""
      mapper.readValue(red, classOf[ColorEnum]) shouldEqual(ColorEnum.Red)
    }
    "deserialize JavaCompatibleColorEnum" in {
      val mapper = JsonMapper.builder().addModule(EnumModule).build()
      mapper.writeValueAsString(JavaCompatibleColorEnum.Red) shouldEqual (s""""${JavaCompatibleColorEnum.Red}"""")
    }
  }
}
