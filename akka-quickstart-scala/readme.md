This is and **quick start example** of the usage of Akka.
This example / tutorial is taken from https://developer.lightbend.com/guides/akka-quickstart-scala/index.html 

---
From 2023-08-29 (i.e. newer commits than d2555f0cc8d0b5c46ab22ed4067e7485eb5683a7) is this example enriched by the **IoT example** described here: https://doc.akka.io/docs/akka/current/typed/guide/tutorial.html

* The following diagram illustrates the **IoT example** application architecture. Since we are interested in the state of each sensor device, we will model devices as actors. The running application will create as many instances of device actors and device groups as necessary.
![Schema IoT Example application architecture](https://doc.akka.io/docs/akka/current/typed/guide/diagrams/arch_boxes_diagram.png "IoT Example application architecture")
* the same architecture diagram as a tree of actors
![Schema IoT Example application architecture as a tree of actors](https://doc.akka.io/docs/akka/current/typed/guide/diagrams/arch_tree_diagram.png "IoT Example application architecture as a tree of actors")