package jm.task.core.jdbc;


import com.mysql.cj.xdevapi.SessionFactory;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command = new String();
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println("Жду команды");
        while (!command.equals("exit")){
            command = in.nextLine();
            switch (command) {
                case ("create"):
                    userService.createUsersTable();
                    break;
                case ("exit"):
                    System.out.println("Закрываю приложение");
                    break;
                case ("drop"):
                    userService.dropUsersTable();
                    break;
                case ("add"):
                    System.out.println("Введите данные пользователя: Имя, Фамилию, Возраст через пробел \n");
                    userService.saveUser(in.next(), in.next(), in.nextByte());
                    break;
                case ("delete"):
                    System.out.println("Введите id, по которому хотите удалить запись ");
                    userService.removeUserById(in.nextInt());
                    break;
                case ("clean"):
                    userService.cleanUsersTable();
                    break;
                case ("getall"):
                    ArrayList<User> arr =(ArrayList<User>) userService.getAllUsers();
                    for(User u : arr) {
                        System.out.println(u);
                    }

                }
            }


    }
}