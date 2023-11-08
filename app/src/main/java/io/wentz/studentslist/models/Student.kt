package io.wentz.studentslist.models

import androidx.annotation.NonNull
import java.io.Serializable
import java.util.UUID

data class Student(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val phone: String,
    val email: String
): Serializable {
    @NonNull
    override fun toString(): String {
        return name
    }
}
