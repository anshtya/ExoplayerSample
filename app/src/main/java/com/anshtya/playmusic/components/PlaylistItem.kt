package com.anshtya.playmusic.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.anshtya.playmusic.data.model.Snippet

@Composable
fun PlaylistItem(
    snippet: Snippet,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Surface(
            color = Color.Gray,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.width(150.dp).height(100.dp)
        ) {

        }
        Column(Modifier.weight(1f)) {
            Text(
                text = snippet.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(snippet.channelTitle)
        }
    }
}