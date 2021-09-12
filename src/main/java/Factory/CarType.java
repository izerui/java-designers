package Factory;

import java.util.function.Supplier;

public enum CarType {
  /**
   * 福特单例对象
   */
  FORD(Ford::new),
  /**
   * 法拉利单例对象
   */
  FERRARI(Ferrari::new);
  
  private final Supplier<Car> constructor;
  
  CarType(Supplier<Car> constructor) {
    this.constructor = constructor;
  }
  
  public Supplier<Car> getConstructor() {
    return this.constructor;
  }
}