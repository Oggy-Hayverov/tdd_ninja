package videoRental.repository

import java.math.BigDecimal

class MovieRepository : IMovieRepository {
    private val INVALID_INDEX = -1
    private val movies = mutableListOf(
        Movie(1, "Die hard", BigDecimal(7)),
        Movie(2, "Star Wars: Episode V – The Empire Strikes Back", BigDecimal(7)),
        Movie(3, "The Godfather", BigDecimal(10)),
        Movie(4, "Casablanca", BigDecimal(12)),
        Movie(5, "Rocky", BigDecimal(10))
    )

    override fun getAllMovies(): List<Movie> {
        return movies
    }

    override fun addNewMovie(movie: Movie) {
        if (findExistingMovieIndex(movie) == INVALID_INDEX) {
            movies.add(movie)
        }
    }

    override fun updateMovie(movie: Movie): Boolean {
        val index = findExistingMovieIndex(movie)
        return if (index != INVALID_INDEX) {
            movies[index] = movie
            true
        } else {
            false
        }

    }

    override fun getVideoById(selectionId: Long): Movie? {
        return movies.find { it.id == selectionId }
    }

    private fun findExistingMovieIndex(movie: Movie) = movies.indexOfFirst { it.id == movie.id }
}
