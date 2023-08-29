package com.example

// the IoT example, part https://doc.akka.io/docs/akka/current/typed/guide/tutorial_3.html

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import org.scalatest.wordspec.AnyWordSpecLike

class DeviceSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {
  import Device._

  "Device actor" must {

    "reply with empty reading if no temperature is known" in {
      // arrange
      val probe = createTestProbe[RespondTemperature]()
      val deviceActor = spawn(Device("group", "device"))
      // act
      deviceActor ! Device.ReadTemperature(requestId = 42, probe.ref)
      // assert
      val response = probe.receiveMessage()
      response.requestId should ===(42)
      response.value should ===(None)
    }
  }
}
