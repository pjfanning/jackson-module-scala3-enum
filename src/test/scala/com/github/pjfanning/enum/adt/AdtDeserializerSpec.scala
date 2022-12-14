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
      mapper.readValue(red, Color.Red.getClass) shouldEqual Color.Red
    }
  }
}
