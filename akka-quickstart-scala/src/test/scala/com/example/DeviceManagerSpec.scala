package com.example

// the IoT example, part https://doc.akka.io/docs/akka/current/typed/guide/tutorial_4.html

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import org.scalatest.wordspec.AnyWordSpecLike
import scala.concurrent.duration._

class DeviceManagerSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {
  import DeviceManager._

  import Device.{RecordTemperature, TemperatureRecorded}

  "DeviceManager actor" must {

    "be able to register a device actor into correct group" in {
      val probe = createTestProbe[DeviceRegistered]()
      val managerActor = spawn(DeviceManager())

      // device1 of groupA
      managerActor ! RequestTrackDevice("groupA", "device1", probe.ref)
      val registeredA1 = probe.receiveMessage()
      val deviceA1 = registeredA1.device

      // device2 of groupA
      managerActor ! RequestTrackDevice("groupA", "device2", probe.ref)
      val registeredA2 = probe.receiveMessage()
      val deviceA2 = registeredA2.device
      deviceA1 should !==(deviceA2)

      // device1 of groupB
      managerActor ! RequestTrackDevice("groupB", "device1", probe.ref)
      val registeredB1 = probe.receiveMessage()
      val deviceB1 = registeredB1.device
      deviceA1 should !==(deviceB1)
      deviceB1 should !==(deviceA2)

      // Check that the device actors are working
      val recordProbe = createTestProbe[TemperatureRecorded]()

      deviceA1 ! RecordTemperature(requestId = 0, 1.0, recordProbe.ref)
      recordProbe.expectMessage(TemperatureRecorded(requestId = 0))

      deviceA2 ! Device.RecordTemperature(requestId = 1, 2.0, recordProbe.ref)
      recordProbe.expectMessage(Device.TemperatureRecorded(requestId = 1))

      deviceB1 ! RecordTemperature(requestId = 3, 8.8, recordProbe.ref)
      recordProbe.expectMessage(TemperatureRecorded(requestId = 3))
    }

    "be able to list active devices of specific group" in {
      val registeredProbe = createTestProbe[DeviceRegistered]()
      val managerActor = spawn(DeviceManager())

      // device1 of groupA
      managerActor ! RequestTrackDevice(
        "groupA",
        "device1",
        registeredProbe.ref
      )
      val registeredA1 = registeredProbe.receiveMessage()
      val deviceA1 = registeredA1.device

      // device2 of groupA
      managerActor ! RequestTrackDevice(
        "groupA",
        "device2",
        registeredProbe.ref
      )
      val registeredA2 = registeredProbe.receiveMessage()
      val deviceA2 = registeredA2.device
      deviceA1 should !==(deviceA2)

      // device1 of groupB
      managerActor ! RequestTrackDevice(
        "groupB",
        "device1",
        registeredProbe.ref
      )
      val registeredB1 = registeredProbe.receiveMessage()
      val deviceB1 = registeredB1.device
      deviceA1 should !==(deviceB1)
      deviceB1 should !==(deviceA2)

      // Check that the group actors are working
      val deviceListProbe = createTestProbe[ReplyDeviceList]()
      
      managerActor ! RequestDeviceList(requestId = 0, groupId = "nonExistingGroup", deviceListProbe.ref)
      deviceListProbe.expectMessage(ReplyDeviceList(requestId = 0, Set.empty))

      managerActor ! RequestDeviceList(requestId = 1, groupId = "groupA", deviceListProbe.ref)
      deviceListProbe.expectMessage(ReplyDeviceList(requestId = 1, Set("device1", "device2")))

      managerActor ! RequestDeviceList(requestId = 3, groupId = "groupB", deviceListProbe.ref)
      deviceListProbe.expectMessage(ReplyDeviceList(requestId = 3, Set("device1")))
    }
  }
}
