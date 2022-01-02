package com.boki.park.defeatingrps.ui.game

import android.app.Application
import androidx.lifecycle.*
import com.boki.park.defeatingrps.repo.RecordRepo
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timer

class GameViewModel(val app: Application): AndroidViewModel(app) {

    companion object {
        const val PROGRESS_MAX = 20
        const val PERIOD_MAX = 100L
    }

    enum class State { INIT, READY, PROGRESS, RESULT }
    enum class RPS { ROCK, PAPER, SCISSORS, NOT_SELECTED }
    enum class Result { FAIL, DRAW, SUCCESS }

    val stage = MutableLiveData(1)
    val score = MutableLiveData(0)
    val bigText = MutableLiveData("")
    val progress = MutableLiveData(PROGRESS_MAX)

    val oppoRPS = MutableLiveData<RPS>()
    val myRPS = MutableLiveData<RPS>()

    val result = MutableLiveData<Result>()

    var period = PERIOD_MAX

    private var timer: Timer? = null

    private val bestScoreFromDb = liveData { emit(RecordRepo.getMyBestRecord()) }

    val bestScore = MediatorLiveData<Int>().apply {
        addSource(bestScoreFromDb) { bestScore ->
            val currentScore = score.value ?: 0
            value = if (bestScore > currentScore) bestScore else currentScore
        }
        addSource(score) { currentScore ->
            val bestScore = bestScoreFromDb.value ?: 0
            value = if (bestScore > currentScore) bestScore else currentScore
        }
    }

    private val stateFlow = MutableStateFlow(State.INIT).apply {
        viewModelScope.launch {
            collect { state ->
                when (state) {
                    State.READY -> {
                        progress.value = PROGRESS_MAX
                        myRPS.value = RPS.NOT_SELECTED

                        if (result.value == Result.SUCCESS) {
                            score.value = score.value?.plus(10)
                            period -= 1
                            stage.value = stage.value?.let { it + 1 } ?: 1
                        }

                        viewModelScope.launch {
                            bigText.value = "ROCK"
                            delay(500)
                            bigText.value = "PAPER"
                            delay(500)
                            bigText.value = "SCISSORS"
                            delay(500)
                            next()
                        }
                    }

                    State.PROGRESS -> {
                        oppoRPS.value = RPS.values()[Random().nextInt(3)]
                        timer = timer(period = period) {
                            progress.postValue((progress.value ?: 0) -1)
                            if (0 >= (progress.value ?: 1)) {
                                next()
                                cancel()
                            }
                        }
                    }

                    State.RESULT -> {
                        timer?.cancel()

                        result.value = when {
                            myRPS.value == null || myRPS.value == RPS.NOT_SELECTED ||
                                    myRPS.value == RPS.ROCK && oppoRPS.value == RPS.SCISSORS ||
                                    myRPS.value == RPS.PAPER && oppoRPS.value == RPS.ROCK ||
                                    myRPS.value == RPS.SCISSORS && oppoRPS.value == RPS.PAPER -> {
                                Result.FAIL
                            }
                            myRPS.value == oppoRPS.value
                                -> Result.DRAW
                            else -> Result.SUCCESS
                        }
                    }

                    else -> {}
                }
            }
        }
    }
    val state = stateFlow.asLiveData()

    fun next() {
        stateFlow.value = when(stateFlow.value) {
            State.INIT -> State.READY
            State.READY -> State.PROGRESS
            State.PROGRESS -> State.RESULT
            State.RESULT -> State.READY
        }
    }

    fun restart() {
        score.value = 0
        stage.value = 1
        period = PERIOD_MAX
        next()
    }

    fun setMyRPS(rps: RPS) {
        myRPS.value = rps
        next()
    }

    fun setRecord(nick: String) {
        score.value?.let { viewModelScope.launch { RecordRepo.addRecord(nick, it) } }
    }

    fun stop() {
        viewModelScope.cancel()
    }
}