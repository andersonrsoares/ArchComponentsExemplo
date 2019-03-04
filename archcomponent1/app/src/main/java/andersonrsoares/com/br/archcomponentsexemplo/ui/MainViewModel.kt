package andersonrsoares.com.br.archcomponentsexemplo.ui

import andersonrsoares.com.br.archcomponentsexemplo.model.User
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var user = MutableLiveData<User>()


}