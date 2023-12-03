package controller

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{complete, path}
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer

class ProductController {
  implicit val system: ActorSystem = ActorSystem("product-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  // http methods
  val route: Route = path("hello") {
    Directives.get {
      complete("hello world")
    }
  }
}
