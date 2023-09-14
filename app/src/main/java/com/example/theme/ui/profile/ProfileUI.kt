package com.example.theme.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.theme.R
import com.example.theme.ui.theme.ChatTheme

/**
 * @author quangtran
 * @since 9/13/23
 */


@Preview
@Composable
fun ConvPreviewPortraitOtherDefault() {
    ChatTheme {
        UserInfoScreen()
    }
}

@Composable
fun UserInfoScreen() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp)
            .navigationBarsPadding()
            .height(48.dp)
            .widthIn(min = 48.dp)
            // Offsets the FAB to compensate for CoordinatorLayout collapsing behaviour
            .offset(y = ((-100).dp))

        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    modifier = Modifier
                        .heightIn(max = 64.dp)
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp
                        )
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.jetchat_logo),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(
                    text = "Name",
                    modifier = Modifier.baselineHeight(32.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Position",
                    modifier = Modifier.baselineHeight(24.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Company",
                    modifier = Modifier.baselineHeight(24.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                ProfileProperty(stringResource(R.string.display_name), "name")

                ProfileProperty(stringResource(R.string.status), "Status")

                ProfileProperty(stringResource(R.string.twitter), "link")

                FloatingActionButton(
                    onClick = { },
                    modifier = modifier
                ) {}
            }
        }
    }
}

@Composable
fun ProfileProperty(label: String, name: String) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Divider()
        Text(
            text = label,
            modifier = Modifier.baselineHeight(24.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = name,
            modifier = Modifier.baselineHeight(24.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

data class BaselineHeightModifier(
    val heightFromBaseline: Dp
) : LayoutModifier {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {

        val textPlaceable = measurable.measure(constraints)
        val firstBaseline = textPlaceable[FirstBaseline]
        val lastBaseline = textPlaceable[LastBaseline]

        val height = heightFromBaseline.roundToPx() + lastBaseline - firstBaseline
        return layout(constraints.maxWidth, height) {
            val topY = heightFromBaseline.roundToPx() - firstBaseline
            textPlaceable.place(0, topY)
        }
    }
}

fun Modifier.baselineHeight(heightFromBaseline: Dp): Modifier =
    this.then(BaselineHeightModifier(heightFromBaseline))
