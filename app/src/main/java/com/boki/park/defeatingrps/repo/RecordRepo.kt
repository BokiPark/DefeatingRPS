package com.boki.park.defeatingrps.repo

import android.content.Context
import com.boki.park.defeatingrps.network.RetrofitClient
import com.boki.park.defeatingrps.network.data.Record
import com.boki.park.defeatingrps.repo.data.Ranking
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RecordRepo {
    var context: Context? = null
    private val userKey by lazy { AdvertisingIdClient.getAdvertisingIdInfo(context!!).id }

    suspend fun getRecords() = withContext(Dispatchers.IO) {
        RetrofitClient.instance.getRecords().records
            .mapIndexed { index, record -> Ranking(
                record.id,
                index + 1,
                record.userKey,
                record.nick,
                record.score,
                record.time.substring(0, 10)
            )}
    }

    suspend fun addRecord(nick: String, score: Int) = withContext(Dispatchers.IO) {
        RetrofitClient.instance.addRecord(Record(
            id = 0,
            userKey = userKey,
            nick = nick,
            score = score,
            time = ""
        ))
    }

    suspend fun getMyRecords() = withContext(Dispatchers.IO) {
        RetrofitClient.instance.getMyRecords(userKey).records
            .mapIndexed { index, record -> Ranking(
                record.id,
                index + 1,
                record.userKey,
                record.nick,
                record.score,
                record.time.substring(0, 10)
            )}
    }

    suspend fun getMyBestRecord() = withContext(Dispatchers.IO) {
        RetrofitClient.instance.getMyRecords(userKey).records
            .getOrNull(0)?.score ?: 0
    }
}