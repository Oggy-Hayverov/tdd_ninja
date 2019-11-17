import videoRental.printer.IReceiptPrinter
import videoRental.repository.IMovieRepository
import videoRental.repository.Movie

class RentalTransactionManager(
    private val movieRepository: IMovieRepository,
    private val printManager: IReceiptPrinter
) {
    fun getAvailableMovies(): List<Movie> {
        return movieRepository.getAllMovies().filter { it.isReserved.not() }
    }

    fun reserveVideo(selectionId: Long): Boolean {
        /*todo New requirement for printing a receipt when the transaction is successful*/
        val videoById = movieRepository.getVideoById(selectionId)
        return when {
            videoById != null && videoById.isReserved.not() -> {
                movieRepository.updateMovie(videoById.apply { isReserved = true })
            }
            else -> false
        }
    }
}
