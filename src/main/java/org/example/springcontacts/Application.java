package org.example.springcontacts;


import org.example.springcontacts.config.AppConfig;
import org.example.springcontacts.manager.ContactManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactManager manager = context.getBean(ContactManager.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите команду: ");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String[] commands = input.trim().split("\\s", 2);
            String command = commands[0].toUpperCase(Locale.ROOT);
            switch (command) {
                case "ADD" -> {
                    if (commands.length < 2 || commands[1].isBlank()) {
                        System.out.println("Необходимо указать данные для добавления");
                        continue;
                    }
                    try {
                        manager.addContactFromString(commands[1], ";");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Введите данные контакта в формате " +
                                "ФИО;номер;эл.почта");
                    }
                }
                case "DELETE" -> {
                    if (commands.length < 2) {
                        System.out.println("Необходимо указать адрес эл. почты для удаления");
                        continue;
                    }
                    int deletedCount = manager.deleteByEmail(commands[1]);
                    System.out.println("Удалено записей: " + deletedCount);

                }
                case "LIST" -> {
                    List<String> data = manager.getAllContactsInStrings("|");
                    data.forEach(System.out::println);
                }
                case "SAVE" -> {
                    if (commands.length < 2) {
                        System.out.println("Необходимо указать имя файла для записи");
                        continue;
                    }
                    try {
                        manager.saveToFile(commands[1]);
                    } catch (IOException e) {
                        System.out.println("Ошибка записи в файл");
                    }
                }

                default -> {
                    System.out.println("Некорректная команда. Попробуйте еще раз");
                }
            }

        }

    }
}