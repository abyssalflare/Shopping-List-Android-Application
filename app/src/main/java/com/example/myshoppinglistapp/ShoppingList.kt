package com.example.myshoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShoppingItem(val id: Int,
                        var name: String,
                        var quantity: Int,
                        var isEditting: Boolean = false)
{

}

@Composable
fun ShoppingListApp()
{
    //mutable states
    var shoppingItems by remember{ mutableStateOf(listOf<ShoppingItem>())};
    var showDialog by remember{ mutableStateOf(false)};
    var itemName by remember { mutableStateOf("")};
    var itemQuantity by remember { mutableStateOf("")};

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ){
        Button(
            onClick = {showDialog = true},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item");
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp))
        {
            items(shoppingItems){

            }
        }
    }

    if(showDialog)
    {
        AlertDialog(
            onDismissRequest = {showDialog = false},
            confirmButton = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                //Add Item Button
                                Button(onClick = {
                                    if(itemName.isNotBlank())
                                    {
                                        val newItem = ShoppingItem(
                                            id = shoppingItems.size+1,
                                            name = itemName,
                                            quantity = itemQuantity.toInt(),
                                        )

                                        shoppingItems += newItem;
                                        showDialog = false;
                                        itemName = "";
                                        itemQuantity = "";
                                    }
                                }) {
                                    Text(text = "Add");
                                }
                                //Cancel Button
                                Button(onClick = {showDialog = false}) {
                                    Text(text = "Cancel")
                                }
                            }
            },
            title = { Text(text = "Add Shopping Item")},
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {itemName = it},
                        singleLine = true,//ensure that it is a single line textfield
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {itemQuantity = it},
                        singleLine = true,//ensure that it is a single line textfield
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        )

    }
}


