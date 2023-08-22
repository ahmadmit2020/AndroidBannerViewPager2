package am.android.androidbannerviewpager2.adapter

import am.android.androidbannerviewpager2.R
import am.android.androidbannerviewpager2.databinding.ItemViewBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ImageViewPagerAdapter(private val imageDrawableList: List<Int>) :
    RecyclerView.Adapter<ImageViewPagerAdapter.ViewPagerViewHolder>() {

    private val newList: List<Int> =
        listOf(imageDrawableList.last()) + imageDrawableList + listOf(imageDrawableList.first())

    inner class ViewPagerViewHolder(val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(imageDrawable: Int) {
            binding.photo = imageDrawable
//            Picasso.get().load(imageDrawable).into(binding.image)
        }

    }

    override fun getItemCount(): Int = newList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        holder.setData(newList[position])
    }

}

@BindingAdapter("android:src")
fun bindImageView(view: ImageView, url: Int) {
    Picasso.get()
        .load(url)
        .fit().centerCrop()
        .into(view)
}