import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final static ArrayList<String> shoppingList = new ArrayList<>();
    private static Scanner scanner;
    private static void add() {
        System.out.println("Какую покупку хотите добавить?");
        scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        shoppingList.add(choice);
        System.out.println("Итого в списке покупок:" + shoppingList.size());
    }

    private static void show() {
        Iterator<String> iterator = shoppingList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            String currentShoppingList = iterator.next();
            System.out.println(i + ". " + currentShoppingList);
        }
    }

    private static void delete() {
        show();
        System.out.println("Какую хотите удалить? Введите номер или название");
        scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            choice--;
            String object = shoppingList.get(choice);
            shoppingList.remove(choice);
            System.out.println("Покупка " + object + " удалена, список покупок:");
        } catch (Exception e) {
            String choice = scanner.nextLine();
            if (shoppingList.contains(choice)) {
                String object = shoppingList.get(shoppingList.indexOf(choice));
                shoppingList.remove(choice);
                System.out.println("Покупка " + object + " удалена, список покупок:");
            }
        }
        show();
    }

    private static void search() {
        System.out.println("Введите текст для поиска:");
        scanner = new Scanner(System.in);
        int y = 0;
        String valueLower = scanner.nextLine().toLowerCase();
        System.out.println("Найдено:");
        for (String s : shoppingList) {
            y++;
            if (containsWord(s.toLowerCase(), valueLower)) {
                System.out.println(y + ". " + s);
            }
        }
    }

    //В проекте использовался OpenJDK v18.0.2
    //Функция contains работает некорректно с кириллицей
    //Для решения проблемы поиска воспользовался регулярными выражениями
    private static boolean containsWord(String FullString, String valueLower) {
        String pattern = ".*" + valueLower + ".*";
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(FullString);
        return mat.find();
    }

    public static void main(String[] args) {
        while (true) {
            int choice;
            System.out.println("Выберите операцию:");

            try {
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Введите числовое значение!");
                continue;
            }

            if (choice == 0) {
                scanner.close();
                break;
            }

            switch (choice) {
                case 1 -> add();
                case 2 -> show();
                case 3 -> delete();
                case 4 -> search();
            }
        }
    }
}