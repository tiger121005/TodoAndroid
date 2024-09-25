package app.ito.yomi.todoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.ito.yomi.todoapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        db = AppDatabase.getInstance(this.applicationContext)

        val id: Int = intent.getIntExtra("ID", 0)

        val tappedTodo = db.todoDataDao().getDataById(id)

        binding.titleEditText.setText(tappedTodo.todo)
        binding.memoEditText.setText(tappedTodo.memo)

        binding.editButton.setOnClickListener {
            val newTitle = binding.titleEditText.text.toString()
            val newMemo = binding.memoEditText.text.toString()
            tappedTodo.todo = newTitle
            tappedTodo.memo = newMemo
            db.todoDataDao().update(tappedTodo)
            finish()
        }
    }
}