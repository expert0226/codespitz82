val cleanUp = """[^.\d-+*\/]""".toRegex()
val mulDiv = """((?:\+-)?[.\d]+)([*\/])((?:\+-)?[.\d]+)""".toRegex()
val paren = """\(([^()]*)\)""".toRegex()

fun ex(v: String): Double {
    val v1 = v.replace(cleanUp, "")

    val v2 = v1.replace("-", "+-")

    val v3 = v2.replace(mulDiv) {
        val (_, left, op, right) = it.groupValues
        val l = left.replace("+", "").toDouble()
        val r = right.replace("+", "").toDouble()
        "${if(op == "*") l * r else l / r}".replace("-", "+-")
    }

    val v4 = v3.split('+')

    val v5 = v4.fold(0.0) { acc, x -> acc + if(x.isBlank()) 0.0 else x.toDouble() }

    return v5
}

fun calc(v: String): Double {
    var r = v
    while(paren.containsMatchIn(r)) r = r.replace(paren) { "${ex(it.groupValues[1])}" }
    return ex(r)
}

//fun calc(v: String) = eval("-2 * -3 - 0.4 / -0.2")

/*
-2 * -3 + 0.4 / -0.2
-2 * ((-3 + 0.4) / -0.2)


error: -2 * -3 - 0.4 / -0.2
*/