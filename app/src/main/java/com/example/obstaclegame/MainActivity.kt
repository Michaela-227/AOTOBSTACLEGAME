package com.example.obstaclegame

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.obstaclegame.logic.GameManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var gameManager: GameManager

    private lateinit var main_FAB_left: FloatingActionButton
    private lateinit var main_FAB_right: FloatingActionButton

    private lateinit var main_IMG_eren: Array<AppCompatImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViews()
        gameManager = GameManager(main_IMG_eren.size)
        initViews()
    }

    private fun findViews() {
        main_FAB_left = findViewById(R.id.main_FAB_left)
        main_FAB_right = findViewById(R.id.main_FAB_right)
        main_IMG_eren = arrayOf(
            findViewById(R.id.main_IMG_eren_0),
            findViewById(R.id.main_IMG_eren_1),
            findViewById(R.id.main_IMG_eren_2)
        )
    }

    private fun initViews() {
        main_FAB_left.setOnClickListener {
            gameManager.moveLeft()
            refreshUI()
        }
        main_FAB_right.setOnClickListener {
            gameManager.moveRight()
            refreshUI()
        }
        refreshUI()
    }

    private fun refreshUI() {
        main_IMG_eren.forEachIndexed { index, eren ->
            eren.visibility =
                if (index == gameManager.erenLane) View.VISIBLE
                else View.INVISIBLE
        }
    }

}