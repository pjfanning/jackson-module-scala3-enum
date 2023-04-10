package com.github.pjfanning.`enum`

import com.github.pjfanning.`enum`.deser.EnumDeserializerModule
import com.github.pjfanning.`enum`.ser.EnumSerializerModule

class EnumModule extends EnumSerializerModule with EnumDeserializerModule {
  override def getModuleName: String = "EnumModule"
}

object EnumModule extends EnumModule
