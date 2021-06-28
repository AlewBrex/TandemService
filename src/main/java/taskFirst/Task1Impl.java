package taskFirst;


import java.util.List;

/**
 *
 *
 * <h1>Задание №1</h1>
 *
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными
 * средствами java.
 */
public class Task1Impl implements IStringRowsListSorter {
  public static volatile Task1Impl instance;

  public static Task1Impl getInstance() {
    Task1Impl task1 = instance;
    if (task1 == null) {
      synchronized (Task1Impl.class) {
        task1 = instance;
        if (task1 == null) {
          instance = new Task1Impl();
        }
      }
    }
    return task1;
  }

  @Override
  public void sort(final List<String[]> rows, final int columnIndex) {
    rows.sort(new Grader(columnIndex));
  }
}
