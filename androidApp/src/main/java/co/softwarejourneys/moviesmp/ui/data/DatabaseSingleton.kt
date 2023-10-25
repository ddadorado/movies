package co.softwarejourneys.moviesmp.ui.data
import androidx.room.Room

import android.content.Context

object DatabaseSingleton {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): AppDatabase {
        val database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "database-name"
        )
            .build()
        return database
    }
}
