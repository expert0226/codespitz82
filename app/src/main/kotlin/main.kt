import kotlin.browser.document
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.HTMLInputElement

fun main() {
    app()
}

fun app() {
    document.querySelector("#base")?.innerHTML = """<input id="input"/><div id="result"></div>"""

    document.querySelector("#input")?.addEventListener("keyup", {
        if((it as KeyboardEvent).keyCode != 13) return@addEventListener

        val input = it.target as HTMLInputElement
        val v = input.value
        document.querySelector("#result")?.innerHTML = "$v = ${calc(v)}"
        input.value = ""
    })
}