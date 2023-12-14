package com.kotlincourse.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.concurrent.fixedRateTimer


class MainActivity : AppCompatActivity() {


    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize your views
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)
        viewPagerAdapter =  ViewPagerAdapter(this);
        viewPager2.offscreenPageLimit = 5
        viewPager2.setAdapter(viewPagerAdapter);
        bottomNavigationView = findViewById(R.id.bottomNavBar);

        val tabSelectedListener = object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                tab?.let { viewPager2.setCurrentItem(it.getPosition()) };
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselected event
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselected event
            }
        }


        // Add the listener to the TabLayout
        tabLayout.addOnTabSelectedListener(tabSelectedListener)
        // Create a custom OnPageChangeCallback implementation
        val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select();
                // Handle page selected event
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Handle page scroll state changed event
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Handle page scrolled event
            }
        }

        // Register the callback with ViewPager2
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.recipesMenu -> viewPager2.currentItem = 0
                R.id.maelPlannerMenu -> viewPager2.currentItem = 1
                R.id.blogMenu -> viewPager2.currentItem = 2
                R.id.contactMenu -> viewPager2.currentItem = 3
                R.id.AboutMeMenu -> viewPager2.currentItem = 4
                // Add cases for other menu items as needed
            }
            true
        }


    }
}