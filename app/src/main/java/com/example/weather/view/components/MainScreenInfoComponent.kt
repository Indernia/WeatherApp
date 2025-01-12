import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.view.components.FigureComponent


@Composable
fun MainScreenInfoComponent(
    city: String,
    temp: String,
    clothingRes: Int,
    accessoryRes: Int,
    onClick: () -> Unit
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        val widthRatio = maxWidth / 360 // Reference width of 360
        val heightRatio = maxHeight / 800 // Reference height of 800

        Box(
            modifier = Modifier
                .width((300 * widthRatio.value).dp)
                .height((550 * heightRatio.value).dp)
                .clickable { onClick() }
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = city,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 52.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$temp°C",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, fontSize = 34.sp)

                )
                Spacer(modifier = Modifier.height(16.dp))


                FigureComponent(
                    baseFigureResId = R.drawable.dude,
                    clothingResId = clothingRes,
                    accessoryResId = accessoryRes,
                    modifier = Modifier.size(350.dp)
                )

            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewMainScreenInfoComponent() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .wrapContentSize(Alignment.TopCenter)
        ) {
            MainScreenInfoComponent(
                city = "Copenhagen",
                temp = "10",
                clothingRes = R.drawable.trunks,
                accessoryRes = R.drawable.hat,
                onClick = { /* ToDO */ }
            )
        }
    }
}

