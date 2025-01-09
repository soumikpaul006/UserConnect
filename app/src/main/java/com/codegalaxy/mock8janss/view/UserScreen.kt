import android.graphics.Paint.Align
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codegalaxy.mock8janss.UiState
import com.codegalaxy.mock8janss.model.dto.User
import com.codegalaxy.mock8janss.viewmodel.UserViewModel


@Composable
fun UserScreen(
    viewModel:UserViewModel
){
    val uiState by viewModel.uiState.collectAsState()
    val context= LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        when(val state=uiState)
        {
            is UiState.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text=state.message)

                    Spacer(modifier = Modifier.height(26.dp))

                    Button(
                        onClick = {
                            viewModel.fetchUsers()
                            Toast.makeText(context, "Refreshing...", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Retry")
                    }

                }

            }
            UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is UiState.Success -> {

                LazyColumn{
                   items(
                       count = state.data.size,
                       key = {idx->
                           state.data[idx].id
                       }
                   ){ index->
                       UserItem(user = state.data[index])
                   }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Row (
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth(),
        ){
            AsyncImage(
                model=user.image,
                contentDescription = "User Image",
                modifier = Modifier
                    .size(60.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
            ) {

                Text(
                    text = "${user.first_name} ${user.last_name}",
                    style=MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text=user.address,
                    style=MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

