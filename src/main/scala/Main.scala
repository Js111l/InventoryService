
import akka.actor.{ActorSystem, Scheduler}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.google.inject.Guice
import conf.registry.MainModule
import controller.InventoryController
import service.InventoryService
import tasks.TaskScheduler

import scala.concurrent.ExecutionContextExecutor

object Main extends App {
  // Create ActorSystem and Materializer
  implicit val system: ActorSystem = ActorSystem("main")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val injector = Guice.createInjector(new MainModule())

  val service = injector.getInstance(classOf[InventoryService])
  val routes = injector.getInstance(classOf[InventoryController]).routes

  val taskScheduler = injector.getInstance(classOf[TaskScheduler])
  taskScheduler.startScheduler(system)

  // Start the server by providing the ActorSystem
  Http().newServerAt("localhost", 9090).bind(routes)
}