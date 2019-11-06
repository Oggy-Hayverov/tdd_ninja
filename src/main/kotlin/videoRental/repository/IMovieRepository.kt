package videoRental.repository

interface IMovieRepository {
    fun getAllMovies(): List<Movie>
    fun addNewMovie(movie: Movie)
    fun updateMovie(movie: Movie): Boolean
    fun getVideoById(selectionId: Long): Movie?
}