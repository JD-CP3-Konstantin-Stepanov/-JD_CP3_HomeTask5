import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final static List<String> shoppingList = new ArrayList<>();

    public static void add(Scanner Scanner) {
        System.out.println("Какую покупку хотите добавить?");
        shoppingList.add(Scanner.nextLine());
        System.out.println("Итого в списке покупок:" + shoppingList.size());
    }

    private static void show() {
        int listOrder = 0;
        for (String currentShoppingList : shoppingList) {
            listOrder++;
            System.out.println(listOrder + ". " + currentShoppingList);
        }
    }

    private static void delete(Scanner Scanner) throws RuntimeException {
        show();
        System.out.println("Какую хотите удалить? Введите номер или название");
        String strChoice = Scanner.nextLine();
        try {
            int intChoice = Integer.parseInt(strChoice);
            intChoice--;
            String object = shoppingList.get(intChoice);
            shoppingList.remove(intChoice);
            System.out.println("Покупка " + object + " удалена, список покупок:");
        } catch (NumberFormatException e) {
            if (shoppingList.contains(strChoice)) {
                String object = shoppingList.get(shoppingList.indexOf(strChoice));
                shoppingList.remove(strChoice);
                System.out.println("Покупка " + object + " удалена, список покупок:");
            }
        }
        show();
    }

    private static void search(Scanner Scanner) {
        System.out.println("Введите текст для поиска:");
        int listOrder = 0;
        String valueLower = Scanner.nextLine().toLowerCase();
        System.out.println("Найдено:");
        for (String s : shoppingList) {
            listOrder++;
            if (containsWord(s.toLowerCase(), valueLower)) {
                System.out.println(listOrder + ". " + s);
            }
        }
    }

    //В проекте использовался OpenJDK v18.0.2
    //Функция contains работает некорректно с кириллицей
    //Для решения проблемы поиска воспользовался регулярными выражениями
    private static boolean containsWord(String FullString, String ValueLower) {
        String pattern = ".*" + ValueLower + ".*";
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(FullString);
        return mat.find();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Выберите операцию:");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Введите числовое значение!");
                continue;
            }

            if (choice == 0) {
                scanner.close();
                break;
            }

            switch (choice) {
                case 1 -> add(scanner);
                case 2 -> show();
                case 3 -> delete(scanner);
                case 4 -> search(scanner);
            }
        }
        scanner.close();
    }
}