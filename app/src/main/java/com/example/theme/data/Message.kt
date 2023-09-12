package com.example.theme.data

import androidx.compose.runtime.Immutable
import com.example.theme.R

/**
 * @author quangtran
 * @since 9/12/23
 */
@Immutable
data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else
)
