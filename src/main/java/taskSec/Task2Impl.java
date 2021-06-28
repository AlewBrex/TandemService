package taskSec;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 *
 *
 * <h1>Задание №2</h1>
 *
 * Реализуйте интерфейс {@link IElementNumberAssigner}.
 *
 * <p>Помимо качества кода, мы будем обращать внимание на оптимальность предложенного алгоритма по
 * времени работы с учетом скорости выполнения операции присвоения номера: большим плюсом (хотя это
 * и не обязательно) будет оценка числа операций, доказательство оптимальности или указание области,
 * в которой алгоритм будет оптимальным.
 */
public class Task2Impl implements IElementNumberAssigner {

  public static volatile Task2Impl instance;

  public static Task2Impl getInstance() {
    Task2Impl task2 = instance;
    if (task2 == null) {
      synchronized (Task2Impl.class) {
        task2 = instance;
        if (task2 == null) {
          instance = new Task2Impl();
        }
      }
    }
    return task2;
  }

  @Override
  public void assignNumbers(final List<IElement> elements) {
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    Multi stream = new Multi(elements);
    forkJoinPool.invoke(stream);
  }
}
