package andersonrsoares.com.br.archcomponentsexemplo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.persistence.room.Room



class RoomActivity : AppCompatActivity() {

    lateinit var sampleDatabase: SampleDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)


        sampleDatabase =  SampleDatabase.create(this)

        //Let's add some dummy data to the database.
        val university = University()
        university.name = "MyUniversity"

        val college = College()
        college.id = 1
        college.name = "MyCollege"

        university.college = college

        //Now access all the methods defined in DaoAccess with sampleDatabase object
        sampleDatabase.daoAccess().insertOnlySingleRecord(university)



    }
}
