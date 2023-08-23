package com.example.chartapp

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chartapp.ZRequest.FungsiLain

@Composable
fun DounatChart() {
    val values: List<Float> = listOf(55f, 31f,7.7f, 6.3f)
    val colors: List<Color> = listOf(
        Color(0xFFFF6384),
        Color(0xFFFFCE56),
        Color(0xFF36A2EB),
        Color(0xFF83d76c)
    )
    val sizeDp = 200.dp
    val thickness = 36.dp

    val legend: List<String> = listOf("Tarik Tunai (55%)", "QRIS Payment (31%)", "Topup Gopay (7.7%)", "Lainnya (6.3%)")

    val sumOfValues = values.sum()

    val proportions = values.map {
        it * 100 / sumOfValues
    }

    val sweepAngles = proportions.map {
        360 * it / 100
    }

    Canvas(modifier = Modifier.size(size = sizeDp)){
        var startAngle = -90f

        for (i in values.indices) {
            drawArc(
                color = colors[i],
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                useCenter = false,
                style = Stroke(width = thickness.toPx(), cap = StrokeCap.Butt)
            )
            startAngle += sweepAngles[i]
        }
    }

    Spacer(modifier = Modifier.height(32.dp))

    Column {
        Text(text = "Pilih Transaksi :")

        Spacer(modifier = Modifier.height(10.dp))

        for (i in values.indices) {
            DisplayLegend(color = colors[i], legend = legend[i])
        }
    }
}

@Composable
fun DisplayLegend(color: Color, legend: String) {
    val cntx = LocalContext.current
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(10.dp)
                .background(color, shape = CircleShape))

        Spacer(modifier = Modifier.width(4.dp))

        TextButton(onClick = { FungsiLain.setView(legend, cntx) }) {
            Text(text = legend, color = Color.Black)
        }
    }
}

//Static value
private fun lineChartData() = listOf(
    3, 7, 8, 10, 5, 10, 1, 3, 5, 10, 7, 7
)

@Composable
fun LineChartLayout() {
    val xValues = listOf("Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Juli", "Agust", "Sep", "Okt", "Nov", "Des")
    val yValues = (0..9).map { (it + 1) }
    val paddingSpace = 25.dp
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(align = Alignment.BottomStart)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                val xAxisSpace = size.width / (lineChartData().size + 1)
                val yAxisSpace = size.height / yValues.size

                val distance = size.width / (lineChartData().size + 1)
                var currentX = 0F
                val maxValue = lineChartData().maxOrNull() ?: 0
                val points = mutableListOf<PointF>()

                lineChartData().forEachIndexed { index, data ->
                    if (lineChartData().size >= index + 2) {
                        val y0 = (maxValue - data) * (size.height / maxValue)
                        val x0 = currentX + distance
                        points.add(PointF(x0, y0))
                        currentX += distance
                    }
                }

                for (i in 0 until points.size - 1) {
                    drawLine(
                        start = Offset(points[i].x, points[i].y),
                        end = Offset(points[i + 1].x, points[i + 1].y),
                        color = Color(0xFF3F51B5),
                        strokeWidth = 8f
                    )
                }

                for (i in xValues.indices) {
                    drawContext.canvas.nativeCanvas.drawText(
                        "${xValues[i]}",
                        xAxisSpace * (i + 1),
                        size.height - 30,
                        textPaint
                    )
                }

                for (i in yValues.indices) {
                    drawContext.canvas.nativeCanvas.drawText(
                        "${yValues[i]}",
                        paddingSpace.toPx() / 2f,
                        size.height - yAxisSpace * (i + 1),
                        textPaint
                    )
                }
            }
        }
    }
}