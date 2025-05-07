package com.lmqr.ha9_comp_service

import android.view.View
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Switch
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.content.Context
import android.widget.CompoundButton
import androidx.core.content.ContextCompat

/**
 * This class replaces the generated data binding class for floating_menu_layout.xml
 * and provides direct access to the views in the layout.
 */
class FloatingMenuViewAccessor(val root: View) {
    val button1: Button = root.findViewById(R.id.button1)
    val button2: Button = root.findViewById(R.id.button2)
    val button3: Button = root.findViewById(R.id.button3)
    val button4: Button = root.findViewById(R.id.button4)
    
    val autoRefresh: Switch = root.findViewById(R.id.auto_refresh)
    val antiShake: Switch = root.findViewById(R.id.anti_shake)
     
    val buttonTransparent: Button = root.findViewById(R.id.button_transparent)
    val buttonSemiTransparent: Button = root.findViewById(R.id.button_semi_transparent)
    val buttonSemiOpaque: Button = root.findViewById(R.id.button_semi_opaque)
    val buttonOpaque: Button = root.findViewById(R.id.button_opaque)
    
    val staticAodText: LinearLayout = root.findViewById(R.id.static_aod_text)
    val staticAodLinearLayout: LinearLayout = root.findViewById(R.id.static_aod_linear_layout)
    
    val settingsIcon: View = root.findViewById(R.id.settings_icon)
    val enableReaderModeText: TextView = root.findViewById(R.id.enable_reader_mode_text)
    val lightSeekbar: SeekBar = root.findViewById(R.id.light_seekbar)
    val lightWarmSeekbar: SeekBar = root.findViewById(R.id.light_warm_seekbar)
    
    companion object {
        fun inflate(inflater: LayoutInflater): FloatingMenuViewAccessor {
            val view = inflater.inflate(R.layout.floating_menu_layout, null)
            return FloatingMenuViewAccessor(view)
        }
    }
}

fun FloatingMenuViewAccessor?.close() = this?.run {
    root.visibility = View.GONE
}

fun FloatingMenuViewAccessor?.updateButtons(mode: RefreshMode) = this?.run {
    listOf(button1, button2, button3, button4).forEach { 
        it.deselect()
    }
    
    when (mode) {
        RefreshMode.CLEAR -> button1.select()
        RefreshMode.BALANCED -> button2.select()
        RefreshMode.SMOOTH -> button3.select()
        RefreshMode.SPEED -> button4.select()
    }
}

fun FloatingMenuViewAccessor?.updateButtons(mode: AODOpacity, isReader: Boolean) = this?.run {
    enableReaderModeText.setIsReader(isReader)
    
    listOf(buttonTransparent, buttonSemiTransparent, buttonSemiOpaque, buttonOpaque).forEach { 
        it.isSelected = false 
    }
    
    when (mode) {
        AODOpacity.CLEAR -> buttonTransparent
        AODOpacity.SEMICLEAR -> buttonSemiTransparent
        AODOpacity.SEMIOPAQUE -> buttonSemiOpaque
        AODOpacity.OPAQUE -> buttonOpaque
        AODOpacity.NOTSET -> null
    }?.isSelected = true
}
 
fun TextView.setIsReader(isReader: Boolean) {
    text = if (isReader) "Reader Mode: ON" else "Reader Mode: OFF"
    
    val colorRes = if (isReader) R.color.reader_mode_on else R.color.black
    setTextColor(ContextCompat.getColor(context, colorRes))
}