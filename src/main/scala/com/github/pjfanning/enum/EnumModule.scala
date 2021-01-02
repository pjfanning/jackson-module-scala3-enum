package com.github.pjfanning.`enum`

import com.github.pjfanning.`enum`.deser.EnumDeserializerModule
import com.github.pjfanning.`enum`.ser.EnumSerializerModule

object EnumModule extends EnumSerializerModule with EnumDeserializerModule
