//#full-example
package com.example

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import com.example.Greeter.Greet
import com.example.Greeter.Greeted
import org.scalatest.wordspec.AnyWordSpecLike

//#definition
class AkkaQuickstartSpec
    extends ScalaTestWithActorTestKit
    with AnyWordSpecLike {
//#definition

  "A Greeter" must {
    // #test
    "reply to greeted" in {
      val replyProbe = createTestProbe[Greeted]()
      val underTest = spawn(Greeter())
      underTest ! Greet("Santa", replyProbe.ref)
      replyProbe.expectMessage(Greeted("Santa", underTest.ref))
    }
    // #test
  }

  "A GreeterBot" must {
    "reply max 3 times to greet Santa - intermingle version" in {
      val replyProbe = createTestProbe[Greet]()
      val underTest = spawn(GreeterBot(3))

      underTest ! Greeted("Santa", replyProbe.ref)
      replyProbe.expectMessage(Greet("Santa", underTest.ref))

      underTest ! Greeted("Santa", replyProbe.ref)
      replyProbe.expectMessage(Greet("Santa", underTest.ref))

      underTest ! Greeted("Santa", replyProbe.ref)
      replyProbe.expectMessage(Greet("Santa", underTest.ref))

      underTest ! Greeted("Santa", replyProbe.ref)
      replyProbe.expectNoMessage()

      underTest ! Greeted("Santa", replyProbe.ref)
      replyProbe.expectNoMessage()

      underTest ! Greeted("Santa", replyProbe.ref)
      replyProbe.expectNoMessage()
    }
  }

  "A GreeterBot" must {
    "reply max 3 times to greet Santa - grouped version" in {
      val replyProbe = createTestProbe[Greet]()
      val underTest = spawn(GreeterBot(3))

      underTest ! Greeted("Santa", replyProbe.ref)
      underTest ! Greeted("Santa", replyProbe.ref)
      underTest ! Greeted("Santa", replyProbe.ref)
      underTest ! Greeted("Santa", replyProbe.ref)
      underTest ! Greeted("Santa", replyProbe.ref)
      underTest ! Greeted("Santa", replyProbe.ref)

      replyProbe.expectMessage(Greet("Santa", underTest.ref))
      replyProbe.expectMessage(Greet("Santa", underTest.ref))
      replyProbe.expectMessage(Greet("Santa", underTest.ref))
      replyProbe.expectNoMessage()
      replyProbe.expectNoMessage()
      replyProbe.expectNoMessage()
    }
  }

"A GreeterBot" must {
    "reply max 3 times to greet always new person - grouped version" in {
      val replyProbe = createTestProbe[Greet]()
      val underTest = spawn(GreeterBot(3))

      underTest ! Greeted("A", replyProbe.ref)
      underTest ! Greeted("B", replyProbe.ref)
      underTest ! Greeted("C", replyProbe.ref)
      underTest ! Greeted("D", replyProbe.ref)
      underTest ! Greeted("E", replyProbe.ref)
      underTest ! Greeted("F", replyProbe.ref)

      replyProbe.expectMessage(Greet("A", underTest.ref))
      replyProbe.expectMessage(Greet("B", underTest.ref))
      replyProbe.expectMessage(Greet("C", underTest.ref))
      replyProbe.expectNoMessage()
      replyProbe.expectNoMessage()
      replyProbe.expectNoMessage()
    }
  }

}
//#full-example
