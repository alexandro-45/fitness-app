package dev.alexandro45.fitnessapp.ui.clubschedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.INVALID_TYPE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.alexandro45.fitnessapp.R

class ClubScheduleListAdapter :
    ListAdapter<ClubScheduleListAdapter.AdapterItemModel, ViewHolder>(object :
        DiffUtil.ItemCallback<AdapterItemModel>() {
        override fun areItemsTheSame(
            oldItem: AdapterItemModel, newItem: AdapterItemModel
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: AdapterItemModel, newItem: AdapterItemModel
        ): Boolean = oldItem.equals(newItem)
    }) {

    open class AdapterItemModel

    data class LessonItemModel(
        @ColorInt val color: Int,
        val startTime: String,
        val endTime: String,
        val lessonTitle: String,
        val trainerName: String?,//some lessons don't have trainer name
        val location: String
    ) : AdapterItemModel()

    data class DateItemModel(val date: String) : AdapterItemModel()

    class LessonViewHolder(viewRoot: View) : ViewHolder(viewRoot) {
        val color: View = viewRoot.findViewById(R.id.color)
        val startTime: TextView = viewRoot.findViewById(R.id.start_time)
        val endTime: TextView = viewRoot.findViewById(R.id.end_time)
        val lessonTitle: TextView = viewRoot.findViewById(R.id.lesson_title)
        val trainerName: TextView = viewRoot.findViewById(R.id.trainer_name)
        val location: TextView = viewRoot.findViewById(R.id.location)
    }

    class DateViewHolder(rootView: View) : ViewHolder(rootView) {
        val date: TextView = rootView as TextView
    }

    companion object {

        private const val ITEM_TYPE_DATE: Int = 0
        private const val ITEM_TYPE_LESSON: Int = 1
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is DateItemModel -> ITEM_TYPE_DATE
        is LessonItemModel -> ITEM_TYPE_LESSON
        else -> INVALID_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            ITEM_TYPE_DATE -> DateViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.date_item, parent, false)
            )

            ITEM_TYPE_LESSON -> LessonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false)
            )

            else -> throw Exception("Invalid view type. Did you forget to add a new one?")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is DateViewHolder -> holder.date.text = (getItem(position) as DateItemModel).date
            is LessonViewHolder -> {
                val item = getItem(position) as LessonItemModel
                holder.color.setBackgroundColor(item.color)
                holder.startTime.text = item.startTime
                holder.endTime.text = item.endTime
                holder.lessonTitle.text = item.lessonTitle
                holder.trainerName.text =
                    item.trainerName ?: holder.itemView.context.getString(R.string.unknown_trainer)
                holder.location.text = item.location
            }

            else -> throw Exception("Invalid ViewHolder. Did you forget to add a branch for it?")
        }
    }
}