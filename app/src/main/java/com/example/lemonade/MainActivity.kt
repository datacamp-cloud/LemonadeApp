package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    var showImage by remember { mutableStateOf("tree") }

    //val showImage = nextImage()

    val nextImage: () -> String = {
        when (showImage) {
            "tree" -> "press"
            "press" -> "drink"
            "drink" -> "empty"
            "empty" -> "tree"
            else -> ""
        }
    }

    // les images à afficher en fonction du mot clé
    val imageResource = when(showImage){
        "tree" -> R.drawable.lemon_tree
        "press" -> R.drawable.lemon_squeeze
        "drink" -> R.drawable.lemon_drink
        "empty" -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    // description de l'image en fonctio du mot clé
    val descriptionImage = when(showImage){
        "tree" -> R.string.lemon_tree
        "press" -> R.string.lemon_press
        "drink" -> R.string.lemon_drink
        "empty" -> R.string.empty
        else -> R.string.lemon_tree
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "",
            modifier = modifier
                .background(
                    color = Color.Green.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(35.dp)
                .clickable { showImage = nextImage() } //modifie directement l'image en fonction de l'image selectionne
        )

        Spacer(
            modifier = modifier.height(16.dp)
        )

        Text(
            text = stringResource(descriptionImage)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}