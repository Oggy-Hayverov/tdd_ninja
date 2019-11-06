package videoRental

import org.junit.Test

import org.junit.Assert.*
import videoRental.repository.Movie
import videoRental.repository.MovieRepository
import java.math.BigDecimal

class MovieRepositoryTest {

    val sut = MovieRepository()

    @Test
    fun testGetAllMovies() {
        val expectedMovies = mutableListOf(
            Movie(1, "Die hard", BigDecimal(7)),
            Movie(2, "Star Wars: Episode V – The Empire Strikes Back", BigDecimal(7)),
            Movie(3, "The Godfather", BigDecimal(10)),
            Movie(4, "Casablanca", BigDecimal(12)),
            Movie(5, "Rocky", BigDecimal(10))
        )
        val actualMovies = sut.getAllMovies()
        assertEquals(expectedMovies, actualMovies)
    }

    @Test
    fun addNewMovieOnlyIfDoesNotExist() {
        val expectedMovies = mutableListOf(
            Movie(1, "Die hard", BigDecimal(7)),
            Movie(2, "Star Wars: Episode V – The Empire Strikes Back", BigDecimal(7)),
            Movie(3, "The Godfather", BigDecimal(10)),
            Movie(4, "Casablanca", BigDecimal(12)),
            Movie(5, "Rocky", BigDecimal(10)),
            Movie(6, "Rocky 2", BigDecimal(11))
        )

        sut.addNewMovie(Movie(6, "Rocky 2", BigDecimal(11)))
        assertEquals(expectedMovies, sut.getAllMovies())
    }

    @Test
    fun ifMovieExistsDoNotAddIt() {
        val expectedMovies = mutableListOf(
            Movie(1, "Die hard", BigDecimal(7)),
            Movie(2, "Star Wars: Episode V – The Empire Strikes Back", BigDecimal(7)),
            Movie(3, "The Godfather", BigDecimal(10)),
            Movie(4, "Casablanca", BigDecimal(12)),
            Movie(5, "Rocky", BigDecimal(10))
        )

        sut.addNewMovie(Movie(4, "Casablanca", BigDecimal(12)))
        assertEquals(expectedMovies, sut.getAllMovies())
    }

    @Test
    fun ifMovieExistsUpdateItAndReturnTrue() {
        val expectedMovies = mutableListOf(
            Movie(1, "Die hard", BigDecimal(7)),
            Movie(2, "Star Wars: Episode V – The Empire Strikes Back", BigDecimal(7)),
            Movie(3, "The Godfather", BigDecimal(10)),
            Movie(4, "Casablanca", BigDecimal(10)),
            Movie(5, "Rocky", BigDecimal(10))
        )
        assertTrue(sut.updateMovie(Movie(4, "Casablanca", BigDecimal(10))))
        assertEquals(expectedMovies, sut.getAllMovies())
    }

    @Test
    fun ifMovieDoesNotExistReturnFalse() {
        assertFalse(sut.updateMovie(Movie(10, "Casablanca", BigDecimal(10))))
    }

    @Test
    fun givenFindVideoByIdExistsReturnVideo() {
        val expectedMovie = Movie(1, "Die hard", BigDecimal(7))
        assertEquals(expectedMovie, sut.getVideoById(1))
    }
}