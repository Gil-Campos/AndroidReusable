package com.example.downloadmanager

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(private val context: Context) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("application/pdf")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("document.pdf")
            .addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "document.pdf")
        return downloadManager.enqueue(request)
    }
}