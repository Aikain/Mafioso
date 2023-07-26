package app.mafioso.ui.game.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import app.mafioso.R
import app.mafioso.data.Player
import app.mafioso.role.Mafia

@Composable
fun PlayerDialog(
    player: Player,
    dismiss: () -> Unit,
    removePlayer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        dismiss()
    }

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = dismiss,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
            ) {
                Text(
                    text = player.name + (player.role?.let { " - " + stringResource(it.getName()) }
                        ?: ""),
                    style = MaterialTheme.typography.headlineLarge,
                )
                player.role?.let { role ->
                    Box {
                        Image(
                            painter = painterResource(id = role.getImage()),
                            contentDescription = stringResource(role.getName()),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.25f)
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
                                    .fillMaxWidth()
                                    .fillMaxHeight(.25f)
                                    .zIndex(2f),
                            )
                    }
                } ?: run {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.25f),
                    )
                }
                Text(
                    text = player.role?.let { stringResource(it.getDescription()) } ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    minLines = 4,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
                if (player.alive)
                    Button(
                        onClick = {
                            removePlayer()
                            dismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp, vertical = 8.dp),
                    ) {
                        Text(stringResource(R.string.remove_player_btn))
                    }
            }
            Button(
                onClick = dismiss,
            ) {
                Text(text = stringResource(R.string.close_btn))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerDialogWithoutRolePreview() {
    PlayerDialog(
        player = Player(
            id = 1,
            name = "Pelaaja A",
        ),
        dismiss = {},
        removePlayer = {},
    )
}

@Preview(showBackground = true)
@Composable
fun PlayerDialogWithRolePreview() {
    PlayerDialog(
        player = Player(
            id = 1,
            name = "Pelaaja A",
            role = Mafia(),
        ),
        dismiss = {},
        removePlayer = {},
    )
}

@Preview(showBackground = true)
@Composable
fun PlayerDialogWithKilledPlayerPreview() {
    PlayerDialog(
        player = Player(
            id = 1,
            name = "Pelaaja A",
            role = Mafia(),
            alive = false,
        ),
        dismiss = {},
        removePlayer = {},
    )
}
