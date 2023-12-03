package entity


import slick.jdbc.H2Profile.api._

import java.time.LocalDate

class Products(tag: Tag) extends Table[ProductEntity](tag, "products") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  private def currentStock = column[Long]("currentStock")

  private def available = column[Int]("available")

  def category = column[String]("category")

  def startDateOfSale = column[LocalDate]("startDateOfSale")

  def endDateOfSale = column[LocalDate]("endDateOfSale")

  def * = (id, currentStock, available, category, startDateOfSale, endDateOfSale) <> (ProductEntity.tupled, ProductEntity.unapply)
}