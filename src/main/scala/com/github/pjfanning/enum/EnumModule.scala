package com.github.pjfanning.`enum`

import com.github.pjfanning.`enum`.deser.EnumDeserializerModule
import com.github.pjfanning.`enum`.ser.EnumSerializerModule

class EnumModule extends EnumSerializerModule with EnumDeserializerModule

object EnumModule extends EnumModule
