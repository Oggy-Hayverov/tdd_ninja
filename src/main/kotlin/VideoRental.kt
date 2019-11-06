import videoRental.repository.Movie
import videoRental.repository.MovieRepository
import java.util.*

private val rentalTransactionManager = RentalTransactionManager(MovieRepository())


fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        greetCustomer()
        val availableMovies = rentalTransactionManager.getAvailableMovies()
        if (availableMovies.isNotEmpty()) {
            printAvailableMovies(availableMovies)
        } else {
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println("!! No more movies to borrow :(((  !!!")
            println("!!!!!!!  Bye  !!!!!!!!!!!!!!!!!!!!!!!")
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            return
        }
        val selectionId = scanner.nextLong()
        if (rentalTransactionManager.reserveVideo(selectionId)) {
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println("!!!  Enjoy the movie  !!!!!!")
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        } else {
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println("!! Transaction unsuccessful! Please try again!  !!!")
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        }
    }
}

private fun greetCustomer() {
    println()
    println()
    println("=======================================")
    println("Welcome to our videoRental rental shop!")
    println("=======================================")
}

private fun printAvailableMovies(availableMovies: List<Movie>) {
    println("~~~Please enter the number of the movie you wish to borrow~~~")
    println()
    availableMovies.forEach { println("${it.id} ${it.name} - £${it.price}") }
    println()
    println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~")
}

