package com.prince.mydicegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mydicegame.composeapp.generated.resources.Res
import mydicegame.composeapp.generated.resources.compose_multiplatform
import mydicegame.composeapp.generated.resources.dice_1
import mydicegame.composeapp.generated.resources.dice_2
import mydicegame.composeapp.generated.resources.dice_3
import mydicegame.composeapp.generated.resources.dice_4
import mydicegame.composeapp.generated.resources.dice_5
import mydicegame.composeapp.generated.resources.dice_6
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        val isPlayer1 = remember { mutableStateOf(true) }
        val playerScores = remember { mutableStateOf(Array(2) { 0 }) }
        val diceImages = remember {
            listOf(
                Res.drawable.dice_1,
                Res.drawable.dice_2,
                Res.drawable.dice_3,
                Res.drawable.dice_4,
                Res.drawable.dice_5,
                Res.drawable.dice_6
            )
        }
        val currentDiceImage = remember { mutableStateOf(Res.drawable.compose_multiplatform) }
        val winner = remember { mutableStateOf("") }
        Column(
            modifier = Modifier.fillMaxSize())
        {
            Text(
                text = "Dice Game",
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(top = 50.dp, bottom = 30.dp),
                fontStyle = FontStyle.Italic,
                fontSize = 40.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceEvenly,
            )
            {
                Text("Player 1 \nScore ${playerScores.value.get(0)}", fontSize = 25.sp)
                Text("Player 2 \nScore ${playerScores.value.get(1)}", fontSize = 25.sp)
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Winning Score: 50",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))
            if (winner.value.isNotEmpty()) {
                Text(
                    text = winner.value,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 50.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(currentDiceImage.value),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally).size(400.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    val randomNumber = (1..6).random()
                    currentDiceImage.value = diceImages.get(randomNumber - 1)
                    if (isPlayer1.value) {
                        playerScores.value[0]+= randomNumber
                    } else {
                        playerScores.value[1] += randomNumber
                    }

                    if (playerScores.value[0] >= 50) {
                        winner.value = "Player 1 Wins! \uD83C\uDF89"
                        playerScores.value = Array(2) { 0 }
                        isPlayer1.value = true
                        currentDiceImage.value = Res.drawable.compose_multiplatform
                    } else if (playerScores.value[1] >= 50) {
                        winner.value = "Player 2 Wins! \uD83C\uDF89"
                        playerScores.value = Array(2) { 0 }
                        isPlayer1.value = true
                        currentDiceImage.value = Res.drawable.compose_multiplatform
                    } else {
                        isPlayer1.value = !isPlayer1.value
                        winner.value = ""
                    }

                },
                modifier = Modifier.align(Alignment.CenterHorizontally).size(width = 300.dp, height = 70.dp)
            )
            {
                if (isPlayer1.value) {
                    Text("Roll the Dice for Player1")
                } else {
                    Text("Roll the Dice for Player2")
                }
            }
            IconButton(
                onClick = {
                    playerScores.value = Array(2) { 0 }
                    isPlayer1.value = true
                    currentDiceImage.value = Res.drawable.compose_multiplatform
                    winner.value = ""
                },
                modifier = Modifier.align(Alignment.End),

            )
            {
                Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)
            }

        }
    }
}
