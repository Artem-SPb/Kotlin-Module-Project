import java.util.Scanner

// Main.kt

fun main(args: Array<String>) {
    println("Добро пожаловать в Заметки!")

    // Инициализируем сканер один раз на всю программу
    val scanner = Scanner(System.`in`)

    // Создаем объект Screens и запускаем главный экран
    val screens = Screens(scanner)
    screens.showArchiveList()
}

