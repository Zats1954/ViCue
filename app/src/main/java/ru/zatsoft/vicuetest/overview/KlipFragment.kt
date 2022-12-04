package ru.zatsoft.vicuetest.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import ru.zatsoft.vicuetest.bindImage
import ru.zatsoft.vicuetest.databinding.FragmentOverviewBinding

class KlipFragment : Fragment() {

    private val viewModel: KlipViewModel by viewModels()
    private var fragmentBinding : FragmentOverviewBinding? = null

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the KlipFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater)
        fragmentBinding = binding
//        val binding = GridViewItemBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the KlipViewModel
        binding.viewModel = viewModel
        viewModel.photos.observe(viewLifecycleOwner){
        bindImage(binding.imageView, viewModel.photos.value?.get(0)?.poster_url)}

        binding.photosGrid.adapter = PhotoGridAdapter { position  -> onListItemClick(position)}
        return binding.root
    }

    private fun onListItemClick(position: Int) {
        fragmentBinding?.let {
        bindImage(it.imageView, viewModel.photos.value?.get(position)?.poster_url)}
    }
}