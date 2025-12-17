package com.evalenzuela.navigation.ui.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AuthHeader(
    modifier: Modifier = Modifier,
    title: String? = null,
    backIconRes: Int? = null,
    onBack: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = 16.dp)
    ) {
        if (title != null && backIconRes != null && onBack != null) {
            TopBar(
                title = title,
                backIconRes = backIconRes,
                onBack = onBack,
                titleColor = MaterialTheme.colorScheme.onBackground
            )
        } else {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "logo",
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.Center),
                tint = MaterialTheme.colorScheme.primary
            )

        }
    }
}

@Composable
fun TopBar(title: String, backIconRes: Int, onBack: () -> Unit, titleColor: Color) {
    TODO("Not yet implemented")
}