package com.lmqr.ha9_comp_service.button_mapper

import android.content.Context
import com.lmqr.ha9_comp_service.command_runners.CommandRunner
import com.lmqr.ha9_comp_service.command_runners.Commands
import com.lmqr.ha9_comp_service.RefreshModeManager
import android.view.WindowManager
import android.view.Gravity
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.view.View
import android.graphics.Color
import android.content.SharedPreferences
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuff.Mode

class ClearScreenButtonAction(private val commandRunner: CommandRunner) : ButtonAction {
    private lateinit var refreshModeManager: RefreshModeManager
    override fun execute(context: Context) {
        commandRunner.runCommands(arrayOf(Commands.SPEED_CLEAR))
        simulateDisplayRefresh(context)
        // Handler(Looper.getMainLooper()).postDelayed({
        //     simulateDisplayRefresh(context)
        // }, 1000) // Delay in milliseconds (adjust as needed)
        commandRunner.runCommands(arrayOf(Commands.SPEED_FAST))
    }
    private fun simulateDisplayRefresh(context: Context) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        // Create a custom View to draw a full-screen black layer
        val blackOverlayView = View(context).apply {
            setBackgroundColor(Color.BLACK) // Set the background color to black
        }

        val whiteOverlayView = View(context).apply {
            setBackgroundColor(Color.WHITE) // Set the background color to white
        }

        val layoutParams = WindowManager.LayoutParams().apply {
            type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
            format = PixelFormat.OPAQUE // Ensure the layer is solid
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
            gravity = Gravity.TOP or Gravity.START
        }

        // Add the black overlay view to the WindowManager
        windowManager.addView(blackOverlayView, layoutParams)

        // After a short delay, replace the black layer with a white layer
        Handler(Looper.getMainLooper()).postDelayed({
            windowManager.removeView(blackOverlayView) // Remove the black layer
            windowManager.addView(whiteOverlayView, layoutParams) // Add the white layer

            // After another short delay, remove the white layer
            Handler(Looper.getMainLooper()).postDelayed({
                windowManager.removeView(whiteOverlayView)
                // Restore the original display mode here if needed
            }, 100) // Delay for the white layer (adjust as needed)
        }, 100) // Delay for the black layer (adjust as needed)
    }
}