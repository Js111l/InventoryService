package tasks

import akka.actor.ActorSystem

import scala.concurrent.ExecutionContext

class ProductAlertTask {
  implicit val system: ActorSystem = ActorSystem("ProductAlertSystem")
  implicit val ec: ExecutionContext = system.dispatcher

//
//  system.scheduler.scheduleAtFixedRate(
//    initialDelay = 0.seconds,
//    interval = 1.hour
//  ) {
//
//    checkProductLevelsAndSendAlerts()
//  }
//
//
//  sys.addShutdownHook {
//    system.terminate()
//  }
//
//
//  def checkProductLevelsAndSendAlerts(): Unit = {
//
//
//    val productId = 123
//    val currentStock = getProductStock(productId)
//
//    if (currentStock < 10) {
//      sendAlert(s"Alert: Product $productId is running low on stock. Current stock: $currentStock")
//    }
//  }
//
//
//  def getProductStock(productId: Long): Int = {
//    // Your implementation to retrieve product stock
//    // ...
//
//    // Example: Return a static value for demonstration purposes
//    8
//  }
//
//
//  def sendAlert(message: String): Unit = {
//
//    println(s"Alert Sent: $message")
//  }
}
