package com.phdteam.historyverse.ui.components

import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.phdteam.historyverse.ui.theme.BackgroundLight

fun setStatusBarColor(
    statusBarColor: Color = BackgroundLight,
    navigationBarColor: Color = BackgroundLight,
    systemUIController: SystemUiController,
    isDarkIcon: Boolean = true
) {
    systemUIController.setStatusBarColor(statusBarColor, darkIcons = isDarkIcon)
    systemUIController.setNavigationBarColor(navigationBarColor, darkIcons = isDarkIcon)
}