package com.example.a3track.tasks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3track.R
import com.example.a3track.model.Task
import java.text.SimpleDateFormat

@Suppress("DEPRECATION")
class TaskAdapter(private val tasksList:MutableList<Task>, private val listener:  OnItemClickListener): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private val task1: Task = Task(679,"Elso feladat", "Lorem ipsum dolor sit amet consectetur adipis", 1675522301375, 9, 9, 2,1625942327,2, 1, 1)

  private val TasksList:MutableList<Task> = mutableListOf(task1)





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder{
        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.task_cards, parent, false)
        Log.i("taskSize", tasksList.size.toString())
        Log.i("task1", tasksList.toString())
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int){
        val currentItem = tasksList[position]
        val dateformat2 = SimpleDateFormat("MMMM dd yyyy")
        holder.taskTitle.text = currentItem.title
        holder.groupName.text = "csoportnev*"
        holder.assignedTime.text = "Alma"
//        holder.assignedTime.text = currentItem.created_time.toString().substring(11,16)
        holder.assigneeName.text = currentItem.asigned_to_user_ID.toString()
        holder.assignedBy.text = currentItem.created_by_user_ID.toString()
        holder.deadlineDate.text = dateformat2.format(currentItem.deadline)

        when(currentItem.status){
            0 ->{
                holder.newTaskStatus.visibility = View.VISIBLE
                holder.newTaskStatusText.visibility = View.VISIBLE
                holder.doneStatus.visibility = View.GONE
                holder.doneStatusText.visibility = View.GONE
                holder.inProgressTaskStatus.visibility = View.GONE
                holder.inProgressTaskStatusText.visibility = View.GONE
                holder.blockedStatus.visibility = View.GONE
                holder.blockedStatusText.visibility = View.GONE
            }

            1 ->{
                holder.newTaskStatus.visibility = View.GONE
                holder.newTaskStatusText.visibility = View.GONE
                holder.doneStatus.visibility = View.GONE
                holder.doneStatusText.visibility = View.GONE
                holder.inProgressTaskStatus.visibility = View.VISIBLE
                holder.inProgressTaskStatusText.visibility = View.VISIBLE
                holder.blockedStatus.visibility = View.GONE
                holder.blockedStatusText.visibility = View.GONE
            }

            2 ->{
                holder.newTaskStatus.visibility = View.GONE
                holder.newTaskStatusText.visibility = View.GONE
                holder.doneStatus.visibility = View.VISIBLE
                holder.doneStatusText.visibility = View.VISIBLE
                holder.inProgressTaskStatus.visibility = View.GONE
                holder.inProgressTaskStatusText.visibility = View.GONE
                holder.blockedStatus.visibility = View.GONE
                holder.blockedStatusText.visibility = View.GONE
            }

            3 ->{
                holder.newTaskStatus.visibility = View.GONE
                holder.newTaskStatusText.visibility = View.GONE
                holder.doneStatus.visibility = View.GONE
                holder.doneStatusText.visibility = View.GONE
                holder.inProgressTaskStatus.visibility = View.GONE
                holder.inProgressTaskStatusText.visibility = View.GONE
                holder.blockedStatus.visibility = View.VISIBLE
                holder.blockedStatusText.visibility = View.VISIBLE
            }
        }

        when(currentItem.priority){
            1->{
                holder.lowPrioIcon.visibility = View.VISIBLE
                holder.mediumPrioIcon.visibility = View.GONE
                holder.highPrioIcon.visibility = View.GONE
                holder.priorityText.text = "Low prio"
            }

            2->{
                holder.lowPrioIcon.visibility = View.GONE
                holder.mediumPrioIcon.visibility = View.VISIBLE
                holder.highPrioIcon.visibility = View.GONE
                holder.priorityText.text = "Medium prio"
            }

            3->{
                holder.lowPrioIcon.visibility = View.GONE
                holder.mediumPrioIcon.visibility = View.GONE
                holder.highPrioIcon.visibility = View.VISIBLE
                holder.priorityText.text = "High prio"
            }
        }
        var tempText = currentItem.description
        if(tempText.length > 75){
            tempText = tempText.substring(0,75)
        }
        holder.descriptionText.text = tempText
        holder.progressBar.progress = currentItem.progress
        holder.percentDone.text = "${currentItem.progress}% Done"

    }
    interface OnItemClickListener {
        fun onItemClick(position: Int,taskId: Int)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val taskTitle : TextView = itemView.findViewById(R.id.taskTitleDet)
        val groupName : TextView = itemView.findViewById(R.id.groupName)
        val assignedBy : TextView = itemView.findViewById(R.id.assignedBy)
        val assignedTime : TextView = itemView.findViewById(R.id.assignedTime)
        val assigneeName : TextView = itemView.findViewById(R.id.assigneeName)
        val deadlineDate : TextView = itemView.findViewById(R.id.deadlineDate)
        val priorityText : TextView = itemView.findViewById(R.id.priorityText)
        val highPrioIcon : ImageView = itemView.findViewById(R.id.highPrioIcon)
        val mediumPrioIcon : ImageView = itemView.findViewById(R.id.mediumPrioIcon)
        val lowPrioIcon : ImageView = itemView.findViewById(R.id.lowPrioIcon)
        val descriptionText : TextView = itemView.findViewById(R.id.descriptionText)
        val percentDone : TextView = itemView.findViewById(R.id.percentDoneText)
        val progressBar : ProgressBar = itemView.findViewById(R.id.progressBar)
        val newTaskStatus : ImageView = itemView.findViewById(R.id.newTaskStatus)
        val newTaskStatusText : TextView = itemView.findViewById(R.id.newTaskStatusText)
        val inProgressTaskStatus : ImageView = itemView.findViewById(R.id.inProgressTaskStatus)
        val inProgressTaskStatusText : TextView = itemView.findViewById(R.id.inProgressTaskStatusText)
        val doneStatus : ImageView = itemView.findViewById(R.id.doneStatus)
        val doneStatusText : TextView = itemView.findViewById(R.id.doneStatusText)
        val blockedStatus : ImageView = itemView.findViewById(R.id.blockedStatus)
        val blockedStatusText : TextView = itemView.findViewById(R.id.blockedStatusText)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position,tasksList[position].ID.toInt())
                }
            }
        }

    }


}