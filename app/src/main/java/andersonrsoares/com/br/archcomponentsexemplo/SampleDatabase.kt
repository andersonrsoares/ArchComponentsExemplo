package andersonrsoares.com.br.archcomponentsexemplo

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.content.Context


@Database(entities = arrayOf(University::class), version = 1)
abstract class SampleDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess

    companion object {
         fun create(context: Context):SampleDatabase{
            val sampleDatabase = Room.databaseBuilder(context,
                    SampleDatabase::class.java, "sample-db").build()

            return sampleDatabase
        }
    }
}