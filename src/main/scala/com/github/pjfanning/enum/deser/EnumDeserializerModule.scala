package com.github.pjfanning.`enum`.deser

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.deser.{Deserializers, KeyDeserializers}
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.*
import com.github.pjfanning.`enum`.JacksonModule

import scala.languageFeature.postfixOps
import scala.reflect.Enum
import scala.util.Try

private object EnumDeserializerShared {
  val StringClass = classOf[String]
  val EnumClass = classOf[Enum]

  def tryValueOf(clz: Class[_], key: String): Option[_] = {
    Try(clz.getMethod("valueOf", EnumDeserializerShared.StringClass)).toOption.map { method =>
      method.invoke(None.orNull, key)
    }
  }
}

private case class EnumDeserializer[T <: Enum](clazz: Class[T]) extends StdDeserializer[T](clazz) {
  private val clazzName = clazz.getName

  override def deserialize(p: JsonParser, ctxt: DeserializationContext): T = {
    val result = Option(p.getValueAsString).flatMap { text =>
      val objectClassOption = if(clazzName.endsWith("$")) {
        Try(Class.forName(clazzName.substring(0, clazzName.length - 1))).toOption
      } else {
        Some(clazz)
      }
      objectClassOption.flatMap { objectClass =>
        EnumDeserializerShared.tryValueOf(objectClass, text)
      }.asInstanceOf[Option[T]]
    }
    result.getOrElse(throw new IllegalArgumentException(s"Failed to create Enum instance for ${p.getValueAsString}"))
  }
}

private case class EnumKeyDeserializer[T <: Enum](clazz: Class[T]) extends KeyDeserializer {
  private val clazzName = clazz.getName

  override def deserializeKey(key: String, ctxt: DeserializationContext): AnyRef = {
    val objectClassOption = if(clazzName.endsWith("$")) {
      Try(Class.forName(clazzName.substring(0, clazzName.length - 1))).toOption
    } else {
      Some(clazz)
    }
    val result = objectClassOption.flatMap { objectClass =>
      EnumDeserializerShared.tryValueOf(objectClass, key)
    }
    val enumResult = result.getOrElse(throw new IllegalArgumentException(s"Failed to create Enum instance for $key"))
    enumResult.asInstanceOf[AnyRef]
  }
}

private object EnumDeserializerResolver extends Deserializers.Base {
  override def findBeanDeserializer(javaType: JavaType, config: DeserializationConfig, beanDesc: BeanDescription): JsonDeserializer[Enum] =
    if (EnumDeserializerShared.EnumClass isAssignableFrom javaType.getRawClass)
      EnumDeserializer(javaType.getRawClass.asInstanceOf[Class[Enum]])
    else None.orNull
}

private object EnumKeyDeserializerResolver extends KeyDeserializers {
  override def findKeyDeserializer(javaType: JavaType, config: DeserializationConfig, beanDesc: BeanDescription): KeyDeserializer =
    if (EnumDeserializerShared.EnumClass isAssignableFrom javaType.getRawClass)
      EnumKeyDeserializer(javaType.getRawClass.asInstanceOf[Class[Enum]])
    else None.orNull
}

trait EnumDeserializerModule extends JacksonModule {
  this += { _ addDeserializers EnumDeserializerResolver }
  this += { _ addKeyDeserializers EnumKeyDeserializerResolver }
}
