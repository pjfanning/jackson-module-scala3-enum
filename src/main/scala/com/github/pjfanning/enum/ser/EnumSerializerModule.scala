package com.github.pjfanning.`enum`.ser

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.ser.Serializers
import com.fasterxml.jackson.databind._
import com.github.pjfanning.`enum`.JacksonModule

import scala.languageFeature.postfixOps
import scala.reflect.Enum

private object EnumSerializer extends JsonSerializer[Enum] {
  def serialize(value: Enum, jgen: JsonGenerator, provider: SerializerProvider): Unit =
    provider.defaultSerializeValue(value.toString, jgen)
}

private object EnumSerializerResolver extends Serializers.Base {
  private val EnumClass = classOf[Enum]

  override def findSerializer(config: SerializationConfig, javaType: JavaType, beanDesc: BeanDescription): JsonSerializer[Enum] =
    if (EnumClass.isAssignableFrom(javaType.getRawClass))
      EnumSerializer
    else None.orNull
}

trait EnumSerializerModule extends JacksonModule {
  this += { _ addSerializers EnumSerializerResolver }
}
