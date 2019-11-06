import videoRental.repository.Movie
import videoRental.repository.MovieRepositoryImpl
import java.util.*

private const val ESCAPE = '\u001B'
private const val RESET = "$ESCAPE[0m"
private const val RED = "$ESCAPE[31m"
private const val YELLOW = "$ESCAPE[33m"
private const val CYAN = "$ESCAPE[36m"
private const val PURPLE = "$ESCAPE[35m"

private val rentalTransactionManager = RentalTransactionManager(MovieRepositoryImpl())
private val scanner = Scanner(System.`in`)

fun main() {
    startVideoRenting()
    return
}

private fun startVideoRenting() {
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
    println("   Welcome to our video rental shop!")
    println("=======================================")
    println()
}

private fun printAvailableMovies(availableMovies: List<Movie>) {
    println("~~~ Please enter the number of the movie you wish to borrow ~~~")
    println()
    availableMovies.forEach {
        println("$PURPLE${it.id} ${it.name} - £${it.price}".prependIndent("    "))
    }
    println(RESET)
    println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~")
}

