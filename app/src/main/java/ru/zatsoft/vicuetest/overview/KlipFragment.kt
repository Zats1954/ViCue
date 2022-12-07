package ru.zatsoft.vicuetest.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import ru.zatsoft.vicuetest.databinding.FragmentOverviewBinding

class KlipFragment : Fragment() {

    private val viewModel: KlipViewModel by viewModels()
    private var fragmentBinding : FragmentOverviewBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater)
        fragmentBinding = binding
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.edText.isVisible = false
        viewModel.photos.observe(viewLifecycleOwner){
            configureVideoView(viewModel.photos.value?.get(0)?.file_url)
        }

        val recyclerView: RecyclerView =   binding.photosGrid
        recyclerView.adapter = PhotoGridAdapter { position  -> onListItemClick(position)}

        binding.btnText.setOnClickListener{binding.edText.isVisible = true}
        return binding.root
    }

    private fun onListItemClick(position: Int) {
        fragmentBinding?.let {
            configureVideoView(viewModel.photos.value?.get(position)?.file_url)
        }
    }

    private fun configureVideoView(fileUrl: String?) {
        fragmentBinding?.let{it.videoView.setVideoPath(fileUrl)
        it.videoView.start()}
    }
}