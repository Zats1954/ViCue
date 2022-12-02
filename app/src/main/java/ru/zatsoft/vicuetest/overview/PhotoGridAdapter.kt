package ru.zatsoft.vicuetest.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zatsoft.vicuetest.databinding.GridViewItemBinding
import ru.zatsoft.vicuetest.network.Klip

class PhotoGridAdapter: ListAdapter<Klip,
        PhotoGridAdapter.PhotoViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val klipPhoto = getItem(position)
        holder.bind(klipPhoto)
    }
    class PhotoViewHolder(private var binding:
                          GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(KlipPhoto: Klip) {
            binding.photo = KlipPhoto
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Klip>() {
        override fun areItemsTheSame(oldItem: Klip, newItem: Klip): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Klip, newItem: Klip): Boolean {
            return oldItem.poster_url == newItem.poster_url
        }
    }
}
