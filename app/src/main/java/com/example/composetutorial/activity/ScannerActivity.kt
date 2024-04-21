package com.example.composetutorial.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.R
import com.example.composetutorial.ui.theme.CardboardCompanionTheme

class ScannerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScannerView()
        }
    }

    @Composable
    fun ScannerView() {
        val identifyClicked by rememberSaveable { mutableStateOf(false) }

        CardboardCompanionTheme {
            Column {
                ExitBtn()
                Spacer(modifier = Modifier.weight(1f))
                if (identifyClicked){
                    IdentificationPopUp()
                    Spacer(modifier = Modifier.weight(1f))
                }
                IdentifyBtn()
            }
        }
    }

    @Composable
    fun ExitBtn() {
        Row (modifier = Modifier.padding(30.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_close_circle),
                    contentDescription = null,
                    tint = Color.Red.copy(alpha = 0.6f))
            }
        }
    }

    @Composable
    fun IdentificationPopUp() {

    }

    @Composable
    fun IdentifyBtn() {
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.alpha(0.6f).padding(10.dp).fillMaxWidth(),
            shape = RoundedCornerShape(40)
        ) {
            Text("Identify")
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun ScannerViewPreview() {
        ScannerView()
    }

}