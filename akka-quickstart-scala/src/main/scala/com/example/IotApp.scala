package com.example

// the IoT example, part https://doc.akka.io/docs/akka/current/typed/guide/tutorial_2.html

import akka.actor.typed.ActorSystem

object IotApp {

  def main(args: Array[String]): Unit = {
    // Create ActorSystem and top level supervisor
    ActorSystem[Nothing](IotSupervisor(), "iot-system")
  }
}