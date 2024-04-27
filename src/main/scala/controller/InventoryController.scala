package controller

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import akka.http.scaladsl.server.Directives.{complete, path, pathPrefix}
import service.InventoryService
import akka.http.scaladsl.server.Directives._
import scala.util.{Failure, Success}
import conf.json.MyJsonProtocol._
import spray.json._
import com.google.inject.Inject
import com.google.inject.Singleton

@Singleton
class InventoryController @Inject()( inventoryService: InventoryService) {
  implicit val jsonFormat: RootJsonFormat[model.Product] = jsonFormat3(model.Product.apply)
  implicit val system: ActorSystem = ActorSystem("inventory-system")
  //todo
  val routes: Route = pathPrefix("inventory") {
    path("/products/active") {
      Directives.get {
        onComplete(inventoryService.getActiveProducts()) {
          case Success(products) => complete(StatusCodes.OK, products.toJson.toString())
          case Failure(ex) => complete(ApiErrorHandler.getHttpEntity(ex))
        }
      }
    } ~ path("products" / IntNumber) { id =>
      get {
        complete(this.inventoryService.getProductStock(id))
      }
    }

    //    ~ path("products") {
    //      get {
    //        onComplete(inventoryService.getAllProducts()) {
    //          case Success(products) => complete(StatusCodes.OK, products.toJson.toString())
    //          case Failure(ex) => complete(StatusCodes.InternalServerError, s"An error occurred: ${ex.getMessage}")
    //        }
    //      }
    //    } ~ path("products/low-stock") {
    //      Directives.get {
    //        complete(inventoryService.getAllProductsWithLowStock())
    //      }
    //    } ~ path("products/out-of-stock") {
    //      Directives.get {
    //        complete(inventoryService.getOutOfStockProducts())
    //      }
    //    } ~ parameters("id".as[Int]) { id =>
    //      path("products/stock") {
    //        Directives.post {
    //          entity(as[String]) { requestBody =>
    //            complete(inventoryService.updateProductStock(id, requestBody))
    //          }
    //        }
    //      } ~ path("/metrics/average-stock") {
    //        Directives.get {
    //          complete(inventoryService.getAverageStock())
    //        }
    //      } ~ path("metrics/total-stock") {
    //        Directives.get {
    //          complete(inventoryService.getTotalStock())
    //        }
    //      } ~ path("products/top-selling") {
    //        Directives.get {
    //          complete(inventoryService.getTopSellingProducts())
    //        }
          } ~ path("products/category") {
            Directives.get {
              complete(inventoryService.getProductsByCategory())
            }
    //      }


    //       ~ parameters("id".as[Int]) { id =>
    //        path("products" / "min-stock-threshold") {
    //          Directives.put {
    //            entity(as[MinStockThresholdRequest]) { thresholdRequest =>
    //              complete(inventoryService.setMinStockThreshold(id, thresholdRequest))
    //            }
    //          }
    //        } ~ parameters("id".as[Int]) { id =>
    //          path("products" / "min-stock-threshold/") {
    //            Directives.get {
    //              complete(inventoryService.getMinStockThreshold(id))
    //            }
    //          }
    //        }
    //      } ~ path("products" / IntNumber / "stock-alerts") { productId =>
    //        Directives.post {
    //          entity(as[StockAlertSubscriptionRequest]) { alertSubscription =>
    //            complete(inventoryService.subscribeToStockAlert(productId, alertSubscription))
    //          }
    //        } ~
    //          Directives.delete {
    //            complete(inventoryService.unsubscribeFromStockAlert(productId))
    //          }
    //      }


  }

}