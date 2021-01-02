package com.github.pjfanning.`enum`

import com.fasterxml.jackson.databind.json.JsonMapper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EnumSerializerSpec extends AnyWordSpec with Matchers {
  "EnumModule" should {
    "serialize ColorEnum" in {
      val mapper = JsonMapper.builder().addModule(EnumModule).build()
      mapper.writeValueAsString(ColorEnum.Red) shouldEqual (s""""${ColorEnum.Red}"""")
    }
    "serialize JavaCompatibleColorEnum" in {
      val mapper = JsonMapper.builder().addModule(EnumModule).build()
      mapper.writeValueAsString(ColorEnum.Red) shouldEqual (s""""${ColorEnum.Red}"""")
    }
  }
}
