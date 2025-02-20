package freeapp.life.freeaiweb.view.component

import freeapp.life.freeaiweb.util.path
import kotlinx.html.*
import kotlinx.html.stream.createHTML


fun errorAlertView(
   msg:String
): String {
    return createHTML().div("flex items-center p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400")  {
            role = "alert"
            svg("shrink-0 inline w-4 h-4 me-3") {
                attributes["aria-hidden"] = "true"
                attributes["fill"] = "currentColor"
                attributes["viewbox"] = "0 0 20 20"
                path {
                    attributes["d"] =
                        "M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"
                }
            }
            span("sr-only") { +"""Info""" }
            div {
                span("font-medium") { +"""Danger alert! """ }
                +msg
            }
    }
}
