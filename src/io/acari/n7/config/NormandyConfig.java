package io.acari.n7.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@State(
    name = "NormandyConfig",
    storages = @Storage("normandy_theme.xml")
)
public class NormandyConfig implements PersistentStateComponent<NormandyConfig>, Cloneable {

  // They are public so they can be serialized
  public String primaryThemeColor = "#FFFFFF";
  public String secondaryThemeColor = "#000000";
  public String jetWash = "#a38dbe,#d6f5f8,#a38dbe,#d6f5f8,#a38dbe,TRANSPARENT,TRANSPARENT,#a38dbe,#d6f5f8,#a38dbe,#d6f5f8,#a38dbe";
  public String borderColor = "#EFEFEF";
  public boolean allowedToBeOverridden = false;


  public NormandyConfig() {
  }

  public static Optional<NormandyConfig> getInstance() {
    return Optional.ofNullable(ServiceManager.getService(NormandyConfig.class));
  }

  public String getPrimaryThemeColor() {
    return primaryThemeColor;
  }

  public void setPrimaryThemeColor(String primaryThemeColor) {
    this.primaryThemeColor = primaryThemeColor;
  }

  public String getSecondaryThemeColor() {
    return secondaryThemeColor;
  }

  public void setSecondaryThemeColor(String secondaryThemeColor) {
    this.secondaryThemeColor = secondaryThemeColor;
  }

  public String getJetWash() {
    return jetWash;
  }

  public void setJetWash(String jetWash) {
    this.jetWash = jetWash;
  }

  public String getBorderColor() {
    return borderColor;
  }

  public void setBorderColor(String borderColor) {
    this.borderColor = borderColor;
  }

  public boolean isAllowedToBeOverridden() {
    return allowedToBeOverridden;
  }

  public void setAllowedToBeOverridden(boolean allowedToBeOverridden) {
    this.allowedToBeOverridden = allowedToBeOverridden;
  }

  @Override
  public Object clone() {
    return XmlSerializerUtil.createCopy(this);
  }


  /**
   * Convenience method to reset settings
   */
  public void resetSettings() {
    primaryThemeColor = "";
    secondaryThemeColor = "";
    jetWash = "";
    borderColor = "";
  }


  /**
   * Get the state of MTConfig
   */
  @Nullable
  @Override
  public NormandyConfig getState() {
    return this;
  }

  /**
   * Load the state from XML
   *
   * @param state the MTConfig instance
   */
  @Override
  public void loadState(@NotNull final NormandyConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }


}
