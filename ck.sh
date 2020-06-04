#!/bin/bash
kotlinc range.kt -include-runtime -d range.jar
java -jar range.jar
