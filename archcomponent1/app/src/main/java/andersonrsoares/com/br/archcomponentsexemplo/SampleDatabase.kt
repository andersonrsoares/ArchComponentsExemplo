package andersonrsoares.com.br.archcomponentsexemplo

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.content.Context


@Database(entities = arrayOf(University::class), version = 2)// a
abstract class SampleDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess

    companion object {
         private var  instance:SampleDatabase? = null
         fun create(context: Context):SampleDatabase{
             if(instance == null){
                 instance = Room.databaseBuilder(context,
                         SampleDatabase::class.java, "sample-db")
                         .fallbackToDestructiveMigration()//para funcionar a recriação da tabela a versão do banco precisa ser criada
                         .build()
             }
            return instance!!
        }
    }
}