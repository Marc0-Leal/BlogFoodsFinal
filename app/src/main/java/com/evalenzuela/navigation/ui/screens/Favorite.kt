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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.evalenzuela.navigation.R
import com.evalenzuela.navigation.ui.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel) {
    val favoriteRecipes = favoriteViewModel.favoriteRecipes

    Box(modifier = Modifier.fillMaxSize()) {

        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.high_quality_pumpkin_pumpkin_soup),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay oscuro
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.7f),
                            Color.Black.copy(alpha = 0.5f)
                        )
                    )
                )
        )

        // Contenido
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tus recetas favoritas",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (favoriteRecipes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Aún no tienes recetas favoritas.\n¡Agrega algunas desde el inicio!",
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else {
                favoriteRecipes.forEach { name ->
                    FavoriteCard(name, favoriteViewModel)
                }
            }
        }
    }
}

@Composable
fun FavoriteCard(name: String, favoriteViewModel: FavoriteViewModel) {
    var showDialog by remember { mutableStateOf(false) }

    val imageRes = when (name) {
        "Sopaipillas" -> R.drawable.sopaipillas
        "Empanadas de queso" -> R.drawable.empanadas_quartirolo_ig01
        "Pastel de choclo" -> R.drawable.pastel_de_choclo_chileno_1
        else -> R.drawable.ic_launcher_foreground
    }

    val recipeText = when (name) {
        "Sopaipillas" -> "Ingredientes:\n- 2 tazas de harina\n- 1 taza de zapallo cocido\n- 1 cda de manteca\n- Sal a gusto\n\nPreparación:\n1. Mezclar el zapallo con la harina y la manteca.\n2. Amasar, estirar y cortar.\n3. Freír hasta dorar."
        "Empanadas de queso" -> "Ingredientes:\n- 2 tazas de harina\n- 100 g de manteca\n- Queso para rellenar\n\nPreparación:\n1. Preparar la masa.\n2. Rellenar con queso.\n3. Freír o hornear hasta dorar."
        "Pastel de choclo" -> "Ingredientes:\n- 6 choclos molidos\n- 500 g de carne molida\n- 2 cebollas\n- 3 huevos duros\n- Aceitunas\n\nPreparación:\n1. Cocinar la carne con cebolla.\n2. Poner en fuente con el pino y cubrir con choclo molido.\n3. Hornear hasta dorar."
        else -> "Receta no disponible."
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { showDialog = true }) {
                Text("Ver receta")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                favoriteViewModel.removeFavorite(name)
            }) {
                Text("Quitar de favoritos", color = Color.Red)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            },
            title = {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            },
            text = {
                Text(recipeText, fontSize = 16.sp)
            }
        )
    }
}