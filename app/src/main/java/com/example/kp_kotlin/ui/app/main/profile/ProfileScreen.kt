
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kp_kotlin.R
import com.example.kp_kotlin.ui.navigation.NavigationDestination
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

object ProfileDestination : NavigationDestination {
    override val title = "Профиль"
    override val route = "profile"
}

@Composable
fun ProfileScreen(
    navigateToAbout: () -> Unit,
    navigateToReg: () -> Unit,
    navigateToEdit: (Int) -> Unit
) {
    val auth = Firebase.auth
    val name = auth.currentUser?.displayName
    val email = auth.currentUser?.email

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kolpak),
            contentDescription = "Аватарка",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape) // Делает изображение круглым
                .border(2.dp, Color.Gray, CircleShape)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
            Text(
                text =
                if (name != null) name
                 else "Имя пользователя",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.six))

            )


        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (email != null) email
            else "user@example.com",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))
        ProfileCardItem(title = "Создать рецепт") { navigateToEdit(0) }

        Spacer(modifier = Modifier.height(8.dp))
        ProfileCardItem(title = "О нас", navigateToAbout)

        Spacer(modifier = Modifier.height(8.dp))
        ProfileCardItem(title = "Выйти") {
            singOut(auth)
            navigateToReg()
        }
    }
}

@Composable
fun ProfileCardItem(title: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp,
        color = Color.White,
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.three))
            )
        }
    }
}

private fun singOut(auth: FirebaseAuth){
    auth.signOut()
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
ProfileScreen(
    navigateToEdit = {},
    navigateToAbout = {},
    navigateToReg = {}
)
}


