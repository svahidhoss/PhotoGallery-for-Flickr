package com.bignerdranch.android.photogallery2

import android.app.Application

class PhotoGalleryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferencesRepository.initialize(this)
    }
}
