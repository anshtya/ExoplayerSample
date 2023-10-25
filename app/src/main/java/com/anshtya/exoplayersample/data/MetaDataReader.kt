package com.anshtya.exoplayersample.data

import android.net.Uri

interface MetaDataReader {
    fun getMetaDataFromUri(contentUri: Uri): MetaData?
}