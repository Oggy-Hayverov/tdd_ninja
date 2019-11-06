import videoRental.repository.Movie
import videoRental.repository.MovieRepository
import java.util.*

private val rentalTransactionManager = RentalTransactionManager(MovieRepository())
private const val RED_VAL = "[31m"
private const val YELLOW_VAL = "[33m"
private const val CYAN_VAL = "[36m"
private const val ESCAPE = '\u001B'
private const val RESET = "$ESCAPE[0m"
private const val RED = "$ESCAPE$RED_VAL"
private const val YELLOW = "$ESCAPE$YELLOW_VAL"
private const val CYAN = "$ESCAPE$CYAN_VAL"

fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        greetCustomer()
        val availableMovies = rentalTransactionManager.getAvailableMovies()
        if (availableMovies.isNotEmpty()) {
            printAvailableMovies(availableMovies)
        } else {
            println("$CYAN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println("$CYAN!! No more movies to borrow :(((  !!!")
            println("$CYAN!!!!!!!  Bye  !!!!!!!!!!!!!!!!!!!!!!!")
            println("$CYAN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            return
        }
        val selectionId = scanner.nextLong()
        if (rentalTransactionManager.reserveVideo(selectionId)) {
            println("$YELLOW!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println("$YELLOW!!!  Enjoy the movie  !!!!!!")
            println("$YELLOW!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println(RESET)
        } else {
            println("$RED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println("$RED!! Transaction unsuccessful! Please try again!  !!!")
            println("$RED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println(RESET)
        }
    }
}

private fun greetCustomer() {
    println()
    println("=======================================")
    println("Welcome to our videoRental rental shop!")
    println("=======================================")
    println()
}

private fun printAvailableMovies(availableMovies: List<Movie>) {
    println("~~~ Please enter the number of the movie you wish to borrow ~~~")
    println()
    availableMovies.forEach {
        println("${it.id} ${it.name} - £${it.price}".prependIndent("    ")) }
    println()
    println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~")
}

