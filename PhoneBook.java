import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PhoneBook {
    private Map<String, List<String>> phoneBook;

    // Конструктор создает новый объект PhoneBook с пустой телефонной книгой
    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    // Добавляет контакт в телефонную книгу
    public void addContact(String name, String phoneNumber) {
        // computeIfAbsent позволяет избежать проверки на null и инициализирует новый ArrayList, если не существует
        phoneBook.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNumber);
    }

    // Возвращает всю телефонную книгу
    public Map<String, List<String>> getPhoneBook() {
        return phoneBook;
    }

    // Возвращает количество номеров телефона для указанного имени
    public int getNumberOfPhoneNumbers(String name) {
        List<String> phoneNumbers = phoneBook.get(name);
        return phoneNumbers != null ? phoneNumbers.size() : 0;
    }

    // Сортирует записи по количеству номеров телефонов и возвращает список
    public List<Entry<String, String>> sortEntriesByPhoneNumberCount() {
        List<Entry<String, String>> entryList = new ArrayList<>();

        // Проходит по записям в телефонной книге
        for (Entry<String, List<String>> entry : phoneBook.entrySet()) {
            // Объединяет номера телефонов для данного имени, разделяя их запятыми
            String phoneNumbers = String.join(", ", entry.getValue());
            // Добавляет запись (имя, номера) в список
            entryList.add(Map.entry(entry.getKey(), phoneNumbers));
        }

        // Сортирует записи по убыванию количества номеров телефонов
        entryList.sort((entry1, entry2) -> {
            int size1 = phoneBook.get(entry1.getKey()).size();
            int size2 = phoneBook.get(entry2.getKey()).size();
            return Integer.compare(size2, size1);
        });

        return entryList;
    }

    // Метод main - точка входа в программу
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Добавление контактов в телефонную книгу
        phoneBook.addContact("Иван", "+375295659674");
        phoneBook.addContact("Иван", "+375292345678");
        phoneBook.addContact("Мария", "+375291112233");

        phoneBook.addContact("Анна", "+375296666666");
        phoneBook.addContact("Анна", "+375290000000");
        phoneBook.addContact("Анна", "+375291234567");

        // Получение всей телефонной книги
        Map<String, List<String>> phoneBookData = phoneBook.getPhoneBook();

        // Вывод всех записей с номерами телефонов
        for (Map.Entry<String, List<String>> entry : phoneBookData.entrySet()) {
            System.out.println("Имя: " + entry.getKey() + ", Номера: " + String.join(", ", entry.getValue()));
        }

        System.out.println("Отсортированные записи по количеству номеров:");
        // Сортировка и вывод записей по количеству номеров телефонов
        List<Entry<String, String>> sortedEntries = phoneBook.sortEntriesByPhoneNumberCount();
        for (Entry<String, String> entry : sortedEntries) {
            System.out.println("Имя: " + entry.getKey() + ", Номера: " + entry.getValue());
        }
    }
}
