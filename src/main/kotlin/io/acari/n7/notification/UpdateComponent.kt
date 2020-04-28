package io.acari.n7.notification

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.ProjectManagerListener
import io.acari.n7.config.ConfigurationPersistence

const val VERSION = "v1.8.1"
val UPDATE_MESSAGE =
  """
      What's New?<br>
      <ul>
      <li>Fixed settings in 2020.1-EAP.
            <ul><li>Thanks for reporting the issue!</li></ul></li>
      <br>
      Thanks again for downloading <b>Normandy Progress Bar UI</b>! •‿•<br>
          <br>See <a href="https://github.com/Unthrottled/normandy-progress-bar/blob/master/docs/CHANGELOG.md">Changelog</a> for more details.
        """.trimIndent()

class UpdateComponent : Disposable {

  private val connection = ApplicationManager.getApplication().messageBus.connect()

  init {
    connection.subscribe(ProjectManager.TOPIC, object : ProjectManagerListener {
      override fun projectOpened(project: Project) {
        ConfigurationPersistence.instance
          .filter { it.version != VERSION }
          .ifPresent { config ->
            config.version = VERSION
            NotificationService.showUserUpdates(project)
          }
      }
    })
  }

  override fun dispose() {
    connection.dispose()
  }
}
