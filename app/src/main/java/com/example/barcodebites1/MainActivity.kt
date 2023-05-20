package com.example.barcodebites1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import android.annotation.SuppressLint as SuppressLint1


@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @SuppressLint1("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(
                    bottomBar = { Toolbar1() }
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MainActivity()
}
@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
data class FoodPreference(val name: String, var isSelected: Boolean)

data class AccountSetting(val name: String)

@Composable
fun ProfileScreen() {
    val profileViewModel: ProfileViewModel = viewModel()
    val selectedFoodPreferences = profileViewModel.selectedFoodPreferences

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Profile",
            style = typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Food Preferences",
            style = typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        profileViewModel.foodPreferences.forEach { preference ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = selectedFoodPreferences.contains(preference),
                    onCheckedChange = {
                        profileViewModel.selectedFoodPreferences.apply {
                            if (it) add(preference)
                            else remove(preference)
                        }
                    }
                )
                Text(
                    text = preference.name,
                    style = typography.bodyMedium,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Account Settings",
            style = typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        profileViewModel.accountSettings.forEach { setting ->
            Text(
                text = setting.name,
                style = typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { profileViewModel.saveProfile() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun Toolbar1() {
    BottomAppBar(
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp)
                )

                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp)
                )

                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Scan",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp)
                )
            }
        }
    )
}



class ProfileViewModel : ViewModel() {
    val foodPreferences = listOf(
        FoodPreference("Vegan", false),
        FoodPreference("Vegetarian", false),
        FoodPreference("Muslim", false),
        FoodPreference("Kosher", false)
    )

    val accountSettings = listOf(
        AccountSetting("Setting 1"),
        AccountSetting("Setting 2"),
        AccountSetting("Setting 3")
    )

    val selectedFoodPreferences = mutableStateListOf<FoodPreference>()

    fun saveProfile() {
        // TODO: Implement saving the profile data
    }
}




