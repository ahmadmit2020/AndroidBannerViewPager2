package am.android.androidbannerviewpager2.transformer

import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import java.lang.Math.abs

class SliderTransformer(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {

    companion object {

        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR_X = 10f
        private const val DEFAULT_TRANSLATION_FACTOR_Y = 10f
        private const val SCALE_FACTOR = .14f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .3f
        private const val DEFAULT_ALPHA = 1f

    }

    override fun transformPage(page: View, position: Float) {

        page.apply {

            ViewCompat.setElevation(page, -kotlin.math.abs(position))

            val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
            val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA

            when {
                position <= 0f -> {
                    translationX = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA + position
                }
                position <= offscreenPageLimit - 1 -> {
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    translationY = -(height / DEFAULT_TRANSLATION_FACTOR_Y) * position
                    translationX= -(width / DEFAULT_TRANSLATION_FACTOR_X) * position
                    alpha = alphaFactor
                }
                else -> {
                    translationY = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA
                }
            }
        }
    }
}