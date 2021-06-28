package taskPalindrome;

public class PalindromeCheck {

  public boolean isPalindrome(String s) {
    String q = changeString(s);
    return q.equals(new StringBuilder(q).reverse().toString());
  }

  public String changeString(String s) {
    return s.toLowerCase().replaceAll(" ", "");
  }
}
