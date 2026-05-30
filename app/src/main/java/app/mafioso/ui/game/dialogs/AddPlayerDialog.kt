package app.mafioso.ui.game.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.mafioso.R

const val MAX_NAME_LENGTH = 12

@Composable
fun AddPlayerDialog(
    dismiss: () -> Unit,
    addPlayer: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var name by remember { mutableStateOf("") }
    val isError = name.trim().length > MAX_NAME_LENGTH

    BackHandler {
        dismiss()
    }

    AlertDialog(
        onDismissRequest = dismiss,
        title = { Text(text = stringResource(R.string.add_player_title)) },
        text = {
            TextField(
                label = { Text(text = stringResource(R.string.add_player_name_label)) },
                value = name,
                onValueChange = { name = it },
                isError = isError,
                supportingText = {
                    if (isError)
                        Text(
                            text = stringResource(R.string.add_player_max_length_error, MAX_NAME_LENGTH),
                            color = MaterialTheme.colorScheme.error,
                        )
                },
            )
        },
        confirmButton = {
            Button(
                onClick = { addPlayer(name.trim()) },
                enabled = name.isNotBlank() && !isError,
            ) {
                Text(text = stringResource(R.string.add_player_confirm_btn))
            }
        },
        dismissButton = {
            Button(onClick = dismiss) {
                Text(text = stringResource(R.string.add_player_dismiss_btn))
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
fun AddPlayerDialogPreview() {
    AddPlayerDialog(
        dismiss = {},
        addPlayer = {},
    )
}
