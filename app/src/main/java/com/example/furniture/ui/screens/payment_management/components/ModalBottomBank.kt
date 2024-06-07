package com.example.furniture.ui.screens.payment_management.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furniture.R
import com.example.furniture.ui.screens.payment_management.BankType
import com.example.furniture.ui.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomBank(
    visible: Boolean,
    onClose: () -> Unit,
    onItemSelected: (item: BankType) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val vietcombank = BankType(R.drawable.icon_vietcombank, "vietcombank", "Vietcombank")
    val agribank = BankType(R.drawable.icon_agribank, "agribank", "Agribank")
    val tpBank = BankType(R.drawable.icon_tpbank, "tpbank", "TP Bank")
    val mbBank = BankType(R.drawable.icon_mbbank, "mbbank", "MB Bank")
    val vpBank = BankType(R.drawable.icon_vpbank, "vpbank", "VP Bank")
    val vibBank = BankType(R.drawable.icon_vibbank, "vibbank", "VIB Bank")
    val acbBank = BankType(R.drawable.icon_acbbank, "acbbank", "ACB Bank")
    val banks = listOf(
        vietcombank,
        agribank,
        tpBank,
        mbBank,
        vpBank,
        vibBank,
        acbBank
    )
    if (visible) {
        ModalBottomSheet(
            onDismissRequest = {
                onClose()
            },
            sheetState = sheetState
        ) {
            IconButton(onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onClose()
                    }
                }
            }, modifier = Modifier.align(alignment = Alignment.End)) {
                Icon(painter = painterResource(id = R.drawable.close), contentDescription = "Icon close", modifier = Modifier.size(25.dp))
            }
            LazyRow(
                state = listState
            ) {
                items(banks) { item ->
                    Column (modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clickable {
                            onItemSelected(item)
                            scope
                                .launch {
                                    sheetState.hide()
                                }
                                .invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        onClose()
                                    }
                                }
                        }
                        , horizontalAlignment = Alignment.CenterHorizontally){
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = "Icon bank",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(5.dp))
                        )
                        Text(text = item.name, style = TextStyle(
                            fontSize = 12.sp
                        ))
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }


        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ModalBottomBankPreview() {
    AppTheme {
        ModalBottomBank(visible = true, onClose = { }) {

        }
    }
}