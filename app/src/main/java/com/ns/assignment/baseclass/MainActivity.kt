package com.ns.assignment.baseclass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.commit
import com.ns.assignment.R
import com.ns.assignment.databinding.ActivityMainBinding
import com.ns.assignment.fragments.ListFragment
import com.ns.assignment.fragments.StaticBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setSupportActionBar(_binding.toolbar)

        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, ListFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
        supportFragmentManager.executePendingTransactions()

        _binding.contentMain.composeView.setContent {
            MaterialTheme {
                StaticBottomSheet()
            }
        }
    }
}
