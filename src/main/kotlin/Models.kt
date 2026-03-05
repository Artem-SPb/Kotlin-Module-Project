// Models.kt

// Класс для заметки. Хранит заголовок и сам текст.
class Note(val title: String, val text: String)

// Класс для архива. У него есть название и список заметок внутри.
// Используем MutableList, чтобы можно было добавлять новые заметки.
class Archive(val title: String, val notes: MutableList<Note>)
