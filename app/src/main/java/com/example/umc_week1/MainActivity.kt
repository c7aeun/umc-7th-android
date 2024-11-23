package com.example.umc_week1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.umc_week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(viewBinding.root)

        // 기본 화면 설정 (앱 시작 시 HomeFragment가 표시됨)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, HomeFragment()).commit()

        // 메뉴 항목 선택 시 프래그먼트 전환
        viewBinding.bottomNavigationView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_home -> selectedFragment = HomeFragment()
                R.id.navigation_writing -> selectedFragment = WritingFragment()
                R.id.navigation_calendar -> selectedFragment = CalendarFragment()
                R.id.navigation_user -> selectedFragment = UserFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, selectedFragment).commit()
            }
            true
        }
    }
}