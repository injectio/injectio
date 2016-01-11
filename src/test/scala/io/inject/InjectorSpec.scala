package io.inject

import io.inject.mock.impl.{ActivityImpl, ViewImpl}
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Sergey Ivonchik <sergey @ mintpay.by>
  */
class InjectorSpec extends FlatSpec with Matchers {
  "An Injector" should "correctly inject mock values" in {
    val activity = new ActivityImpl
    val view = new ViewImpl(0)

    activity.getViewOne should not be null
    activity.getViewOne.getId shouldBe 1

    activity.getViewTwo should not be null
    activity.getViewTwo.getId shouldBe 2

    activity.isBooleanRes shouldBe true
    activity.getStringArray should not be null

    view.getSubView should not be null
    view.getDimmen shouldBe 2.0f
    view.getIntArray should not be null
    view.getEmpty shouldBe null
    view.getZero shouldBe 0
    view.getNull should equal (null)
  }
}
