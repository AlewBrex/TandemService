package taskSec;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/** одна из возможных реализаций {@link IElement} */
public final class ElementExampleImpl implements IElement {

  public static class Context {

    // количество операций присвоения
    private final AtomicInteger operationCount = new AtomicInteger(0);

    // проверка на уникальность номеров элементов
    private Map<Integer, ElementExampleImpl> uniqueMap = new HashMap<>();

    public synchronized int getOperationCount() {
      return this.operationCount.get();
    }

    public synchronized void updateCount() {
      this.operationCount.incrementAndGet();
    }
  }

  private final Context context;
  private final long id;
  private Integer number = null;

  /** @param number номер элемента */
  public ElementExampleImpl(final Context context, final int number) {
    this.context = context;
    this.id = System.identityHashCode(this);
    this.setNumber(number);
  }

  @Override
  public long getId() {
    return this.id;
  }

  @Override
  public int getNumber() {
    return this.number;
  }

  private void setNumber(final int number) {
    if (this.number != null) {
      this.context.uniqueMap.remove(this.number);
    }

    this.number = number;

    final ElementExampleImpl old = this.context.uniqueMap.put(this.number, this);
    if (null == old) {
      return;
    }
    if (this == old) {
      return;
    }
    throw new IllegalStateException("Duplicate numbers.");
  }

  @Override
  public void setupNumber(final int number) {
    this.setNumber(number);
    // число таких операций фиксируется
    this.context.updateCount();
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new UnsupportedOperationException(); // клонировать элементы нельзя
  }
}
