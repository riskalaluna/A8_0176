package com.example.a8_176.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a8_176.R
import com.example.a8_176.ui.navigation.DestinasiNavigasi


object DestinasiHomeUtama : DestinasiNavigasi {
    override val route = "homeutama"
    override val titleRes: String
        get() = TODO("Not yet implemented")
}

@Composable
fun HomeUtamaView(
    onMahasiswa: () -> Unit,
    onKamar: () -> Unit,
    onBangunan: () -> Unit,
    onPembayaranSewa: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFF2196F3)  // Ganti dengan warna biru kustom
            ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.padding(16.dp))

        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.logoumy
                ),
                contentDescription = " ",
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.padding(start = 16.dp))
            Column {
                Text(
                    text = "Asrama Universitas Muhammadiyah Yogyakarta",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Bersih dan Nyaman",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 16.dp))
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topEnd = 15.dp,
                        topStart = 15.dp
                    )
                )
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ASRAMA",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    fontSize = 22.sp
                )
                Spacer(
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = "Jadikan Asramamu Rumah Keduamu",
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Spacer(
                    modifier = Modifier
                        .padding(16.dp)
                )
                Button(
                    onClick = {
                        onMahasiswa()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = "Mahasiswa")
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = {
                        onKamar()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = "Kamar")
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = {
                        onBangunan()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = "Bangunan")
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = {
                        onPembayaranSewa()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = "Pembayaran Sewa")
                }
            }
        }
    }
}