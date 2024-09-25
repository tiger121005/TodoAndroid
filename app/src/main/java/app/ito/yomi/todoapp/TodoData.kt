package app.ito.yomi.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_data")
data class TodoData(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "todo")
    var todo: String,

    @ColumnInfo(name = "memo")
    var memo: String,
)
