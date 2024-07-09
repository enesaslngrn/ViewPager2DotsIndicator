package com.example.viewpager2dotsindicator

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2dotsindicator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewPager2 Auto Swipe -> 1. Runnable - Handler

        val imageList = listOf(
            R.drawable.minion1,
            R.drawable.minion2,
            R.drawable.minion3,
            R.drawable.minion4,
            R.drawable.minion5,
            R.drawable.minion6,
            R.drawable.minion7,
            R.drawable.minion8,
            R.drawable.minion9,
            R.drawable.minion10,
            )
        adapter = ViewPagerAdapter(imageList)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        startAutoSwiping()
        binding.viewPager.registerOnPageChangeCallback(onPageChangeCallback)

    }

    private fun startAutoSwiping() {
        autoSwipeHandler.postDelayed(autoSwipeRunnable,3000)
    }
    private fun stopAutoSwiping() {
        autoSwipeHandler.removeCallbacks(autoSwipeRunnable)
    }

    private val autoSwipeHandler = Handler(Looper.getMainLooper())
    private val autoSwipeRunnable = object : Runnable{
        override fun run() {
            val currentItem = binding.viewPager.currentItem
            if (binding.viewPager.adapter != null){
                val nextItem = (currentItem + 1) % binding.viewPager.adapter!!.itemCount
                binding.viewPager.setCurrentItem(nextItem,true)
                autoSwipeHandler.postDelayed(this,3000)
            }
        }
    }
    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            if (state == ViewPager2.SCROLL_STATE_DRAGGING) stopAutoSwiping()

        }
    }

    // !!! Önemli. Lifecycle kontrolü

    override fun onDestroy() {
        stopAutoSwiping()
        Log.d("Main","Swipe Sonlandı...")
        super.onDestroy()
    }

    override fun onPause() {
        stopAutoSwiping()
        Log.d("Main","Swipe Sonlandı...")
        super.onPause()

    }

    override fun onResume() {
        startAutoSwiping()
        Log.d("Main","Swipe Başladı...")
        super.onResume()
    }
}