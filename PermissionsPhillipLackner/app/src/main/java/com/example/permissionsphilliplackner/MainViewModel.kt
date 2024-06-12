package com.example.permissionsphilliplackner

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    val visiblePermissionDialogue = mutableListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialogue.removeLast()
    }

    fun onPermissionResult(permission: String, isGranted: Boolean) {
        if (!isGranted) {
            visiblePermissionDialogue.add(0, permission)
        }
    }
}