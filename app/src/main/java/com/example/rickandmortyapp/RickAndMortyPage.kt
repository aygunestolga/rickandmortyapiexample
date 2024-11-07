package com.example.rickandmortyapp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyapp.api.NetworkResponse
import com.example.rickandmortyapp.api.RickAndMortyModel


/* Bu sayfada görüntüyü tasarlıycaz
    Servisten veri geliyor mu
    Servisten veri geliyorsa bekleme süresinde ekrana CircularProgesIndicator koyma
    Gelen veriyi ekranda gösterme
 */

@Composable
fun RickAndMortyPage(viewModel: RickAndMortyViewModel) {
    val result = viewModel.result.observeAsState()

    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "List for Characters to Click Search Icon",
                    Modifier.background(Color.Red)
                )
                Spacer(modifier = Modifier.height(5.dp))
                IconButton(
                    onClick = {viewModel.getData()},
                    modifier = Modifier.padding(8.dp),

                    ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "List",
                        Modifier.background(
                            Color.Cyan
                        )
                    )
                }
            }
        }
        when(val sonuc = result.value){
            is NetworkResponse.Error ->{
                Text(text = sonuc.message)
            }
            NetworkResponse.Loading ->{
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                RickAndMortyDetails(data = sonuc.data)
            }
                null -> {}
        }
    }
}

@Composable
fun RickAndMortyDetails(data : RickAndMortyModel){

    Column {
        Row {
            Column {
                Text(text = "Count : ${data.info.count}")
                Text(text = "Pages : ${data.info.pages}")
                Text(text = "Next : ${data.info.next}")
                Text(text = "Prev : ${data.info.prev}")
            }

        }
    }


}

