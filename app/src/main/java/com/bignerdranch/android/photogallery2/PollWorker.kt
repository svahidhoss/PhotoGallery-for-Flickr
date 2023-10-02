package com.bignerdranch.android.photogallery2

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
                    notifyUser()
                }
            }

            return Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "")
            return Result.failure()
        }
    }

    private fun notifyUser() {
        val intent = MainActivity.newIntent(context)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val resources = context.resources

        val notification = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
            .setTicker(resources.getString(R.string.new_pictures_title))
            .setSmallIcon(android.R.drawable.ic_menu_report_image)
            .setContentTitle(resources.getString(R.string.new_pictures_title))
            .setContentText(resources.getString(R.string.new_pictures_text))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(0, notification)
    }
}
