package dev.android.assignment.intent

data class AppState(val displayData: String, val error: Error? = null) {
    data class Error(val message: String)
    companion object {
        fun initial() = AppState(displayData = "")
    }
}

sealed class AppIntent {
    data class KeyPressed(val key: String) : AppIntent()
    data object DeletePressed : AppIntent()
    data object ClearPressed : AppIntent()
    data class OverflowError(val message: String): AppIntent()
}