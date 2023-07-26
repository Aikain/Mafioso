package app.mafioso.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import app.mafioso.R
import app.mafioso.data.Player
import app.mafioso.role.Mafia
import app.mafioso.ui.game.dialogs.PlayerDialog

@Composable
fun PlayerCard(
    player: Player,
    status: Status,
    removePlayer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showPlayerDialog by remember { mutableStateOf(false) }

    if (showPlayerDialog)
        PlayerDialog(
            player = player,
            dismiss = { showPlayerDialog = false },
            removePlayer = removePlayer
        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable(
            enabled = status != Status.FINISH,
            onClick = { showPlayerDialog = true },
        ),
    ) {
        Box(
            modifier = modifier
                .background(color = Color.Gray, shape = RoundedCornerShape(10))
                .aspectRatio(1f),
            contentAlignment = Alignment.Center,
        ) {
            player.role?.let { role ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(role.getName()),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Unspecified.copy(alpha = if (player.alive) 1f else .5f),
                    )
                    Box {
                        Image(
                            painter = painterResource(id = role.getImage()),
                            contentDescription = stringResource(role.getName()),
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    this.alpha = if (player.alive) 1f else .5f
                                },
                        )
                        if (!player.alive)
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.killed),
                                tint = Color.Red,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .zIndex(2f),
                            )
                    }
                }
            } ?: run {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(.8f)
                )
            }
        }
        Text(
            text = player.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
fun PlayerCardWithoutRolePreview() {
    PlayerCard(
        player = Player(
            id = 1,
            name = "Pelaaja",
        ),
        status = Status.INITIALIZE,
        removePlayer = {},
        modifier = Modifier
            .width(64.dp),
    )
}

@Preview
@Composable
fun PlayerCardPreview() {
    PlayerCard(
        player = Player(
            id = 1,
            name = "Pelaaja",
            role = Mafia(),
        ),
        status = Status.RUNNING,
        removePlayer = {},
        modifier = Modifier
            .width(64.dp),
    )
}

@Preview
@Composable
fun PlayerCardKilledPreview() {
    PlayerCard(
        player = Player(
            id = 1,
            name = "Pelaaja",
            role = Mafia(),
            alive = false,
        ),
        status = Status.RUNNING,
        removePlayer = {},
        modifier = Modifier
            .width(64.dp),
    )
}
