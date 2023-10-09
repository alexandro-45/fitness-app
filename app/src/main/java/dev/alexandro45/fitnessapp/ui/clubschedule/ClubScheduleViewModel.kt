package dev.alexandro45.fitnessapp.ui.clubschedule

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alexandro45.fitnessapp.data.ClubRepository
import dev.alexandro45.fitnessapp.data.model.Lesson
import dev.alexandro45.fitnessapp.data.model.Trainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ClubScheduleViewModel @Inject constructor(private val clubRepository: ClubRepository) :
    ViewModel() {

    private val _uiState = MutableLiveData<ClubScheduleUiState>()
    val uiState: LiveData<ClubScheduleUiState> get() = _uiState

    init {
        fetchClubSchedule()
    }

    fun fetchClubSchedule() {
        _uiState.value = ClubScheduleUiState(
            isLoading = true,
            isSuccess = false,
            list = emptyList(),
            errorMessage = ""
        )
        viewModelScope.launch(Dispatchers.IO) {
            val trainers: List<Trainer>
            val lessons: List<Lesson>

            try {
                val club = clubRepository.getClub()
                trainers = club.trainers
                lessons = club.lessons
            } catch (e: Exception) {
                _uiState.postValue(
                    ClubScheduleUiState(
                        isLoading = false,
                        isSuccess = false,
                        list = emptyList(),
                        errorMessage = e.message.toString()
                    )
                )
                return@launch
            }

            val trainersMap = HashMap<String, Trainer>(trainers.size)
            trainers.forEach { trainersMap[it.id] = it }

            val dateHeaderFormat = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
            val parser = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val sorted = lessons.map { Pair(parser.parse(it.date + " " + it.startTime)!!.time, it) }
                .sortedBy { it.first }

            val uiList = ArrayList<ClubScheduleListAdapter.AdapterItemModel>(sorted.size)

            var prevDate = ""
            for (lesson in sorted) {
                if (lesson.second.date != prevDate) {
                    prevDate = lesson.second.date
                    uiList.add(ClubScheduleListAdapter.DateItemModel(dateHeaderFormat.format(lesson.first)))
                }
                uiList.add(
                    ClubScheduleListAdapter.LessonItemModel(
                        Color.parseColor(lesson.second.color),
                        lesson.second.startTime,
                        lesson.second.endTime,
                        lesson.second.name,
                        trainersMap[lesson.second.coachId]?.fullName,
                        lesson.second.place
                    )
                )
            }
            _uiState.postValue(
                ClubScheduleUiState(
                    isLoading = false,
                    isSuccess = true,
                    list = uiList,
                    errorMessage = ""
                )
            )
        }
    }
}