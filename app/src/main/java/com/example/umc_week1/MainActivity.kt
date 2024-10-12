package com.example.umc_week1

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.umc_week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // BottomNavigationView와 프래그먼트 연결
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)

        // 기본 화면 설정 (앱 시작 시 HomeFragment가 표시됨)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()

        // 메뉴 항목 선택 시 프래그먼트 전환
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_home -> selectedFragment = HomeFragment()
                R.id.navigation_writing -> selectedFragment = WritingFragment()
                R.id.navigation_calendar -> selectedFragment = CalendarFragment()
                R.id.navigation_user -> selectedFragment = UserFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.container, selectedFragment).commit()
            }
            true
        }

    }
}