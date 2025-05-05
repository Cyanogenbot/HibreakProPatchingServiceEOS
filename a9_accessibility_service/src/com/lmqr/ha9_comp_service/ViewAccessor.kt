package com.lmqr.ha9_comp_service

import android.view.View
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.content.Context
import android.view.MotionEvent
import android.widget.CompoundButton
import androidx.core.content.ContextCompat

/**
 * This class replaces the generated data binding class for floating_menu_layout.xml
 * and provides direct access to the views in the layout.
 */
class FloatingMenuViewAccessor(val root: View) {
    // Button views for refresh modes
    val button1: Button = root.findViewById(R.id.button1)
    val button2: Button = root.findViewById(R.id.button2)
    val button3: Button = root.findViewById(R.id.button3)
    val button4: Button = root.findViewById(R.id.button4)
    
    // CheckBox views
    val autoRefresh: CheckBox = root.findViewById(R.id.autoRefresh)
    val antiShake: CheckBox = root.findViewById(R.id.antiShake)
    
    // Button views for opacity modes
    val buttonTransparent: Button = root.findViewById(R.id.buttonTransparent)
    val buttonSemiTransparent: Button = root.findViewById(R.id.buttonSemiTransparent)
    val buttonSemiOpaque: Button = root.findViewById(R.id.buttonSemiOpaque)
    val buttonOpaque: Button = root.findViewById(R.id.buttonOpaque)
    
    // Static AOD views
    val staticAodText: TextView = root.findViewById(R.id.staticAodText)
    val staticAodLinearLayout: LinearLayout = root.findViewById(R.id.staticAodLinearLayout)
    
    // Other views
    val settingsIcon: View = root.findViewById(R.id.settingsIcon)
    val enableReaderModeText: TextView = root.findViewById(R.id.enableReaderModeText)
    val lightSeekbar: SeekBar = root.findViewById(R.id.lightSeekbar)
    val lightWarmSeekbar: SeekBar = root.findViewById(R.id.lightWarmSeekbar)
    
    companion object {
        /**
         * Factory method to inflate and create a new FloatingMenuViewAccessor
         */
        fun inflate(inflater: LayoutInflater): FloatingMenuViewAccessor {
            val view = inflater.inflate(R.layout.floating_menu_layout, null)
            return FloatingMenuViewAccessor(view)
        }
    }
}

/**
 * Extension functions to replace original data binding methods
 */

/**
 * Closes the floating menu by setting its visibility to GONE
 */
fun FloatingMenuViewAccessor?.close() = this?.run {
    root.visibility = View.GONE
}

/**
 * Updates the refresh mode buttons to reflect the current mode
 * @param mode The current refresh mode
 */
fun FloatingMenuViewAccessor?.updateButtons(mode: RefreshMode) = this?.run {
    // Reset all buttons
    listOf(button1, button2, button3, button4).forEach { 
        it.isSelected = false 
    }
    
    // Highlight the selected button
    when (mode) {
        RefreshMode.CLEAR -> button1
        RefreshMode.BALANCED -> button2
        RefreshMode.SMOOTH -> button3
        RefreshMode.SPEED -> button4
    }?.isSelected = true
}

/**
 * Updates the AOD opacity buttons to reflect the current mode and reader status
 * @param mode The current AOD opacity mode
 * @param isReader Whether reader mode is active
 */
fun FloatingMenuViewAccessor?.updateButtons(mode: AODOpacity, isReader: Boolean) = this?.run {
    // Update reader mode text
    enableReaderModeText.setIsReader(isReader)
    
    // Reset all opacity buttons
    listOf(buttonTransparent, buttonSemiTransparent, buttonSemiOpaque, buttonOpaque).forEach { 
        it.isSelected = false 
    }
    
    // Highlight the selected button
    when (mode) {
        AODOpacity.CLEAR -> buttonTransparent
        AODOpacity.SEMICLEAR -> buttonSemiTransparent
        AODOpacity.SEMIOPAQUE -> buttonSemiOpaque
        AODOpacity.OPAQUE -> buttonOpaque
        AODOpacity.NOTSET -> null // No button selected for NOTSET
    }?.isSelected = true
}

/**
 * Extension function to set reader mode on a TextView
 * This implementation replicates the original functionality from the A9AccessibilityService
 */
fun TextView.setIsReader(isReader: Boolean) {
    // Set the text based on reader mode state
    text = if (isReader) "Reader Mode: ON" else "Reader Mode: OFF"
    
    // Set text color based on reader mode state
    val colorRes = if (isReader) R.color.reader_mode_on else R.color.black
    setTextColor(ContextCompat.getColor(context, colorRes))
}