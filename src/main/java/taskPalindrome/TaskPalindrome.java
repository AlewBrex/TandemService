package taskPalindrome;

import java.util.Scanner;

public class TaskPalindrome {
  private static UserStorage userStorage = new UserStorage();
  private static PalindromeCheck palindromeCheck = new PalindromeCheck();

  public static void main(String[] args) {

    for (; ; ) {
      System.out.println("Добро пожаловать в игру!" + "\n" + "Введите ваше имя:");
      String nameUser = new Scanner(System.in).nextLine();
      User user = new User(nameUser);
      boolean b = userStorage.existUser(user);
      if (!b) {
        userStorage.addUser(user);
      }

      System.out.println("Введите фразу/слова для проверки:");
      String phraseUser = new Scanner(System.in).nextLine();
      boolean check = palindromeCheck.isPalindrome(phraseUser);
      String newString = palindromeCheck.changeString(phraseUser);
      userStorage.incrementScore(user.getName(), check, newString);

      userStorage.print();
    }
  }
}
