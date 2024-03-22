package conf.registry
import com.google.inject.AbstractModule
import akka.actor.ActorSystem
import controller.InventoryController
import db.ProductRepository
import net.codingwell.scalaguice.ScalaModule
import service.InventoryService
import tasks.TaskScheduler
import com.google.inject.{AbstractModule, Singleton}

//CONFIGURE DEPENDENCIES
class MainModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[ActorSystem].toInstance(ActorSystem("main"))
    bind[ProductRepository].in[Singleton]
    bind[InventoryService].in[Singleton]
    bind[InventoryController].in[Singleton]
    bind[TaskScheduler].in[Singleton]
  }
}
