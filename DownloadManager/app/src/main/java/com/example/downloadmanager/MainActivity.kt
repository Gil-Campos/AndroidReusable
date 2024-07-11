package com.example.downloadmanager

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.downloadmanager.ui.theme.DownloadManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DownloadManagerTheme {
                DownloadButton(
                    context = this, Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Composable
fun DownloadButton(context: Context, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(onClick = {
            val downloader = AndroidDownloader(context)
            downloader.downloadFile("http://www.africau.edu/images/default/sample.pdf")
        }) {
            Text(text = "Downoad Image")
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DownloadButtonPreview() {
    DownloadButton(
        LocalContext.current,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}
