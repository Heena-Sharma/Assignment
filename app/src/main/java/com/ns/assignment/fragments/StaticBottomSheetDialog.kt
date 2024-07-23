package com.ns.assignment.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ns.assignment.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaticBottomSheet() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val list1 = listOf(
        "a" to 5, "e" to 4, "r" to 3
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { scope.launch { sheetState.show() } }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Show Bottom Sheet Dialogue")
            }
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
              if (sheetState.isVisible) {
                ModalBottomSheet(
                    onDismissRequest = { scope.launch { sheetState.hide() } },
                    sheetState = sheetState
                ) {
                    BottomSheetContent(list1)
                }
            }
        }
    }
}

@Composable
fun BottomSheetContent(list1: List<Pair<String, Int>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen._16sdp))
    ) {
        Text("List 1", fontWeight = FontWeight.Bold, modifier = Modifier.padding(dimensionResource(id = R.dimen._14sdp)))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8sdp)))
        LazyColumn {
            items(list1) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = item.first,
                        fontSize = dimensionResource(id = R.dimen._14sdp).value.sp,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen._14sdp))
                    )
                    Text(
                        item.second.toString(),
                        fontSize = dimensionResource(id = R.dimen._14sdp).value.sp,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen._14sdp))
                    )
                }
            }
        }
    }
}