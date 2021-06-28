package taskPalindrome;

import java.util.ArrayList;
import java.util.List;

public class User {
  private String name;
  private Integer score = 0;
  private List<String> phrase = new ArrayList<>();

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public List<String> getPhrase() {
    return phrase;
  }

  public void setPhrase(List<String> phrase) {
    this.phrase = phrase;
  }

  public void addPhrase(String s) {
    if (!s.isBlank()) {
      phrase.add(s);
    }
  }
}
