package dev.alexandro45.fitnessapp.ui.clubschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.alexandro45.fitnessapp.databinding.FragmentClubScheduleBinding

@AndroidEntryPoint
class ClubScheduleFragment : Fragment() {

    private var _binding: FragmentClubScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubScheduleBinding.inflate(inflater)
        val viewModel: ClubScheduleViewModel by viewModels()

        binding.clubScheduleList.layoutManager = LinearLayoutManager(context)
        binding.clubScheduleList.adapter = ClubScheduleListAdapter()

        binding.refreshButton.setOnClickListener { viewModel.fetchClubSchedule() }

        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.clubScheduleList.visibility = View.GONE
                binding.errorMessage.visibility = View.GONE
                binding.refreshButton.visibility = View.GONE
                return@observe
            } else {
                binding.progressBar.visibility = View.GONE
            }
            if (it.isSuccess) {
                binding.clubScheduleList.visibility = View.VISIBLE
                binding.errorMessage.visibility = View.GONE
                binding.refreshButton.visibility = View.GONE
                (binding.clubScheduleList.adapter as ClubScheduleListAdapter).submitList(it.list)
            } else {
                binding.clubScheduleList.visibility = View.GONE
                binding.errorMessage.visibility = View.VISIBLE
                binding.refreshButton.visibility = View.VISIBLE
                binding.errorMessage.text = it.errorMessage
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class ClubScheduleUiState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val list: List<ClubScheduleListAdapter.AdapterItemModel>,
    val errorMessage: String
)
