package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

public class UserDataProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите данные в следующем формате:");
            System.out.println("ФИО ДатаРождения НомерТелефона Пол");
            System.out.print("Пример: Иванов Иван Иванович 01.01.1990 1234567890 m\n");
            System.out.print("Введите данные: ");
            String input = scanner.nextLine();

            String[] userData = input.split(" ");

            if (userData.length != 5) {
                throw new InvalidDataFormatException("Неправильное количество данных. Пожалуйста, используйте примерный формат.");
            }

            String lastName = userData[0];
            String firstName = userData[1];
            String middleName = userData[2];
            String dateOfBirth = userData[3];
            String phoneNumber = userData[4];

            // Проверка формата даты рождения
            if (!Pattern.matches("\\d{2}.\\d{2}.\\d{4}", dateOfBirth)) {
                throw new InvalidDataFormatException("Неправильный формат даты рождения. Пожалуйста, используйте формат dd.mm.yyyy.");
            }

            // Проверка формата номера телефона
            if (!Pattern.matches("\\d+", phoneNumber)) {
                throw new InvalidDataFormatException("Неправильный формат номера телефона. Пожалуйста, используйте только цифры.");
            }

            // Проверка пола
            if (!"f".equalsIgnoreCase(phoneNumber) && !"m".equalsIgnoreCase(phoneNumber)) {
                throw new InvalidDataFormatException("Неправильный формат пола. Пожалуйста, используйте 'f' или 'm'.");
            }

            // Создание файла с фамилией пользователя
            FileWriter fileWriter = new FileWriter(lastName + ".txt");
            String userDataString = lastName + firstName + middleName + dateOfBirth + phoneNumber;
            fileWriter.write(userDataString);
            fileWriter.close();

            System.out.println("Данные успешно записаны в файл: " + lastName + ".txt");
        } catch (InvalidDataFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
