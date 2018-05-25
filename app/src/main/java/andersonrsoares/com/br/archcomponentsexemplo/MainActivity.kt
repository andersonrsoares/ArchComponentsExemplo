package andersonrsoares.com.br.archcomponentsexemplo

import andersonrsoares.com.br.archcomponentsexemplo.model.User
import andersonrsoares.com.br.archcomponentsexemplo.ui.MainViewModel
import android.arch.lifecycle.*
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var viewmodel:MainViewModel

    private val result = MediatorLiveData<List<User>>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE)

        setContentView(R.layout.activity_main)


        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        result.observeForever {
            it?.run {
                print(it)
            }
        }

        // Create the observer which updates the UI.
        val userObserver = Observer<User> {
            it?.run {
                helloworld.text = this.nome
            }
        }


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewmodel.user.observe(this, userObserver)


        viewmodel.user.value = User("id",nome = "teste")




        result.value = arrayListOf()
        val arrayUtils =  arrayListOf<User>()
        for (x in 1..5) {
            arrayUtils.add(User("id",nome = getSaltString()))
        }
        val usersFromDatabase = MutableLiveData<List<User>>()
        usersFromDatabase.value = arrayUtils
        result.addSource(usersFromDatabase, { newUserList ->
            result.setValue(newUserList)
        })

        val arrayUtils2 =  arrayListOf<User>()
        for (x in 1..5) {
            arrayUtils2.add(User("id",nome = getSaltString()))
        }
        val usersFromNetwork = MutableLiveData<List<User>>()
        usersFromNetwork.value = arrayUtils2
        result.addSource(usersFromNetwork, { newUserList ->
            result.setValue(newUserList)
        })


//        result.value = arrayListOf()
//        val handler = Handler()
//        val runnable = object : Runnable {
//            override fun run() {
//                handler.postDelayed(this, 5000)
//
//                val arrayUtils =  arrayListOf<User>()
//                for (x in 1..5) {
//                    arrayUtils.add(User("id",nome = getSaltString()))
//                }
//
//                val livedata = MutableLiveData<List<User>>()
//                //livedata.observe(this,userObserver)
//                livedata.value = arrayUtils
//
//                //result.removeSource(livedata)
//                result.value =  arrayUtils
//
//            }
//        }

//        val livedata = MutableLiveData<List<User>>()
//        result?.addSource(livedata,{
//            result.value = it
//            print(it)
//        })

       // handler.postDelayed(runnable, 5000)

    }

    private fun getSaltString(): String {
        val SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        val salt = StringBuilder()
        val rnd = Random()
        while (salt.length < 18) { // length of the random string.
            val index = (rnd.nextFloat() * SALTCHARS.length).toInt()
            salt.append(SALTCHARS[index])
        }
        return salt.toString()

    }
}
