package com.example.obstaclegame.logic

class GameManager(private val lanes: Int = 3) {
    var erenLane: Int = lanes / 2
        private set

    fun moveLeft() {
        if (erenLane > 0)
            erenLane--

    }

    fun moveRight() {
        if (erenLane < lanes - 1)
            erenLane++
    }

}

