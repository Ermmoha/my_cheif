package com.example.kp_kotlin.ui.app.other.edit

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import coil.compose.rememberAsyncImagePainter
import com.example.kp_kotlin.R
import com.example.kp_kotlin.ui.app.other.edit.EditDestination.cardId
import com.example.kp_kotlin.ui.navigation.NavigationDestination
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object EditDestination : NavigationDestination {
    var cardId = 0
    override val title = "Редактирование"
    override val route = "Edit"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeEditScreen(
    viewModel: RecipeEditViewModel,
    navigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val categories = listOf("Завтраки", "Первые блюда", "Вторые блюда", "Салаты","Десерты", "Напитки")
    var expanded by remember { mutableStateOf(false) }
    var context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val filePath = saveImageToInternalStorage(context, uri, "recipe_image_${System.currentTimeMillis()}.jpg")
            filePath?.let { viewModel.updateState(state.recipe.copy(imageUri = it)) }
        }
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 3.dp)
    ) {
        val imageFile = File(state.recipe.imageUri)
        item {
            Text(
                text = "Фотография готового блюда",
                fontFamily = FontFamily(Font(R.font.four)),
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray)
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imageFile.exists()) {
                    Image(
                        painter = rememberAsyncImagePainter(model = Uri.fromFile(imageFile)),
                        contentDescription = "Фото блюда",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Upload Photo",
                            tint = colorResource(id = R.color.purple_500)
                        )
                        Text(
                            text = "Загрузить фото",
                            color = colorResource(id = R.color.purple_500)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Основная информация ",
                fontFamily = FontFamily(Font(R.font.four))
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.four)),
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                value = state.recipe.firstName,
                onValueChange = { viewModel.updateState(state.recipe.copy(firstName = it)) },
                label = {
                    Text(text = "Название рецепта ")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Описание рецепта
            OutlinedTextField(
                value = state.recipe.description,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                onValueChange = { viewModel.updateState(state.recipe.copy(description = it)) },
                label = { Text("Описание рецепта ") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = state.recipe.category,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { viewModel.updateState(state.recipe.copy(category = it)) },
                    readOnly = true,
                    label = { Text("Категория") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(text = category) },
                            onClick = {
                                viewModel.updateState(state.recipe.copy(category = category))
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            // Время приготовления
            Text(
                text = "Время приготовления",
                fontFamily = FontFamily(Font(R.font.four)),
            )
            Spacer(modifier = Modifier.height(8.dp))


            Spacer(modifier = Modifier.height(16.dp))

            // Активное время готовки
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = state.recipe.cookingHours,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {
                        if(it.isDigitsOnly() && it.length <= 2)
                            viewModel.updateState(state.recipe.copy(cookingHours = it))
                    },
                    label = { Text("Часы ") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = state.recipe.cookingMinutes,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {
                        if (it.isDigitsOnly() && it.length <= 2)
                            viewModel.updateState(state.recipe.copy(cookingMinutes = it)) },
                    label = { Text("Минуты ") },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            // Ингредиенты
            Text(
                text = "Ингредиенты",
                fontFamily = FontFamily(Font(R.font.four)),
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.recipe.ingredients,
                onValueChange = { viewModel.updateState(state.recipe.copy(ingredients = it)) },
                label = { Text("Ингредиенты") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Инструкция приготовления",
                fontFamily = FontFamily(Font(R.font.four)),
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.recipe.steps,
                onValueChange = { viewModel.updateState(state.recipe.copy(steps = it)) },
                label = { Text("Описание шага") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.insertNewRecipe()
                    navigateBack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.dark_green))
            ) {
                Text(
                    text = if (cardId != 0)"Сохранить изменения" else "Создать рецепт",
                    fontFamily = FontFamily(Font(R.font.six)),
                    fontSize = 20.sp,
                )
            }
        }
    }
}

fun saveImageToInternalStorage(context: Context, uri: Uri, fileName: String): String? {
    try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val file = File(context.filesDir, fileName)
        val outputStream = FileOutputStream(file)

        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

