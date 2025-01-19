package com.example.weather.view.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.UIControllers.SettingsViewModel
import java.util.Locale

fun setLocale(context: Context, language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = context.resources.configuration
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}

fun saveLanguagePreference(context: Context, language: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("selected_language", language)
    editor.apply()
}

fun getLanguagePreference(context: Context): String {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("selected_language", "da") ?: "da"
}

@Composable
fun SettingsScreen(
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    SettingsViewModel: SettingsViewModel = viewModel(),
    handleClickBack: () -> Unit = {},
    context: Context = LocalContext.current
){
    val currentLanguage = getLanguagePreference(context)
    val langOptions = listOf("Dansk", "English")
    var isSwitchChecked by remember { mutableStateOf(true) }
    val languageOptions = listOf("Dansk", "English")
    val languageCodeMap = mapOf("Dansk" to "da", "English" to "en")
    val selectedLanguageDisplay = when (currentLanguage) {
        "da" -> "Dansk"
        "en" -> "English"
        else -> "Dansk"
    }
    var selectedLanguage by remember { mutableStateOf(selectedLanguageDisplay) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = { handleClickBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "BackArrow"
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    key(selectedLanguage) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            item {
                                Text(
                                    text = stringResource(R.string.Settings),
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                            }
                            item {
                                SwitchSettingItem(
                                    title = stringResource(R.string.WeatherAlert),
                                    description = stringResource(R.string.EnableDisableAlert),
                                    isChecked = isSwitchChecked,
                                    onCheckedChange = { isSwitchChecked = it }
                                )
                            }
                            item {
                                DropdownSettingItem(
                                    title = stringResource(R.string.Language),
                                    options = langOptions,
                                    selectedOption = selectedLanguage,
                                    onOptionSelected = { newOption ->
                                        selectedLanguage = newOption
                                        val langCode = if (newOption == "Dansk") "da" else "en"
                                        SettingsViewModel.changeLanguage(langCode)
                                        saveLanguagePreference(context, langCode)
                                        setLocale(context, langCode)
                                    }
                                )
                            }
                        }
                    }

                }
            }
        }

    }

}

@Composable
fun SwitchSettingItem(
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                uncheckedThumbColor = Color.Red,
                checkedTrackColor = Color.LightGray,
                uncheckedTrackColor = Color.Gray
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSettingItem(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.ChooseLanguage)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        text = { Text(option) }
                    )
                }
            }
        }
    }
}