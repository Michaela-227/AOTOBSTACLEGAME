package com.example.obstaclegame

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

    private lateinit var main_IMG_titans: Array<Array<AppCompatImageView>>

    private val handler: Handler = Handler(Looper.getMainLooper())

    private val gameLoop: Runnable = object : Runnable {
        override fun run() {
            gameManager.moveTitansDown()
            refreshUI()
            handler.postDelayed(this, 1_000L)
        }
    }

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
        gameManager = GameManager(main_IMG_eren.size, main_IMG_titans.size)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(gameLoop, 1_000L)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(gameLoop)
    }

    private fun findViews() {
        main_FAB_left = findViewById(R.id.main_FAB_left)
        main_FAB_right = findViewById(R.id.main_FAB_right)
        main_IMG_eren = arrayOf(
            findViewById(R.id.main_IMG_eren_0),
            findViewById(R.id.main_IMG_eren_1),
            findViewById(R.id.main_IMG_eren_2)
        )
        main_IMG_titans = arrayOf(
            arrayOf(
                findViewById(R.id.main_IMG_titan_0_0),
                findViewById(R.id.main_IMG_titan_0_1),
                findViewById(R.id.main_IMG_titan_0_2)
            ),
            arrayOf(
                findViewById(R.id.main_IMG_titan_1_0),
                findViewById(R.id.main_IMG_titan_1_1),
                findViewById(R.id.main_IMG_titan_1_2)
            ),
            arrayOf(
                findViewById(R.id.main_IMG_titan_2_0),
                findViewById(R.id.main_IMG_titan_2_1),
                findViewById(R.id.main_IMG_titan_2_2)
            ),
            arrayOf(
                findViewById(R.id.main_IMG_titan_3_0),
                findViewById(R.id.main_IMG_titan_3_1),
                findViewById(R.id.main_IMG_titan_3_2)
            ),
            arrayOf(
                findViewById(R.id.main_IMG_titan_4_0),
                findViewById(R.id.main_IMG_titan_4_1),
                findViewById(R.id.main_IMG_titan_4_2)
            )
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
        main_IMG_titans.forEachIndexed { row, rowImages ->
            rowImages.forEachIndexed { lane, titan ->
                titan.visibility =
                    if (gameManager.titans[row][lane]) View.VISIBLE
                    else View.INVISIBLE
            }
        }

    }

}