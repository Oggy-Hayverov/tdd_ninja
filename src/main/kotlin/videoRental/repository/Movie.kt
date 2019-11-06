package videoRental.repository

import java.math.BigDecimal

data class Movie(val id: Long, val name: String, val price: BigDecimal, var isReserved: Boolean = false)