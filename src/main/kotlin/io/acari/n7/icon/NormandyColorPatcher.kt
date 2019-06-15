package io.acari.n7.icon

import com.intellij.util.SVGLoader.SvgColorPatcher
import io.acari.n7.theme.NormandyTheme
import org.w3c.dom.Element

class NormandyColorPatcher(private val otherColorPatcher: (Element) ->Unit = {} ) : SvgColorPatcher {

  override fun patchColors(svg: Element) {
    otherColorPatcher(svg)
    patchChildren(svg)
  }

  private fun patchChildren(svg: Element) {
    val themedPrimaryAttribute = svg.getAttribute("themedPrimary")
    val themedSecondaryAttribute = svg.getAttribute("themedSecondary")

    when(themedPrimaryAttribute){
      "fill"-> svg.setAttribute("fill", NormandyTheme.primaryColorString())
      "stroke"-> svg.setAttribute("stroke", NormandyTheme.primaryColorString())
      "both"-> {
        svg.setAttribute("stroke", NormandyTheme.primaryColorString())
        svg.setAttribute("fill", NormandyTheme.primaryColorString())
      }
    }

    when(themedSecondaryAttribute){
      "fill"-> svg.setAttribute("fill", NormandyTheme.secondaryColorString())
      "stroke"-> svg.setAttribute("stroke", NormandyTheme.secondaryColorString())
      "both"-> {
        svg.setAttribute("stroke", NormandyTheme.secondaryColorString())
        svg.setAttribute("fill", NormandyTheme.secondaryColorString())
      }
    }

    val nodes = svg.childNodes
    val length = nodes.length
    for (i in 0 until length) {
      val item = nodes.item(i)
      if (item is Element) {
        patchColors(item)
      }
    }
  }
}