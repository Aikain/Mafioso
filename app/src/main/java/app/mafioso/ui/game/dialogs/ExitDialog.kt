package app.mafioso.ui.game.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.mafioso.R


@Composable
fun ExitDialog(
    dismiss: () -> Unit,
    confirm: () -> Unit,
    leader: Boolean,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = dismiss,
        title = { Text(text = stringResource(R.string.game_exit_confirm_title)) },
        text = {
            Text(
                text = stringResource(if (leader) R.string.game_exit_leader_text else R.string.game_exit_normal_text),
            )
        },
        confirmButton = {
            Button(onClick = confirm) {
                Text(text = stringResource(R.string.game_exit_confirm_btn))
            }
        },
        dismissButton = {
            Button(onClick = dismiss) {
                Text(text = stringResource(R.string.game_exit_dismiss_btn))
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
fun ExitDialogPreview() {
    ExitDialog(
        dismiss = {},
        confirm = {},
        leader = true,
    )
}
