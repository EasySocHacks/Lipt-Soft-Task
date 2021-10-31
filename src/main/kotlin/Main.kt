import search.IntellijIdeaIshSearcher
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val usage = "Usage: Main.kt -f <filename> -p <pattern>";

    var filename: String? = null
    var pattern: String? = null

    var command: String? = null
    for (i in args.indices) {
        val arg = args[i]

        when (command) {
            "filename" -> filename = arg
            "pattern" -> pattern = arg
            else ->
                if (i != args.size - 1) {
                    command = when (arg) {
                        "-f" -> "filename"
                        "--filename" -> "filename"
                        "-p" -> "pattern"
                        "--pattern" -> "pattern"
                        else -> {
                            println(usage)
                            exitProcess(0)
                        }
                    }

                    continue
                } else {
                    println(usage)
                    exitProcess(0)
                }
        }

        command = null
    }

    if (filename == null || pattern == null) {
        println(usage)
        exitProcess(0)
    }

    val classes: MutableList<String> = mutableListOf()

    File(filename).forEachLine {
        classes.add(it)
    }

    val intellijIdeaIshSearcher = IntellijIdeaIshSearcher(classes)

    println(intellijIdeaIshSearcher.search(pattern))
}