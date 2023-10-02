package com.bignerdranch.android.photogallery2

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.first

private const val TAG = "PollWorker"

class PollWorker(
    private val context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(
    context,
    workerParameters
) {
    override suspend fun doWork(): Result {
        Log.i(TAG, "Work request triggered")
        val preferencesRepository = PreferencesRepository.get()
        val photoRepository = PhotoRepository()

        val query = preferencesRepository.storedQuery.first()
        val lastId = preferencesRepository.lastResultId.first()

        if (query.isEmpty()) {
            Log.i(TAG, "No saved query, finishing early")
            return Result.success()
        }

        try {
            val galleryItems = photoRepository.searchPhotos(query)

            if (galleryItems.isNotEmpty()) {
                val newResultId = galleryItems.first().id
                if (newResultId == lastId) {
                    Log.i(TAG, "Still have the same result: $newResultId")
                } else {
                    Log.i(TAG, "Got a new result: $newResultId")
                    preferencesRepository.setLastResultId(newResultId)
                }
            }

            return Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "")
            return Result.failure()
        }
    }
}
