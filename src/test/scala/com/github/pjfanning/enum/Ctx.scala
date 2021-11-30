package com.github.pjfanning.`enum`

object Ctx {
  enum ColorEnum { case Red, Green, Blue }
}

case class CtxCar(make: String, color: Ctx.ColorEnum)
