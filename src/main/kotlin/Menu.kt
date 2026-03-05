import java.util.Scanner

// Menu.kt

// Класс для пункта меню.
// title - это то, что видит пользователь
// action - это код, который сработает, когда выберут этот пункт
class MenuItem(val title: String, val action: () -> Unit)

class Menu(private val scanner: Scanner) {

    // Функция запускает меню.
    // Важный момент: itemsBuilder - это функция, которая возвращает список пунктов.
    // Я сделал так, чтобы каждый раз при обновлении меню список создавался заново.
    // Иначе новые архивы не появлялись бы.
    fun showMenu(title: String, itemsBuilder: () -> MutableList<MenuItem>) {
        while (true) {
            // Получаем актуальный список пунктов
            val items = itemsBuilder()

            println("\n$title")

            // Выводим пункты на экран с номерами (0, 1, 2...)
            items.forEachIndexed { index, item ->
                println("$index. ${item.title}")
            }

            // Читаем ввод пользователя
            val input = scanner.nextLine()

            // Проверяем, ввел ли пользователь число
            if (input.toIntOrNull() != null) {
                val index = input.toInt()

                // Проверяем, есть ли такой пункт в списке
                if (index >= 0 && index < items.size) {
                    // Выполняем действие пункта
                    items[index].action()

                    // Если это был пункт "Выход", то прерываем цикл while и выходим из меню
                    if (items[index].title == "Выход") {
                        break
                    }
                } else {
                    // Если число ввели, но такого пункта нет
                    println("Такого пункта нет, попробуйте другую цифру.")
                }
            } else {
                // Если ввели буквы или пустую строку
                println("Введите цифру, пожалуйста.")
            }
        }
    }
}
