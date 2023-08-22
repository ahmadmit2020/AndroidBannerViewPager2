package am.android.androidbannerviewpager2

import am.android.androidbannerviewpager2.adapter.ImageViewPagerAdapter
import am.android.androidbannerviewpager2.databinding.ActivityMainBinding
import am.android.androidbannerviewpager2.transformer.DepthPageTransformer
import am.android.androidbannerviewpager2.transformer.SlideUpTransformer
import am.android.androidbannerviewpager2.transformer.SliderTransformer
import am.android.androidbannerviewpager2.transformer.ZoomOutPageTransformer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)


        //populating the list with some image urls
        val imageUrlList = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
        )

        //initializing the adapter
        imageViewPagerAdapter = ImageViewPagerAdapter(imageUrlList)

        setUpViewPager()

        onInfinitePageChangeCallback(imageUrlList.size + 2)

    }

    private fun setUpViewPager() {

//        binding.viewPager.setPageTransformer(DepthPageTransformer())
        binding.viewPager.offscreenPageLimit   = 4
        binding.viewPager.setPageTransformer(SliderTransformer(4))


        binding.viewPager.adapter = imageViewPagerAdapter

        //set the orientation of the viewpager using ViewPager2.orientation
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        //select any page you want as your starting page
        val currentPageIndex = 1
        binding.viewPager.currentItem = currentPageIndex



    }

    override fun onDestroy() {
        super.onDestroy()

        // unregistering the onPageChangedCallback
        binding.viewPager.unregisterOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {}
        )
    }

    private fun onInfinitePageChangeCallback(listSize: Int) {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (binding.viewPager.currentItem) {
                        listSize - 1 -> binding.viewPager.setCurrentItem(1, false)
                        0 -> binding.viewPager.setCurrentItem(listSize - 2, false)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position != 0 && position != listSize - 1) {
                    // pageIndicatorView.setSelected(position-1)
                }
            }
        })
    }
}