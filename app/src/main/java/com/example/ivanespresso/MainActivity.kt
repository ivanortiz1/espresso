package com.example.ivanespresso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var taskList: MutableList<Task>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskList = mutableListOf()
        recyclerView = findViewById(R.id.recyclerView)
        adapter = TaskAdapter(taskList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val addButton: Button = findViewById(R.id.addButton)
        val taskInput: EditText = findViewById(R.id.taskInput)

        addButton.setOnClickListener {
            val taskName = taskInput.text.toString()
            if (taskName.isNotBlank()) {
                val task = Task(taskName)
                taskList.add(task)
                adapter.notifyDataSetChanged()
                taskInput.text.clear()
            }
        }
    }

    inner class TaskAdapter(private val taskList: MutableList<Task>) :
        RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
            return TaskViewHolder(view)
        }

        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            val task = taskList[position]
            holder.bind(task)
        }

        override fun getItemCount(): Int {
            return taskList.size
        }

        inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val taskNameTextView: TextView = itemView.findViewById(R.id.taskNameTextView)
            private val taskCheckBox: CheckBox = itemView.findViewById(R.id.taskCheckBox)
            private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

            fun bind(task: Task) {
                taskNameTextView.text = task.name
                taskCheckBox.isChecked = task.completed

                taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
                    task.completed = isChecked
                }

                deleteButton.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        taskList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                }

                itemView.setOnClickListener {
                    task.completed = !task.completed
                    notifyDataSetChanged()
                }
            }
        }
    }
}

data class Task(val name: String, var completed: Boolean = false)