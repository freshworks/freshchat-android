package com.example.freshchatsampleappkotlin

import java.util.*

object Utils {
    fun convertStringToList(csv: String?, delimiter: String?): List<String> {
        var convTags = csv
        val tagsList: MutableList<String> = ArrayList()
        if (convTags == null || convTags.trim().isEmpty()) {
            return tagsList
        }
        convTags = convTags.replace(" ", "")
        val values: Array<String> = convTags.split(delimiter!!).toTypedArray()
        tagsList.addAll(listOf(*values))
        return tagsList
    }
}