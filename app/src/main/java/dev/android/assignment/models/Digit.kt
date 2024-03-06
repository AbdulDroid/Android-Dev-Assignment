package dev.android.assignment.models

data class Digit(val digitChar: Char, val fullString: String, val index: Int) {
    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Digit -> digitChar == other.digitChar
            else -> super.equals(other)
        }
    }

    override fun hashCode(): Int {
        var result = digitChar.hashCode()
        result = 31 * result + fullString.hashCode()
        result = 31 * result + index
        return result
    }
}

operator fun Digit.compareTo(other: Digit): Int {
    return fullString.compareTo(other.fullString)
}
