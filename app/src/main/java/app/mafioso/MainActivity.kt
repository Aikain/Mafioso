package app.mafioso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.mafioso.ui.theme.MafiosoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MafiosoTheme {
                MafiosoApp()
            }
        }
    }
}
