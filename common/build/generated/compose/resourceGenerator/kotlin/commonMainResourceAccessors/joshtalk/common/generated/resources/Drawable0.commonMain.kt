@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package joshtalk.common.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi

private object CommonMainDrawable0 {
  public val coder: DrawableResource by 
      lazy { init_coder() }
}

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("coder", CommonMainDrawable0.coder)
}

internal val Res.drawable.coder: DrawableResource
  get() = CommonMainDrawable0.coder

private fun init_coder(): DrawableResource = org.jetbrains.compose.resources.DrawableResource(
  "drawable:coder",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/joshtalk.common.generated.resources/drawable/coder.jpeg", -1, -1),
    )
)
