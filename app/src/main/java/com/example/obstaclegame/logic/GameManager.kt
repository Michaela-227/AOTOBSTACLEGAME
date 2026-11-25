package com.example.obstaclegame.logic

import kotlin.random.Random

class GameManager(private val lanes: Int = 3, private val rows: Int = 5) {
    var erenLane: Int = lanes / 2
        private set

    val titans: Array<BooleanArray> = Array(rows) { BooleanArray(lanes) }

    fun moveLeft() {
        if (erenLane > 0)
            erenLane--

    }

    fun moveRight() {
        if (erenLane < lanes - 1)
            erenLane++
    }

    fun moveTitansDown() {
        for (row in rows - 1 downTo 1) {
            titans[row] = titans[row - 1]
        }
        titans[0] = BooleanArray(lanes)
        titans[0][Random.nextInt(lanes)] = true
    }

}

