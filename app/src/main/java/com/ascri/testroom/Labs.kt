package com.ascri.testroom

import android.os.Environment
import java.io.File
import java.io.FileWriter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Labs {
    private val externalFile = File(Environment.getExternalStorageDirectory().path, "/labs/")

    private fun writeFileOnExternalStorage(labNum: Int, sBody: String): File? {
        val root = externalFile
        val outputFile = File(root.path + "/" + "output$labNum.txt")
        if (!root.exists()) {
            root.mkdirs()
        }
        return try {
            outputFile.createNewFile()
            val writer = FileWriter(outputFile)
            writer.append(sBody)
            writer.flush()
            writer.close()
            outputFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun doAllLabs(): List<File?> {
        return listOf(lab1(), lab2(), lab3(), lab4(), lab5(), lab6())
    }

    private fun lab1Sum(n: Int): Int {
        if (n < 10) return n
        return n % 10 + lab1Sum(n / 10)
    }

    fun lab1(): File? {
        val inputFile = File(externalFile.path + "/" + "input1.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNext()) {
            var n: Int = scanner.nextInt()
            if (n < 0) n = -n
            val res: Int = lab1Sum(n)
            stringBuilder.appendln(res)
        }
        return writeFileOnExternalStorage(1, stringBuilder.toString())
    }

    fun lab2(): File? {
        val inputFile = File(externalFile.path + "/" + "input2.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            val line: List<String> = scanner.nextLine().split(" ")
            var hours = line[0].toInt()
            var minutes = line[1].toInt()
            val seconds = line[2].toInt()
            hours = (hours + (minutes + 1) / 60) % 24
            minutes = (minutes + 1) % 60
            val resultLine = "$hours $minutes $seconds"
            stringBuilder.appendln(resultLine)
        }
        return writeFileOnExternalStorage(2, stringBuilder.toString())
    }

    fun lab3(): File? {
        val inputFile = File(externalFile.path + "/" + "input3.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        val students = scanner.nextInt()
        val apples = scanner.nextInt()
        val result = students - (apples % students)
        stringBuilder.appendln(result)
        return writeFileOnExternalStorage(3, stringBuilder.toString())
    }

    fun lab4(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input4.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            var i = scanner.nextInt()
            var count = 0
            while (i > 0) {
                if (i % 10 == 0) {
                    count++
                }
                i /= 10
            }
            stringBuilder.appendln(count)
        }
        return writeFileOnExternalStorage(4, stringBuilder.toString())
    }

    fun lab5(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input5.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            val dateString = scanner.nextLine()
            val year = dateString.split(" ")[2].toInt()
            val sdf: DateFormat = SimpleDateFormat("dd MM yyyy", Locale.US)
            sdf.isLenient = false
            try {
                sdf.parse(dateString)
                if (((year / 100) + 1) == 21)
                    stringBuilder.appendln("YES")
                else
                    stringBuilder.appendln("NO")

            } catch (e: ParseException) {
                stringBuilder.appendln("NO")
            }
        }
        return writeFileOnExternalStorage(5, stringBuilder.toString())
    }

    fun lab6(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input6.txt"))
        val stringBuilder = StringBuilder()
        val lines = mutableListOf<String>()
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine())
        }
        val coords = lines.last().split(" ")
        val x = coords[0].toInt()
        val y = coords[1].toInt()
        for (i in 0..lines.size - 2) {
            val line = lines[i].split(" ")
            val x1 = line[0].toInt()
            val y1 = line[1].toInt()
            if (x == x1 || y == y1 || x - y == x1 - y1 || x + y == x1 + y1)
                stringBuilder.append("$x1 $y1 ")
        }
        return writeFileOnExternalStorage(6, stringBuilder.toString())
    }
}