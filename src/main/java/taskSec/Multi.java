package taskSec;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Multi extends RecursiveAction {
  private int start;
  private int end;
  private int limit;
  private volatile List<IElement> elements;

  public Multi(List<IElement> elements) {
    this.elements = elements;
  }

  public Multi(List<IElement> elements, int startNumber, int endNumber) {
    this.elements = elements;
    this.limit = (elements.size() % Runtime.getRuntime().availableProcessors());
    this.start = startNumber;
    this.end = endNumber;
  }

  @Override
  protected void compute() {
    if ((end - start) <= limit) {
      for (int i = start; i <= Math.max(end - 1, elements.size() - 1); i++) {
        IElement element = elements.get(i);
        setupNumberElement(element, i);
      }
    } else {
      int r = (start + end) / 2;
      invokeAll(new Multi(elements, start, r), new Multi(elements, r + 1, end));
    }
  }

  private void setupNumberElement(IElement element, int counter) {
    if (element.getNumber() != counter) {
      try {
        element.setupNumber(counter);
      } catch (IllegalStateException e) {
        setupNumberElement(element, counter);
      }
    }
  }
}