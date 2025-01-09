import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.weather.view.components.FigureComponent


@Composable
fun MainScreenInfoComponent(
    city: String,
    temp: String,
    figureComponent: FigureComponent,
    onClick: () -> Unit
) {
    val context = LocalContext.current


    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        val widthRatio = maxWidth / 360 // Reference width of 360
        val heightRatio = maxHeight / 800 // Reference height of 800

        Box(
            modifier = Modifier
                .width((300 * widthRatio.value).dp)
                .height((450 * heightRatio.value).dp)
                .clickable { onClick() }
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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

                AndroidView(
                    factory = { FigureComponent(context) },
                    modifier = Modifier.size(300.dp)
                ) { figureComponent ->
                    //figureComponent.setBaseFigure(R.drawable.dude) // Set base figure
                    //figureComponent.setClothing(clothing) // Replace with actual clothing drawable
                    //figureComponent.setAccessory(accesory) // Replace with actual accessory drawable
                }
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
                figureComponent = FigureComponent(LocalContext.current),
                onClick = { /* ToDO */ }
            )
        }
    }
}

