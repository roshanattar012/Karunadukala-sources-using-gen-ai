package com.karunada.kala.ui.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.karunada.kala.data.HeritageSite

data class SiteClusterItem(
    val site: HeritageSite
) : ClusterItem {
    override fun getPosition(): LatLng = site.location
    override fun getTitle(): String = site.name
    override fun getSnippet(): String = site.type
    override fun getZIndex(): Float? = 0f
}
