package app.ito.yomi.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.ito.yomi.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        db = AppDatabase.getInstance(this.applicationContext)
        adapter = RecyclerViewAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        adapter.checkEvent.observe(this) { id ->
            db.todoDataDao().deleteDataById(id)
            adapter.submitList(db.todoDataDao().getAll())
        }
    }

    override fun onResume() {
        super.onResume()

        adapter.submitList(db.todoDataDao().getAll())

        binding.addButton.setOnClickListener {
            val todoText = binding.editText.text.toString()
            val memoText = binding.editText2.text.toString()

            val todoData = TodoData(
                todo = todoText,
                memo = memoText
            )
            db.todoDataDao().insert(todoData)
            adapter.submitList(db.todoDataDao().getAll())
        }
    }
}