package taskPalindrome;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class UserStorage {
  private TreeMap<String, User> storage = new TreeMap<>();
  private TreeSet<User> list = new TreeSet<>(Comparator.comparing(User::getScore));

  public TreeMap<String, User> getStorage() {
    return storage;
  }

  public void setStorage(TreeMap<String, User> storage) {
    this.storage = storage;
  }

  public void addUser(User user) {
    storage.put(user.getName(), user);
    list.add(user);
  }

  public boolean existUser(User user) {
    return storage.get(user.getName()) != null;
  }

  public void incrementScore(String userName, boolean b, String string) {
    if (b) {
      checkString(userName, string);
    } else {
      System.out.println("Не поздравляем - это не палиндром!");
    }
  }

  private void checkString(String userName, String s) {
    User user = storage.get(userName);
    if (!user.getPhrase().contains(s)) {
      user.addPhrase(s);
      user.setScore(user.getScore() + s.length());
      System.out.println(
          "Поздравляем! "
              + s
              + " является палиндромом и вам начислено: "
              + s.length()
              + " баллов.");
    } else {
      System.out.println("Вы уже использовали эту фразу в игре!");
    }
  }

  public void print() {
    List<User> userList =
        list.stream()
            .limit(5)
            .sorted(Comparator.comparing(User::getScore).reversed())
            .collect(Collectors.toList());
    System.out.println("Доска лидеров");
    userList.forEach(
        u -> System.out.println(u.getName() + " " + u.getScore() + " " + u.getPhrase()));
  }
}
