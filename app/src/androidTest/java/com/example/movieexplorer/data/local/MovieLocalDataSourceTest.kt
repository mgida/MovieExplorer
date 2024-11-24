package com.example.movieexplorer.data.local

import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieLocalDataSourceTest {

    private lateinit var movieDatabase: MovieDatabase
    private lateinit var movieLocalDataSource: MovieLocalDataSource

    @Before
    fun setup() {
        movieDatabase = inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()

        movieLocalDataSource = movieDatabase.movieLocalDataSource
    }

    @After
    fun tearDown() {
        movieDatabase.close()
    }

    @Test
    fun saveMovieToWatchList() {
        runTest {
            val movieModel = createMovieDetailsModel(1)

            movieLocalDataSource.insertMovie(movie = movieModel)

            val movies = movieLocalDataSource.getWatchList().first()

            assertThat(movies).contains(movieModel)
        }
    }

    @Test
    fun removeMovieFromWatchList() {
        runTest {
            val movieModel = createMovieDetailsModel(id = 1)

            movieLocalDataSource.insertMovie(movie = movieModel)
            movieLocalDataSource.deleteMovie(movie = movieModel)

            val movies = movieLocalDataSource.getWatchList().first()

            assertThat(movies).doesNotContain(movieModel)
        }
    }

    @Test
    fun countOfSavedMoviesInWatchList() {
        runTest {
            val movies = listOf(
                createMovieDetailsModel(id = 4),
                createMovieDetailsModel(id = 5)
            )

            for (movie in movies) {
                movieLocalDataSource.insertMovie(movie)
            }

            val count = movieLocalDataSource.getWatchList().first().size
            assertThat(count).isEqualTo(movies.size)
        }
    }

    private fun createMovieDetailsModel(id: Int) = MovieDetailsModel(
        id = id,
        title = "Inception",
        overview = "overview",
        image = "image.jpg",
        releaseDate = "2010",
        status = "Released",
        tagLine = "tagLine",
        revenue = "revenue"
    )
}