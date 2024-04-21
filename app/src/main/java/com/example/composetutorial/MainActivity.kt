package com.example.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetutorial.activity.CollectionActivity
import com.example.composetutorial.activity.ScannerActivity
import com.example.composetutorial.activity.TutorialActivity
import com.example.composetutorial.ui.theme.CardboardCompanionTheme

class MainActivity : ComponentActivity() {

    private var collectionActivity: CollectionActivity = CollectionActivity()
    private var scannerActivity : ScannerActivity = ScannerActivity()
    private var tutorialActivity : TutorialActivity = TutorialActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }

    @Composable
    fun MainView() {
        CardboardCompanionTheme {
            collectionActivity.CollectionView(modifier = Modifier.fillMaxSize())
            //scannerActivity.ScannerView()
        }
    }


    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun MainViewPreview() {
        MainView()
    }
}