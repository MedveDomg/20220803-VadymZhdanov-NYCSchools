package com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.medvedomg.a20220803_vadymzhdanov_nycschools.databinding.ActivityMainBinding
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist.SchoolListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        viewModel.navigationActionLiveData.observe(this) {
            supportFragmentManager.commit {
                replace(binding.root.id, SchoolListFragment())
            }
        }
    }
}