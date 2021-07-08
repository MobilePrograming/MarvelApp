package com.guessaname.marvelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.guessaname.marvelapp.data.repository.CharactersRepository
import com.guessaname.marvelapp.data.repository.ComicsRepository
import com.guessaname.marvelapp.data.repository.CreatorsRepository

import com.guessaname.marvelapp.databinding.ActivityMainBinding
import com.guessaname.marvelapp.ui.viewmodel.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var creatorsViewModel: CreatorsViewModel
    lateinit var charactersViewModel: CharactersViewModel
    lateinit var comicsViewModel: ComicsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.bookMarkFragment, R.id.characterFragment, R.id.creatorsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

        val creatorsRepository = CreatorsRepository()
        val creatorsViewModelProviderFactory = CreatorsViewModelFactory(creatorsRepository)
        creatorsViewModel = ViewModelProvider(this, creatorsViewModelProviderFactory).get(CreatorsViewModel::class.java)

        val characterRepository = CharactersRepository()
        val characterViewModelProviderFactory = CharactersViewModelFactory(characterRepository)
        charactersViewModel = ViewModelProvider(this, characterViewModelProviderFactory).get(CharactersViewModel::class.java)

        val comicsRepository = ComicsRepository()
        val comicsViewModelFactory = ComicsViewModelFactory(comicsRepository)
        comicsViewModel = ViewModelProvider(this, comicsViewModelFactory).get(ComicsViewModel::class.java)


    }
}