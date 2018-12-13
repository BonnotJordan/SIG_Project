package com.example.jordan.sig_project

import java.util.*


/**
 * Formats tracker data as KML output
 */
internal class KMLFormatter {

    val header: String
        get() {
            val builder = LineBuilder()
            builder.addLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
            builder.addLine("<kml xmlns=\"http://www.opengis.net/kml/2.2\">")
            builder.addLine("<Document>")
            return builder.toString()
        }

    val footer: String
        get() {
            val builder = LineBuilder()
            builder.addLine("</Document>")
            builder.addLine("</kml>")
            return builder.toString()
        }

    fun getOutput(entry: LinkedList<GeoPoint>): String {
        val builder = LineBuilder()
        builder.addLine("<Folder>")
        for (i in 0 until entry.size -1) {
            builder.addLine("<Placemark>")
            builder.addLine("<name> Line </name>")
            builder.addLine("<LineString>")
            builder.addLine("<coordinates>")
            builder.addLine(entry[i].longitude.toString() + "," + entry[i].latitude.toString())
            builder.addLine(entry[i+1].longitude.toString() + "," + entry[i+1].latitude.toString())
            builder.addLine("</coordinates>")
            builder.addLine("</LineString>")
            builder.addLine("</Placemark>")
        }
        for (i in 0 until entry.size) {
            builder.addLine("<Placemark>")
            builder.addLine("<name>" + entry[i].name + "</name>")
            builder.addLine("<Point>")
            builder.addLine("<coordinates>" + entry[i].longitude + "," + entry[i].latitude + "</coordinates>")
            builder.addLine("</Point>")
            builder.addLine("</Placemark>")
        }
        builder.addLine("</Folder>")
        return builder.toString()
    }

    private class LineBuilder {
        private val mBuilder: StringBuilder

        init {
            mBuilder = StringBuilder()
        }

        fun addLine(line: String) {
            mBuilder.append(line)
            mBuilder.append("\n")
        }

        override fun toString(): String {
            return mBuilder.toString()
        }

    }

}