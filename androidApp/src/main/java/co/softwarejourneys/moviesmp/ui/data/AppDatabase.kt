package co.softwarejourneys.moviesmp.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import co.softwarejourneys.moviesmp.MoviesDao
import co.softwarejourneys.moviesmp.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}
