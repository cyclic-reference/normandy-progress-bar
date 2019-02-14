package io.acari.n7

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.BaseComponent
import com.intellij.openapi.util.IconLoader
import com.intellij.util.SVGLoader
import com.intellij.util.messages.MessageBusConnection
import io.acari.n7.config.*

/**
 * Forged in the flames of battle by alex.
 */
class NormandyIconComponent : BaseComponent {

  companion object {
    private val colorPatcher = NormandyColorPatcher()

    init {
      setColorPatcher()
    }

    fun getNormandyIcon() = IconLoader.getIcon("/normandy.svg")
    fun getNormandyToCitadelIcon() = IconLoader.getIcon("/normandyToCitadel.svg")

    private fun setColorPatcher() {
      SVGLoader.setColorPatcher(colorPatcher)
    }
  }

  lateinit var messageBus: MessageBusConnection

  override fun initComponent() {

    messageBus = ApplicationManager.getApplication().messageBus.connect()
    messageBus.subscribe(CONFIGURATION_TOPIC, NormandyConfigurationSubcriber {
      setColorPatcher()
      //todo: set color patcher back.
    })

    //todo: should just put in the laf theme changes listener instead for now.
    messageBus.subscribe(DOKI_DOKI_THEME_TOPIC, DokiDokiThemeSubcriber {
      println("Doki Doki theme changed")
    })
  }

  override fun disposeComponent() {
    if (this::messageBus.isInitialized) {
      messageBus.disconnect()
    }
  }
}


