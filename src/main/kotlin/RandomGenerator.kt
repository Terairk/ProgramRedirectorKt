// ProgramA
import kotlin.random.Random

fun main() {
    val random = Random(System.currentTimeMillis())

    while (true) {
        try {
            val input = readlnOrNull() ?: break

            when (input.trim()) {
                "Hi" -> println("Hi")
                "GetRandom" -> println(random.nextInt())
                "Shutdown" -> {
                    println("Goodbye")
                    break
                }
            }
            System.out.flush()
        } catch (e: Exception) {
            System.err.println("Error: ${e.message}")
            break
        }
    }
}