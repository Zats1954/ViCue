package ru.zatsoft.vicuetest.overview

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.zatsoft.vicuetest.R
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
        binding.edText.visibility = View.GONE
        viewModel.photos.observe(viewLifecycleOwner){
            configureVideoView(viewModel.photos.value?.get(0)?.file_url)
        }

        val recyclerView: RecyclerView =   binding.photosGrid
        recyclerView.adapter = PhotoGridAdapter { position  -> onListItemClick(position)}

        binding.btnText.setOnClickListener{
            binding.edText.visibility = View.VISIBLE
        binding.edText.width = binding.frame1.width}
        return binding.root
    }

    private fun onListItemClick(position: Int) {
        fragmentBinding?.let {
            configureVideoView(viewModel.photos.value?.get(position)?.file_url)
        }
    }

    private fun configureVideoView(fileUrl: String?) {
        fragmentBinding?.let{
//            it.frame1.visibility = View.GONE
                             it.videoView.setVideoPath(fileUrl)
                             it.videoView.setOnPreparedListener {
                                     video -> video.isLooping = true
//                                     it.frame1.visibility = View.VISIBLE
//                                     it.progressView?.visibility = View.INVISIBLE
                                     video.start()}
        }
    }

}