import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kbtu.dukenapp.R
import com.kbtu.dukenapp.presentation.features.profile.ui.isValidEmail
import com.kbtu.dukenapp.ui.theme.LightBlueBackground

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    onSignInClick: (String, String) -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

    val isEmailValid = isValidEmail(email.value)

    Scaffold(
        containerColor = LightBlueBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                singleLine = true,
                isError = !isEmailValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            if (!isEmailValid && email.value.isNotEmpty()) {
                Text(
                    text = "Invalid email address",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(
                            painter = painterResource(id = if (passwordVisibility.value) R.drawable.hide else R.drawable.eye),
                            contentDescription = "Toggle Password Visibility",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Button(
                onClick = { onSignInClick(email.value, password.value) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(48.dp),
                shape = MaterialTheme.shapes.medium,
                enabled = isEmailValid
            ) {
                Text(text = "Sign In", fontSize = 16.sp)
            }

            TextButton(
                onClick = navigateToSignUp,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Don't have an account? Sign up",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
