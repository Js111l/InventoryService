package tasks

import akka.actor.{ActorSystem, Props}
import db.ProductRepository

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import com.google.inject.Inject
import com.google.inject.Singleton

@Singleton
class TaskScheduler @Inject()(productRepository: ProductRepository) {

  def startScheduler(system: ActorSystem): Unit = {
    val scheduledActor = system.actorOf(Props[TaskActor], "myScheduledActor")

    system.scheduler.scheduleWithFixedDelay(
      initialDelay = 0.millisecond,
      delay = 100.millisecond,
      receiver = scheduledActor,
      message = "productAlertTask"
    )

  }
}
