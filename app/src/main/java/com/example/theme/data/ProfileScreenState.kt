package com.example.theme.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

/**
 * @author quangtran
 * @since 9/12/23
 */
@Immutable
data class ProfileScreenState(
    val userId: String,
    @DrawableRes val photo: Int?,
    val name: String,
    val status: String,
    val displayName: String,
    val position: String,
    val twitter: String = "",
    val timeZone: String?, // Null if me
    val commonChannels: String? // Null if me
) {
    fun isMe() = userId == meProfile.userId
}