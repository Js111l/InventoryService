package entity

import java.time.LocalDate

case class ProductEntity(id: Long,
                         currentStock: Long,
                         available: Int,
                         category: String,
                         startDateOfSale: LocalDate,
                         endDateOfSale: LocalDate) {

}
