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

    "reply with latest temperature reading" in {
      // arrange
      val recordProbe = createTestProbe[TemperatureRecorded]()
      val readProbe = createTestProbe[RespondTemperature]()
      val deviceActor = spawn(Device("group", "device"))

      // act
      deviceActor ! Device.RecordTemperature(requestId = 1, 24.0, recordProbe.ref)
      // assert
      recordProbe.expectMessage(Device.TemperatureRecorded(requestId = 1))

      // act
      deviceActor ! Device.ReadTemperature(requestId = 2, readProbe.ref)
      // assert
      val response1 = readProbe.receiveMessage()
      response1.requestId should === (2)
      response1.value should ===(Some(24.0))

      // act
      deviceActor ! Device.RecordTemperature(requestId = 3, 55.0, recordProbe.ref)
      // assert
      recordProbe.expectMessage(Device.TemperatureRecorded(requestId = 3))

      // act
      deviceActor ! Device.ReadTemperature(requestId = 4, readProbe.ref)
      // assert
      val response2 = readProbe.receiveMessage()
      response2.requestId should ===(4)
      response2.value should ===(Some(55.0))
    }
  }
}
