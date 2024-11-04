// Controller.kt
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.roundToInt

private const val NUM_SIZE = 100

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please provide the path to Program A as an argument")
        return
    }

    try {
        // Start Program A as a separate process
        val process = ProcessBuilder("java", "-jar", args[0])
            .redirectErrorStream(true)
            .start()

        // Set up communication channels
        val processInput = PrintWriter(process.outputStream, true)
        val processOutput = BufferedReader(InputStreamReader(process.inputStream))

        // Test initial communication
        processInput.println("Hi")
        val response = processOutput.readLine()
        if (response != "Hi") {
            throw Exception("Invalid response to Hi command: $response")
        }

        // Collect random numbers
        val numbers = mutableListOf<Int>()
        repeat(NUM_SIZE) {
            processInput.println("GetRandom")
            val number = processOutput.readLine()?.toIntOrNull()
            if (number != null) {
                numbers.add(number)
            }
        }

        // Shutdown Program A
        processInput.println("Shutdown")
        process.waitFor()

        // Process and display results
        val sortedNumbers = numbers.sorted()
        println("\nSorted numbers:")
        println(sortedNumbers.joinToString(", "))

        // Calculate statistics
        val average = numbers.average()
        val median = if (sortedNumbers.size % 2 == 0) {
            (sortedNumbers[NUM_SIZE / 2 - 1] + sortedNumbers[NUM_SIZE / 2]) / 2.0
        } else {
            sortedNumbers[NUM_SIZE / 2].toDouble()
        }

        println("\nStatistics:")
        println("Average: ${average.roundToInt()}")
        println("Median: ${median.roundToInt()}")

    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}