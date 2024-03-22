package db

import entity.{ProductEntity, Products}
import slick.lifted.TableQuery

import scala.concurrent.Future
import slick.jdbc.H2Profile.api._
import slick.jdbc.GetResult

import java.time.LocalDate
import scala.concurrent.ExecutionContext.Implicits.global
class ProductRepository() extends BaseRepository[ProductEntity] {
  private val products = TableQuery[Products]
  private val db = Database.forConfig("h2mem1")
  implicit val getProductEntityResult = GetResult(r => ProductEntity(r.nextLong(), r.nextLong(), r.nextInt(),
    r.nextString(),r.nextDate().toLocalDate,r.nextDate().toLocalDate))
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
//  def findActiveProducts(endDate:LocalDate): Future[List[ProductEntity]] = {
//    val query = sql"""
//    SELECT *
//    FROM products
//    WHERE start_date_of_sale < ${endDate}
//  """.as[ProductEntity]
//
//    db.run(query).map(_.toList)
//  }


  override def save(entity: ProductEntity): ProductEntity = ???

  override def saveAll(entities: List[ProductEntity]): Future[List[ProductEntity]] = ???

  override def delete(id: Long): Future[Boolean] = ???

  override def deleteAll(): Future[Boolean] = ???
}