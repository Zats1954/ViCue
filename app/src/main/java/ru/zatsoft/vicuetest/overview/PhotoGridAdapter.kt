package ru.zatsoft.vicuetest.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zatsoft.vicuetest.databinding.GridViewItemBinding
import ru.zatsoft.vicuetest.network.Klip

class PhotoGridAdapter(private val onItemClicked: (position: Int) -> Unit): ListAdapter<Klip,
        PhotoGridAdapter.PhotoViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(GridViewItemBinding.inflate(
                                    LayoutInflater.from(parent.context)),
                               onItemClicked )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val klipPhoto = getItem(position)
        holder.bind(klipPhoto)
    }

    class PhotoViewHolder (private var binding: GridViewItemBinding,
                           private val onItemClicked: (position: Int) -> Unit
    ) :RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(KlipPhoto: Klip) {
            binding.photo = KlipPhoto

            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            onItemClicked(position)
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
