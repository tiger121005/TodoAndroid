package app.ito.yomi.todoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.ito.yomi.todoapp.databinding.ItemTodoDataCellBinding

class RecyclerViewAdapter(private val context: Context): ListAdapter<TodoData, TodoDataViewHolder> (diffCallback) {

    private  val _checkEvent = MutableLiveData(-1)
    val checkEvent: LiveData<Int> get() = _checkEvent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoDataViewHolder {
        val view = ItemTodoDataCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoDataViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.todoTextView.text = item.todo
        holder.binding.todoTextView2.text = item.memo
        holder.binding.checkBox.setOnClickListener {
            if (holder.binding.checkBox.isChecked) {
                _checkEvent.value = item.id
                holder.binding.checkBox.isChecked = false
                Toast.makeText(context, "削除しました！", Toast.LENGTH_LONG).show()
            }
        }
        holder.binding.constraintLayout.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java).apply {
                putExtra("ID", item.id)
            }
            context.startActivity(intent)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TodoData>() {
            override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData) =
                oldItem == newItem
        }
    }
}

class TodoDataViewHolder(val binding: ItemTodoDataCellBinding) : RecyclerView.ViewHolder(binding.root)