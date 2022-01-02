package com.boki.park.defeatingrps.ui.ranking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.boki.park.defeatingrps.repo.RecordRepo

class RankingViewModel(val app: Application): AndroidViewModel(app) {

    val recordList = liveData { emit(RecordRepo.getRecords()) }
}