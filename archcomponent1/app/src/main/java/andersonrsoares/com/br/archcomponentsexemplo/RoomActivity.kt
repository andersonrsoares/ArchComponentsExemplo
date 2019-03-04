package andersonrsoares.com.br.archcomponentsexemplo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.persistence.room.Room
import org.jetbrains.anko.doAsync
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer


class RoomActivity : AppCompatActivity() {

    lateinit var sampleDatabase: SampleDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)


        sampleDatabase =  SampleDatabase.create(applicationContext)

        //Let's add some dummy data to the database.
        val university = University()
        university.name = "MyUniversity"

        val college = College()
        college.id = 1
        college.name = "MyCollege"

        university.college = college

        //Now access all the methods defined in DaoAccess with sampleDatabase object
        doAsync {
            sampleDatabase.daoAccess().insertOnlySingleRecord(university)

            sampleDatabase.daoAccess().fetchAllData().forEach {
                print(it)
            }
        }

        val universityLiveData = sampleDatabase.daoAccess().fetchAllLiveData()
        universityLiveData.observe(this,Observer<List<University>> {
            it?.forEach {
                print(it)
            }
        })




    }
}
