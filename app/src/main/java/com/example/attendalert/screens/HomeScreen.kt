package com.example.attendalert.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.attendalert.R
import com.example.attendalert.data.CLassInformationViewModel
import com.example.attendalert.data.UserInformationViewModel
import com.example.attendalert.supabase
import io.github.jan.supabase.postgrest.from
//import com.androiddev.aaui.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import io.github.jan.supabase.postgrest.query.Columns

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleUi(modifier: Modifier ,viewModel: UserInformationViewModel,viewModelc: CLassInformationViewModel){
    Box(modifier = modifier.fillMaxSize()) {

//        BackGround(modifier = Modifier.fillMaxSize())
        Column(modifier=Modifier) {
            Headerbar(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            DateAndDay(modifier = Modifier.fillMaxWidth(),viewModel)

            LabSchedule(modifier = Modifier.fillMaxWidth(), onClick = {"Clicked"})

            AttendanceButtons(modifier = Modifier,onAttendedClick = { "Attended" },
                onMissedClick = { "Missed" },
                onCancelledClick = { "Cancelled" })

            Spacer(modifier = Modifier.height(25.dp))

            ClassesSchedule(modifier = Modifier.fillMaxWidth(), onClick = {"Clicked"},viewModel, viewModelc )


        }
    }
}
@Composable
fun BackGround(modifier: Modifier){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Image(
            painter = painterResource(id = R.drawable.gradient_bg),
            contentDescription =null,
            modifier= Modifier
                .fillMaxSize()
                .alpha(0.1f),
            contentScale = ContentScale.FillBounds
        )
    }
}
@Composable
fun Headerbar(modifier: Modifier) {
    val headerColor = Color(0xFF7A3853)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(headerColor)
            .padding(top = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "ATTEND ALERT",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Menu Icon",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateAndDay(modifier: Modifier,viewModel: UserInformationViewModel){
    val currentDate=LocalDate.now()
    val dateFormatter= DateTimeFormatter.ofPattern("EEE,MMM d,yyyy")
    val dayOfWeek=currentDate.format(DateTimeFormatter.ofPattern("EEEE"))

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Semester = ${viewModel.semester}",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Branch = ${viewModel.stream}",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${currentDate.format(dateFormatter)} - $dayOfWeek",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LabSchedule(modifier: Modifier=Modifier,onClick:()->Unit){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "LABS",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .background(color = Color.DarkGray),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2000.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Today's Lab",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }
}
@Composable
fun AttendanceButtons(
    modifier: Modifier=Modifier,
    onAttendedClick: () -> Unit,
    onMissedClick: () -> Unit,
    onCancelledClick: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.padding(start = 16.dp),
            onClick = onAttendedClick,
            colors = ButtonDefaults.run {
                buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.Black
                )
            },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Attended")
        }
        Button(
            onClick = onMissedClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Missed")
        }
        Button(
            modifier = Modifier.padding(end = 16.dp),
            onClick = onCancelledClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Cancelled")
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClassesSchedule(modifier: Modifier, onClick: () -> Unit, viewModel: UserInformationViewModel,viewModelc: CLassInformationViewModel) {
    val currentDate=LocalDate.now()
    val dateFormatter= DateTimeFormatter.ofPattern("EEE,MMM d,yyyy")
    val dayOfWeek=currentDate.format(DateTimeFormatter.ofPattern("EEEE"))
    val today = LocalDate.now() // Assuming you have logic to get current day
    val currentSemester = viewModel.semester // Assuming you have logic to get semester
    val currentBranch = viewModel.stream // Assuming you have logic to get branch
//    viewModel.
    viewModelc.fetchClassSchedule(dayOfWeek,currentSemester,currentBranch)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Lectures",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .background(color = Color.DarkGray),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2000.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                val class1Value = viewModelc.classSchedule.value?.class1 ?: "Loading..."

                Text(
                    text = class1Value,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
    AttendanceButtons(modifier = Modifier,onAttendedClick = { "Attended" },
        onMissedClick = { "Missed" },
        onCancelledClick = { "Cancelled" })

    Spacer(modifier = Modifier.height(5.dp))


    Column(modifier = Modifier.padding(16.dp)) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .background(color = Color.DarkGray),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2000.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                val class2Value = viewModelc.classSchedule.value?.class2?: "Loading..."
                Text(
                    text = class2Value,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

    AttendanceButtons(modifier = Modifier,onAttendedClick = { "Attended" },
        onMissedClick = { "Missed" },
        onCancelledClick = { "Cancelled" })

}



//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//fun ScheduleUiPreview() {
//    ScheduleUi(modifier = Modifier, viewModel = )
//}