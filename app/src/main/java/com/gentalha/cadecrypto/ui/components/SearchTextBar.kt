package com.gentalha.cadecrypto.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gentalha.cadecrypto.ui.theme.BlueGray
import com.gentalha.cadecrypto.ui.theme.DarkBlue

@Composable
fun SearchTextBar(
    onValueChange: (String) -> Unit,
    onKeyBoardClickAction: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(true) }
    var query by remember {
        mutableStateOf("")
    }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val expandedAnimateValue: Float by animateFloatAsState(
        if (expanded) 1f else 0.14f,
        finishedListener = { value ->
            showText = (value < 1f)
        }, label = ""
    )
    Row(
        modifier = Modifier
            .background(DarkBlue)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .height(54.dp)
                .fillMaxWidth(expandedAnimateValue)
                .background(BlueGray, RoundedCornerShape(999.dp)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                expanded = !expanded
                if (expanded) {
                    focusRequester.requestFocus()
                } else {
                    query = ""
                    focusManager.clearFocus()
                }

            }) {
                Icon(
                    imageVector = if (expanded) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            BasicTextField(
                value = query,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onKeyBoardClickAction(query)
                    }
                ),
                cursorBrush = SolidColor(Color.White),
                onValueChange = {
                    query = it
                    onValueChange(query)
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                ),
                modifier = Modifier.focusRequester(focusRequester)
            )
        }
        if (!expanded && showText) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Exchanges",
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }
    }
}
