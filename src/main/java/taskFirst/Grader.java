package taskFirst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grader implements Comparator<String[]> {

  private static final String match = "\\d+";
  private int indexColumn;

  public Grader(int indexColumn) {
    this.indexColumn = indexColumn;
  }

  @Override
  public int compare(String[] o1, String[] o2) {
    String q = o1[indexColumn];
    String w = o2[indexColumn];

    if (q.isEmpty() && w.isEmpty()) {
      return 0;
    }
    if (q.isEmpty()) {
      return -1;
    }
    if (w.isEmpty()) {
      return 1;
    }
    return subCompare(q, w);
  }

  private int subCompare(String first, String second) {
    List<String> listLeft = new ArrayList<>();
    List<String> listRight = new ArrayList<>();

    Pattern pattern = Pattern.compile(match);
    Matcher matcher;

    matcher = pattern.matcher(first);
    while (matcher.find()) {
      listLeft.add(first.substring(matcher.start(), matcher.end()));
    }
    listLeft.add(first.replaceAll(pattern.toString(), ""));

    matcher = pattern.matcher(second);
    while (matcher.find()) {
      listRight.add(second.substring(matcher.start(), matcher.end()));
    }
    listRight.add(second.replaceAll(pattern.toString(), ""));

    for (int i = 0; i < Math.max(listLeft.size(), listRight.size()); i++) {
      String a = listLeft.get(i);
      String b = listRight.get(i);

      if (a.equals(b)) {
        continue;
      }

      if (isDigits(a) && isDigits(b)) {
        return Integer.valueOf(a).compareTo(Integer.valueOf(b));
      }

      return a.compareTo(b);
    }

    return first.compareTo(second);
  }

  private boolean isDigits(String digits) {
    return digits.matches(match);
  }
}
