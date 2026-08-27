package com.exemplo.contador

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exemplo.contador.ui.theme.ContadorAppTheme

@Composable
fun ContadorRoute(viewModel: ContadorViewModel = viewModel()) {
    ContadorScreen(
        contador = viewModel.contador,
        onIncrementar = viewModel::incrementar,
        onDecrementar = viewModel::decrementar,
        onZerar = viewModel::zerar
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContadorScreen(
    contador: Int,
    onIncrementar: () -> Unit,
    onDecrementar: () -> Unit,
    onZerar: () -> Unit
) {

    val corDoNumero = when {
        contador < 0 -> MaterialTheme.colorScheme.error
        contador > 0 -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.onBackground
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Contador") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = contador.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = corDoNumero
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                OutlinedButton(
                    onClick = onDecrementar,
                    enabled = contador > 0
                ) {
                    Text(text = "-1")
                }

                Button(onClick = onIncrementar) {
                    Text(text = "+1")
                }
            }

            TextButton(onClick = onZerar) {
                Text(text = "Zerar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContadorScreenPreview() {
    ContadorAppTheme {
        ContadorScreen(
            contador = 3,
            onIncrementar = {},
            onDecrementar = {},
            onZerar = {}
        )
    }
}

@Preview(showBackground = true, name = "Contador zerado (botão -1 desabilitado)")
@Composable
private fun ContadorScreenZeradoPreview() {
    ContadorAppTheme {
        ContadorScreen(
            contador = 0,
            onIncrementar = {},
            onDecrementar = {},
            onZerar = {}
        )
    }
}
