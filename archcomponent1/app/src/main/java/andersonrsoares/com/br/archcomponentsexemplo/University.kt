package andersonrsoares.com.br.archcomponentsexemplo

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class University(
        @PrimaryKey(autoGenerate = true) var  slNo:Int = 0,
        var name:String = "",
        @Embedded(prefix = "clg") var  college:College = College(),
        var endereco:String = ""
)

data class College(
        var  id:Int = 0,
        var  name:String = ""
)