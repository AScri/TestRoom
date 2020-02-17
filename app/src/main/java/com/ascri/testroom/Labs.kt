package com.ascri.testroom

import android.os.Environment
import java.io.File
import java.io.FileWriter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.sqrt


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
        return listOf(
            lab1(),
            lab2(),
            lab3(),
            lab4(),
            lab5(),
            lab6(),
            lab7(),
            lab8(),
            lab9(),
            lab10(),
            lab11(),
            lab12(),
            lab13(),
            lab14(),
            lab15(),
            lab16(),
            lab17(),
            lab18(),
            lab19(),
            lab20(),
            lab21(),
            lab22(),
            lab23(),
            lab24(),
            lab25()
        )
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

    fun lab7(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input7.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val integer = scanner.nextInt()
            val conditions = listOf(
                integer < 0,
                (integer % 10 != 0 && (integer / 10) % 10 == 0),
                (integer % 3 != 0 && integer % 5 != 0)
            )
            if (conditions.count { !it } == 1)
                stringBuilder.appendln("YES")
            else stringBuilder.appendln("NO")
        }
        return writeFileOnExternalStorage(7, stringBuilder.toString())
    }

    fun lab8(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input8.txt"))
        val stringBuilder = StringBuilder()
        val lines = mutableListOf<String>()
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine())
        }
        val price = lines[0].split(" ").map { it.toInt() }
        val money = lines[1].split(" ").map { it.toInt() }
        val uahCentsPrice = 100 * price[0] + price[1]
        val uahCentsWallet = 100 * money[0] + money[1]
        val penCount = uahCentsWallet / uahCentsPrice
        val uahAfter = (uahCentsWallet - uahCentsPrice * penCount) / 100
        val centsAfter = uahCentsWallet - uahCentsPrice * penCount
        stringBuilder.appendln(penCount).append("$uahAfter $centsAfter")
        return writeFileOnExternalStorage(8, stringBuilder.toString())
    }

    fun lab9(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input9.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val i = scanner.nextInt().toDouble()
            stringBuilder.appendln(sqrt(i).toInt() + 1)
        }
        return writeFileOnExternalStorage(9, stringBuilder.toString())
    }

    fun lab10(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input10.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val n = scanner.nextInt()
            var t1 = 0
            var t2 = 1
            val fibList = mutableListOf(t1, t2)
            for (i in 3..n + 1) {
                val sum = t1 + t2
                t1 = t2
                t2 = sum
                fibList.add(sum)
            }
            stringBuilder.appendln(fibList.sum())
        }
        return writeFileOnExternalStorage(10, stringBuilder.toString())
    }

    fun lab11(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input11.txt"))
        val stringBuilder = StringBuilder()
        val list = mutableListOf<Int>()
        while (scanner.hasNextInt()) {
            val nextInt = scanner.nextInt()
            if (nextInt != 0) {
                list.add(nextInt)
            } else break
        }
        stringBuilder.appendln(list.sum())
        return writeFileOnExternalStorage(11, stringBuilder.toString())
    }

    fun lab12(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input12.txt"))
        val stringBuilder = StringBuilder()
        val list = mutableListOf<Int>()
        while (scanner.hasNextInt()) {
            val nextInt = scanner.nextInt()
            if (nextInt != 0) {
                list.add(nextInt)
            } else break
        }
        stringBuilder.appendln(list.let {
            it.remove(it.max()!!)
            it.max()
        })
        return writeFileOnExternalStorage(12, stringBuilder.toString())
    }

    fun lab13(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input13.txt"))
        val stringBuilder = StringBuilder()
        var beforeInt: Int? = null
        val sameCountList = mutableListOf<Int>()
        var sameCount = 1
        while (scanner.hasNextInt()) {
            val nextInt = scanner.nextInt()
            if (beforeInt != null && (nextInt != 0 || beforeInt != 0)) {
                if (beforeInt == nextInt) sameCount++
                else {
                    sameCountList.add(sameCount)
                    sameCount = 1
                }
            } else if (beforeInt != null) {
                sameCountList.add(sameCount)
                break
            }
            beforeInt = nextInt
        }
        stringBuilder.appendln(sameCountList.max())
        return writeFileOnExternalStorage(13, stringBuilder.toString())
    }

    fun lab14(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input14.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val size = scanner.nextInt()
            var max = Pair(0, 0)
            var min = Pair(0, 0)
            var sum = 0
            for (i in 1..size) {
                val number = scanner.nextInt()
                sum += number
                if (number >= max.first) {
                    max = Pair(number, i)
                }
                if (number < min.first) {
                    min = Pair(number, i)
                }
            }
            stringBuilder.appendln(sum).appendln("${max.first} ${max.second}")
                .appendln("${min.first} ${min.second}")
        }
        return writeFileOnExternalStorage(14, stringBuilder.toString())
    }

    fun lab15(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input15.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val size = scanner.nextInt()
            var tempNumber: Int? = null
            var uniqueCount = 1
            for (i in 1..size) {
                val number = scanner.nextInt()
                tempNumber?.let {
                    if (it != number) {
                        uniqueCount++
                    }
                }
                tempNumber = number
            }
            stringBuilder.appendln(uniqueCount)
        }
        return writeFileOnExternalStorage(15, stringBuilder.toString())
    }

    fun lab16(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input16.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val size = scanner.nextInt()
            val list = mutableListOf<Int>()
            for (i in 1..size) {
                val number = scanner.nextInt()
                list.add(number)
            }
            val sortedList = list.sortedWith(kotlin.Comparator { o1, o2 ->
                if (abs(o1) < abs(o2) || (abs(o1) == abs(o2) && o1 < o2)) -1 else 1
            })
            stringBuilder.appendln(sortedList.joinToString(" "))
        }
        return writeFileOnExternalStorage(16, stringBuilder.toString())
    }

    fun lab17(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input17.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val size = scanner.nextInt()
            val list = mutableListOf<Int>()
            for (i in 1..size) {
                val number = scanner.nextInt()
                list.add(number)
            }
            val evenList = list.filter { it % 2 == 0 }
            list.removeAll(evenList)
            stringBuilder.appendln("${evenList.joinToString(" ")} ${list.joinToString(" ")}")
        }
        return writeFileOnExternalStorage(17, stringBuilder.toString())
    }

    fun lab18(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input18.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val size = scanner.nextInt()
            val list = Array(size) { 0 }
            for (i in 0 until size) {
                val number = scanner.nextInt()
                list[size - 1 - i] = number
            }
            stringBuilder.appendln(list.joinToString(" "))
        }
        return writeFileOnExternalStorage(18, stringBuilder.toString())
    }

    fun lab19(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input19.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            var str = scanner.nextLine()
            str = str.map { if (it == 'z') 'w' else if (it == 'w') 'z' else it }.joinToString(" ")
            stringBuilder.appendln(str)
        }
        return writeFileOnExternalStorage(19, stringBuilder.toString())
    }

    fun lab20(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input20.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            val str = scanner.nextLine()
            stringBuilder.appendln(str.count { it == ' ' })
        }
        return writeFileOnExternalStorage(20, stringBuilder.toString())
    }

    fun lab21(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input21.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val xSize = scanner.nextInt()
            val ySize = scanner.nextInt()
            val mass = Array(xSize) { Array(ySize) { 0 } }
            for (i in 0 until xSize) {
                for (j in 0 until ySize) {
                    mass[i][j] = scanner.nextInt()
                }
            }
            val replasePair = Pair(scanner.nextInt(), scanner.nextInt())
            val temp = mass[replasePair.first - 1]
            mass[replasePair.first - 1] = mass[replasePair.second - 1]
            mass[replasePair.second - 1] = temp
            for (k in mass.indices) {
                stringBuilder.appendln(mass[k].joinToString(" "))
            }
        }
        return writeFileOnExternalStorage(21, stringBuilder.toString())
    }

    fun lab22(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input22.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val studCount = scanner.nextInt()
            val scores = Array(3) { 0 }
            for (i in 0 until studCount) {
                scanner.next()
                for (j in 0 until 3) {
                    scores[j] += scanner.nextInt()
                }
            }
            val middles = Array(3) { 0f }
            for (k in scores.indices) {
                scores[k] /= studCount
            }
            stringBuilder.appendln(scores.joinToString(" "))
        }
        return writeFileOnExternalStorage(22, stringBuilder.toString())
    }

    fun lab23(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input23.txt"))
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val cordsCount = scanner.nextInt()
            val sideSize = scanner.nextDouble()
            for (i in 0 until cordsCount) {
                var fits = true
                val coords = Array(3) { 0.0 }
                for (j in 0 until 3) {
                    val coord = scanner.next().toDouble()
                    coords[j] = coord
                    if (coord > sideSize) fits = false
                }
                if (fits) stringBuilder.appendln(coords.joinToString(" "))
            }
        }
        return writeFileOnExternalStorage(23, stringBuilder.toString())
    }

    fun lab24(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input24.txt"))
        val stringBuilder = StringBuilder()
        val numbers = mutableListOf<Int>()
        while (scanner.hasNextInt()) {
            val massSize = scanner.nextInt()
            for (i in 0 until massSize) {
                numbers.add(scanner.nextInt())
            }
        }
        val numbersMassive = numbers.toTypedArray()
        for (pass in 0 until (numbersMassive.size - 1)) {
            for (currentPosition in 0 until (numbersMassive.size - 1)) {
                if (numbersMassive[currentPosition] > numbersMassive[currentPosition + 1]) {
                    val tmp = numbersMassive[currentPosition]
                    numbersMassive[currentPosition] = numbersMassive[currentPosition + 1]
                    numbersMassive[currentPosition + 1] = tmp
                }
            }
        }
        stringBuilder.appendln(numbersMassive.joinToString(" "))
        return writeFileOnExternalStorage(24, stringBuilder.toString())
    }

    fun lab25(): File? {
        val scanner = Scanner(File(externalFile.path + "/" + "input25.txt"))
        val stringBuilder = StringBuilder()
        val stack = mutableListOf<Int>()
        loop@ while (scanner.hasNextLine()) {
            val command = scanner.nextLine()
            when {
                command.contains("push") -> {
                    stack.add(command.filter { it.isDigit() }.toInt())
                    stringBuilder.appendln("ok")
                }
                command.contains("pop") -> {
                    stringBuilder.appendln(stack.last())
                    stack.removeAt(stack.lastIndex)
                }
                command.contains("back") -> {
                    stringBuilder.appendln(stack.last())
                }
                command.contains("size") -> {
                    stringBuilder.appendln(stack.size)
                }
                command.contains("clear") -> {
                    stack.clear()
                    stringBuilder.appendln("ok")
                }
                command.contains("exit") -> {
                    stringBuilder.appendln("bye")
                    break@loop
                }
            }
        }
        return writeFileOnExternalStorage(25, stringBuilder.toString())
    }
}