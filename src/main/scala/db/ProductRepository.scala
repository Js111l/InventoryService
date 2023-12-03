package db

import entity.{ProductEntity, Products}
import slick.lifted.TableQuery

import scala.concurrent.Future
import slick.jdbc.H2Profile.api._

import java.time.LocalDate
import scala.concurrent.ExecutionContext.Implicits.global

class ProductRepository() extends BaseRepository[ProductEntity] {
  private val products = TableQuery[Products]
  private val db = Database.forConfig("h2mem1")

  def initSchema(): Future[Unit] = {
    db.run(products.schema.create)
  }

  override def findAll(): Future[List[ProductEntity]] = {
    db.run(products.result).map(_.toList)
  }

  override def findById(id: Long): Future[Option[ProductEntity]] = {
    db.run(products.filter(_.id === id).result.headOption)
  }

  def findActiveProducts(): Future[List[ProductEntity]] = {
    db.run(products.filter(_.startDateOfSale < LocalDate.now()).result).map(_.toList)
  }

  override def save(entity: ProductEntity): ProductEntity = ???

  override def saveAll(entities: List[ProductEntity]): Future[List[ProductEntity]] = ???

  override def delete(id: Long): Future[Boolean] = ???

  override def deleteAll(): Future[Boolean] = ???
}