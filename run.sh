#!/bin/bash

# Check if required tools are installed
command -v kotlinc >/dev/null 2>&1 || { echo "kotlinc is required but not installed"; exit 1; }
command -v java >/dev/null 2>&1 || { echo "java is required but not installed"; exit 1; }

# Create build directory if it doesn't exist
mkdir -p build

# Compile and package the Kotlin files
kotlinc src/main/kotlin/RandomGenerator.kt -include-runtime -d build/randomgenerator.jar || {
    echo "Failed to compile RandomGenerator.kt"
    exit 1
}

kotlinc src/main/kotlin/Controller.kt -include-runtime -d build/controller.jar || {
    echo "Failed to compile Controller.kt"
    exit 1
}

# Execute the program
java -jar build/controller.jar build/randomgenerator.jar || {
    echo "Failed to execute the program"
    exit 1
}