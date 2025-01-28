package com.example.a8_176.ui.custumwidget

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.vector.ImageVector

import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePickerFields(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    colors: TextFieldColors
) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID")) // Format tanggal Indonesia
    val calendar = Calendar.getInstance()

    // Membuat context untuk date picker dialog
    val context = LocalContext.current

    // Fungsi untuk membuka DatePickerDialog
    val openDatePicker = {
        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedCalendar = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                val date = dateFormat.format(selectedCalendar.time)
                onDateSelected(date)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    // TextField untuk menampilkan tanggal yang dipilih
    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text(label) },
        modifier = modifier,
        enabled = true,
        singleLine = true,
        colors = colors,
        trailingIcon = {
            // Ganti tombol dengan ikon kalender
            IconButton(onClick = openDatePicker) {
                Icon(Icons.Filled.CalendarToday, contentDescription = "Pilih Tanggal")
            }
        }
    )
}
