package app.ito.yomi.todoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDataDao {
    @Insert
    fun insert(todoData: TodoData)

    @Query("select * from todo_data")
    fun getAll(): List<TodoData>

    @Query("select * from todo_data where id = :id")
    fun getDataById(id: Int): TodoData

    @Update
    fun update(todoData: TodoData)

    @Query("delete from todo_data where id = :id")
    fun deleteDataById(id: Int)
}