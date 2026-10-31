package com.example.assignmentm1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignmentm1.ui.theme.AssignmentM1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentM1Theme {
                    MainContent()
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    // Define a list of available colors
    val colors = listOf(Color.White, Color.Blue, Color.Green, Color.Yellow, Color.Red)

    // Create the state variable to track the current background color
    var backgroundColor by remember { mutableStateOf(Color.White) }

    var menuExpanded by remember { mutableStateOf(false) }
    var amountText by remember { mutableStateOf("") }
    var tips by remember { mutableDoubleStateOf(0.00) }

    val amount: Double = amountText.toDoubleOrNull() ?: 0.0
    val tipsAmount : Double = amount * (tips/100)
    val totalBill : Double = tipsAmount + amount

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tip Calculator")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =
                        MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor =
                        MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    //Direct Action Icon 1: Color Palette
                    IconButton(onClick = { backgroundColor = colors.random()}) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = "Change Background"
                        )
                    }

                    // Overflow Menu Icon (Three Dots)
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Tips Options"
                        )
                    }

                    //Dropdown items for the overflow menu
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = {menuExpanded = false}
                    ) {
                        DropdownMenuItem(
                            text = { Text("tips10")},
                            onClick = {
                                menuExpanded = false
                                tips = 10.0
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("tips15")},
                            onClick = {
                                menuExpanded = false
                                tips = 15.0
                            }
                        )
                    }
                }
            ) //topAppBar
        } //topBar

    ) //Scaffold
    { innerPadding ->
        //Main Screen content showing the last clicked action
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                value = amountText,
                onValueChange = { amountText = it},
                label = { Text("Bill Amount") },
                modifier = Modifier.padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
            )

            Text(
                text = "Tips percentage: ${tips.toInt()}%\n" +
                        "Tips amount: $${"%.2f".format(tipsAmount)}\n"
            )

            Text(
                text = "Total bill to pay: $${"%.2f".format(totalBill)}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AssignmentM1Theme {
        MainContent()
    }
}