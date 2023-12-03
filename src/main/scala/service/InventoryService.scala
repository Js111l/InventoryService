package service

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import db.ProductRepository
import entity.ProductEntity

import scala.concurrent.Future

class InventoryService(productRepository: ProductRepository) {

  def getAllProducts(): Future[List[ProductEntity]] = {
    this.productRepository.findAll()
  }
  def getActiveProducts(): Future[List[ProductEntity]] = {
    this.productRepository.findActiveProducts()
  }

  def getMinStockThreshold(id: Int): ToResponseMarshallable = ???

  def getProductsByCategory(): ToResponseMarshallable = ???

  def updateProductStock(id: Int, requestBody: String): ToResponseMarshallable = ???


  def getOutOfStockProducts(): ToResponseMarshallable = ???

  def getTopSellingProducts(): ToResponseMarshallable = ???

  def getTotalStock(): ToResponseMarshallable = ???

  def getAverageStock(): ToResponseMarshallable = ???

  def getAllProductsWithLowStock(): ToResponseMarshallable = ???

  def getProductStock(id: Int): _root_.akka.http.scaladsl.marshalling.ToResponseMarshallable = ???


}
