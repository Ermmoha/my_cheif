package com.example.kp_kotlin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kp_kotlin.ui.app.main.catalogue.CatalogueScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChiefApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    CatalogueScreen({})
}
