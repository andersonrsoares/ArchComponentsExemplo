package andersonrsoares.com.br.archcomponentsexemplo

import android.arch.persistence.room.*


@Dao
interface DaoAccess {

    @Insert
    fun insertMultipleRecord(vararg universities: University)

    @Insert
    fun insertMultipleListRecord(universities: List<University>)

    @Insert
    fun insertOnlySingleRecord(university: University)

    @Query("SELECT * FROM University")
    fun fetchAllData(): List<University>

    @Query("SELECT * FROM University WHERE clgid =:college_id")
    fun getSingleRecord(college_id: Int): University

    @Update
    fun updateRecord(university: University)

    @Delete
    fun deleteRecord(university: University)

}