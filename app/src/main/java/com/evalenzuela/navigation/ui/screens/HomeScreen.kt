package com.evalenzuela.navigation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.evalenzuela.navigation.R
import com.evalenzuela.navigation.data.model.Favorite
import com.evalenzuela.navigation.data.model.Item
import com.evalenzuela.navigation.navigation.Routes
import com.evalenzuela.navigation.ui.viewmodel.FavoriteViewModel
import com.evalenzuela.navigation.ui.viewmodel.MainViewModel
import androidx.compose.ui.graphics.Brush
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    favoriteViewModel: FavoriteViewModel,
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val items by mainViewModel.items.collectAsState()
    val isLoading by mainViewModel.isLoading.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    // Filtrar recetas por búsqueda
    val filteredItems = remember(items, searchQuery) {
        if (searchQuery.isBlank()) {
            items
        } else {
            items.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // Imagen de fondo que ocupa toda la pantalla
        Image(
            painter = painterResource(id = R.drawable.high_quality_pumpkin_pumpkin_soup),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay oscuro para mejorar legibilidad
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.3f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Blog de comida",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de búsqueda funcional
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Búsqueda de comida", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.navigate(Routes.ADD_RECIPE) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar receta")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar loading
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else if (filteredItems.isEmpty() && searchQuery.isNotBlank()) {
                // No hay resultados de búsqueda
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No se encontraron recetas con '$searchQuery'",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            } else if (filteredItems.isEmpty()) {
                // No hay recetas en la BD
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No hay recetas. ¡Agrega una nueva!",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            } else {
                // Mostrar recetas
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    filteredItems.forEach { item ->
                        RecipeCard(
                            item = item,
                            favoriteViewModel = favoriteViewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeCard(
    item: Item,
    favoriteViewModel: FavoriteViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (item.imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(item.imageUri),
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Sin imagen",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(item.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { showDialog = true }) {
                Text("Ver receta")
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    // Agregar a favoritos usando la API
                    favoriteViewModel.addFavoriteToApi(
                        title = item.title,
                        descripcion = item.description
                    )
                    showDialog = false
                }) {
                    Text("Añadir a favoritos")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            },
            title = { Text(item.title) },
            text = {
                Column {
                    Text("Ingredientes:", fontWeight = FontWeight.Bold)
                    Text(item.description)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Preparación:", fontWeight = FontWeight.Bold)
                    Text(item.preparacion)
                }
            }
        )
    }
}