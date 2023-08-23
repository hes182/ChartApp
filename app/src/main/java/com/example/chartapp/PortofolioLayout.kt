package com.example.chartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chartapp.Object.ObjectTransaksi
import com.example.chartapp.ZRequest.FungsiLain
import com.example.chartapp.ui.theme.ChartAppTheme

class PortofolioLayout : ComponentActivity() {
    private var label = ""
    private var listData = ArrayList<ObjectTransaksi>()
    private var bund = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null) {
            bund = intent.extras!!
            label = bund.getString("label").toString()
            listData = bund.getParcelableArrayList("obyek")!!
        }
        setContent {
            ChartAppTheme(darkTheme = false) {
                Greeting2(label, listData)
            }
        }
    }
}

@Composable
fun Greeting2(label: String, listData: ArrayList<ObjectTransaksi>) {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Transparent)){
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp))
        Spacer(modifier = Modifier.height(10.dp))
        ListViewData(listData = listData)
    }
}

@Composable
fun ListViewData(listData: ArrayList<ObjectTransaksi>) {
    LazyColumn(modifier = Modifier.padding(top = 10.dp, start = 4.dp, end = 4.dp)) {
        itemsIndexed(listData) {index, item ->
            val paddingModif = Modifier.padding(8.dp)
            Card(elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = paddingModif
                    .fillMaxWidth()) {
                Column(modifier = paddingModif) {
                    Row() {
                        Text(text = "Tanggal Transaksi",
                            modifier = Modifier
                                .padding(end = 8.dp, start = 4.dp, top = 4.dp, bottom = 4.dp)
                                .weight(1f))
                        Text(text = listData[index].trxDate,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(end = 8.dp, start = 4.dp, top = 4.dp, bottom = 4.dp)
                                .weight(1f),
                            textAlign = TextAlign.End,
                        )
                    }
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        Text(text = "Nominal Pembayaran",
                            modifier = Modifier
                                .padding(end = 8.dp, start = 4.dp, top = 4.dp, bottom = 4.dp)
                                .weight(1f))
                        Text(text = "Rp " + FungsiLain.setFormatText(listData[index].nominal.toInt(), "#,###"),
                            color = Color.Black,
                            modifier = Modifier
                                .padding(end = 8.dp, start = 4.dp, top = 4.dp, bottom = 4.dp)
                                .weight(1f),
                            textAlign = TextAlign.End,
                        )
                    }
                }
            }
        }
    }
}

