import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import videoRental.printer.IReceiptPrinter
import videoRental.repository.IMovieRepository
import videoRental.repository.Movie
import java.math.BigDecimal

class RentalTransactionManagerTest {

    val repository = mock(IMovieRepository::class.java)
    val printManager = mock(IReceiptPrinter::class.java)
    val sut = RentalTransactionManager(repository, printManager)

    private val availableMovies = mutableListOf(
        Movie(1, "Die hard", BigDecimal(7)),
        Movie(3, "The Godfather", BigDecimal(10)),
        Movie(4, "Casablanca", BigDecimal(12)),
        Movie(5, "Rocky", BigDecimal(10))
    )
    private val allMovies = mutableListOf(
        Movie(1, "Die hard", BigDecimal(7)),
        Movie(2, "Star Wars: Episode V – The Empire Strikes Back", BigDecimal(7), true),
        Movie(3, "The Godfather", BigDecimal(10)),
        Movie(4, "Casablanca", BigDecimal(12)),
        Movie(5, "Rocky", BigDecimal(10))
    )

    @Test
    fun onlyGetMoviesAvailableToRent() {
        Mockito.`when`(repository.getAllMovies()).thenReturn(allMovies)
        assertEquals(availableMovies, sut.getAvailableMovies())
    }

    @Test
    fun testReserveMovieSuccess() {
        val movie = Movie(12, "Test headline", BigDecimal(3))
        Mockito.`when`(repository.getVideoById(anyLong())).thenReturn(movie)
        Mockito.`when`(repository.updateMovie(movie)).thenReturn(true)
        assertEquals(true, sut.reserveVideo(12L))
    }

    @Test
    fun testReserveMovieFailure() {
        val reservedMovie = Movie(12, "Test headline", BigDecimal(3), true)
        Mockito.`when`(repository.getVideoById(anyLong())).thenReturn(reservedMovie)
        assertEquals(false, sut.reserveVideo(1234))
    }

    @Test
    fun testTryingToReserveANonExistentMovie() {
        Mockito.`when`(repository.getVideoById(anyLong())).thenReturn(null)
        assertEquals(false, sut.reserveVideo(1234))
    }

    @Test
    fun givenVideoIsAvailableMakeSureWePrintReceipt() {
        //Arrange
        val reservedMovie = Movie(12, "Test headline", BigDecimal(3), false)
        Mockito.`when`(repository.getVideoById(anyLong())).thenReturn(reservedMovie)
        Mockito.`when`(repository.updateMovie(reservedMovie)).thenReturn(true)

        //Act
        sut.reserveVideo(123)

        //Assert
        verify(printManager).printReceipt(anyString(), anyString())
    }

}