package db

import slick.dbio.DBIO
import slick.driver.H2Driver.api._

import scala.concurrent.Future
import scala.util.{Failure, Success}

class DbCon() {
  def createCoffees: DBIO[Int] =
    sqlu"""create table coffees(
      name varchar not null,
      sup_id int not null,
      price double not null,
      sales int not null,
      total int not null,
      foreign key(sup_id) references suppliers(id))
      """

  val price = 23;

  val selectQuery =
    sql"""select c.name, s.name
          from coffees c, suppliers s
          where c.price < $price and s.id = c.sup_id""".as[(String, String)]


}