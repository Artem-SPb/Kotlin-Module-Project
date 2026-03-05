import java.util.Scanner

// Screens.kt

class Screens(private val scanner: Scanner) {
    // Тут храним все наши архивы
    private val archives: MutableList<Archive> = mutableListOf()
    // Создаем меню и передаем ему сканер
    private val menu = Menu(scanner)

    // Главный экран со списком архивов
    fun showArchiveList() {
        // Запускаем меню и передаем список пунктов.
        // Используем лямбду, чтобы список пересоздавался каждый раз.
        menu.showMenu("СПИСОК АРХИВОВ:") {
            val items = mutableListOf<MenuItem>()

            // Сначала пункт создания
            items.add(MenuItem("Создать архив") { createArchive() })

            // Потом добавляем все существующие архивы
            archives.forEach { archive ->
                items.add(MenuItem(archive.title) { showNoteList(archive) })
            }

            // В конце пункт выхода
            items.add(MenuItem("Выход") {
                println("До свидания!")
                System.exit(0) // Завершаем программу полностью
            })

            // Возвращаем готовый список
            items
        }
    }

    // Логика создания архива
    private fun createArchive() {
        println("Введите название архива:")
        var name = scanner.nextLine()

        // Проверка, чтобы имя не было пустым
        while (name.trim().isEmpty()) {
            println("Название не может быть пустым. Введите название:")
            name = scanner.nextLine()
        }

        // Сохраняем новый архив
        archives.add(Archive(name, mutableListOf()))
        println("Архив '$name' создан!")
    }

    // Экран просмотра заметок внутри архива
    private fun showNoteList(archive: Archive) {
        // Тоже обновляем список каждый раз, чтобы видеть новые заметки
        menu.showMenu("АРХИВ '${archive.title}':") {
            val items = mutableListOf<MenuItem>()

            items.add(MenuItem("Создать заметку") { createNote(archive) })

            // Добавляем заметки из текущего архива
            archive.notes.forEach { note ->
                items.add(MenuItem(note.title) { showNoteDetails(note) })
            }

            // Пункт для возврата назад (просто пустая лямбда, чтобы сработал break в Menu)
            items.add(MenuItem("Выход") { })

            items
        }
    }

    // Логика создания заметки
    private fun createNote(archive: Archive) {
        println("Введите название заметки:")
        var title = scanner.nextLine()

        // Валидация заголовка
        while (title.trim().isEmpty()) {
            println("Название не может быть пустым. Попробуйте еще раз:")
            title = scanner.nextLine()
        }

        println("Введите текст заметки:")
        var text = scanner.nextLine()

        // Валидация текста
        while (text.trim().isEmpty()) {
            println("Заметка не может быть пустой. Введите текст:")
            text = scanner.nextLine()
        }

        // Добавляем заметку в список заметок этого архива
        archive.notes.add(Note(title, text))
        println("Заметка '$title' создана!")
    }

    // Экран просмотра самой заметки
    private fun showNoteDetails(note: Note) {
        println("\n--- ${note.title} ---")
        println(note.text)
        println("-------------------")

        // Ждем нажатия Enter, чтобы пользователь успел прочитать
        println("Нажмите Enter, чтобы вернуться назад...")
        scanner.nextLine()
    }
}

