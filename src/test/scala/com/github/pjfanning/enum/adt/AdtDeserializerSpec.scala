package com.github.pjfanning.`enum`.adt

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.github.pjfanning.`enum`.EnumModule
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AdtDeserializerSpec extends AnyWordSpec with Matchers {
  "EnumModule" should {
    "deserialize Color ADT" in {
      val mapper = JsonMapper.builder().addModule(EnumModule).build()
      val red = s""""${Color.Red}""""
      mapper.readValue(red, classOf[Color]) shouldEqual Color.Red
    }
    "fail deserialization of invalid Color ADT" in {
      val mapper = JsonMapper.builder().addModule(EnumModule).build()
      val json = s""""xyz""""
      intercept[IllegalArgumentException] {
        mapper.readValue(json, classOf[Color])
      }
    }
    "deserialize ColorSet" in {
      val mapper = JsonMapper.builder().addModule(DefaultScalaModule).addModule(EnumModule).build()
      val colors = ColorSet(Set(Color.Red, Color.Green))
      val json = mapper.writeValueAsString(colors)
      mapper.readValue(json, classOf[ColorSet]) shouldEqual colors
    }
  }
}
