package com.example.tasks.app.data.layout

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import com.google.gson.annotations.SerializedName

class UIDialogModel {
    @DrawableRes
    @SerializedName("icon")
    var icon: Int? = null

    @StringRes
    @SerializedName("title")
    var title: Int? = null

    @SerializedName("title_string")
    var titleStr: String? = null

    @StringRes
    @SerializedName("description")
    var description: Int? = null

    @StringRes
    @SerializedName("descriptionTwo")
    var descriptionTwo: Int? = null

    @SerializedName("description_string")
    var descriptionStr: String? = null

    @StringRes
    @SerializedName("special_warning")
    var specialWarning: Int? = null

    @StyleRes
    @SerializedName("bg_btn_positive")
    var bgPositive: Int? = null

    @StyleRes
    @SerializedName("bg_btn_negative")
    var bgNegative: Int? = null

    @StringRes
    @SerializedName("btnPositive")
    var btnPositive: Int? = null

    @StringRes
    @SerializedName("btn_negative")
    var btnNegative: Int? = null

    constructor()

    constructor(
        icon: Int?, title: Int?, titleStr: String,
        description: Int?, descriptionStr: String,
        descriptionTwo: Int?, specialWarning: Int?,
        bgPositive: Int?, bgNegative: Int?,
        btnPositive: Int?, btnNegative: Int?
    ) {
        this.icon = icon
        this.title = title
        this.titleStr = titleStr
        this.description = description
        this.descriptionStr = descriptionStr
        this.descriptionTwo = descriptionTwo
        this.specialWarning = specialWarning
        this.bgPositive = bgPositive
        this.bgNegative = bgNegative
        this.btnPositive = btnPositive
        this.btnNegative = btnNegative
    }
}