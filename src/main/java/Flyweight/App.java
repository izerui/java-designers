package Flyweight;

/**
 * 享元模式: 它用于通过尽可能多地与相似对象共享来最小化内存使用或计算开销。
 *
 * <p>炼金术士的商店货架上摆满了魔法药水。许多药水是相同的，因此无需为每个药水创建一个新对象。
 * 相反，一个对象实例可以表示多个货架项目，因此内存占用仍然很小。
 *
 * <p>为了在客户端和线程之间实现安全共享，享元对象必须是不可变的。按照定义，享元对象是值对象(类似POJO)。
 */
public class App {


  public static void main(String[] args) {
    // 创建一个摆满药剂的炼金药水商店
    AlchemistShop alchemistShop = new AlchemistShop();
    // 一位勇敢的访客进入炼金术士商店并喝下所有的药水
    alchemistShop.drinkPotions();
  }
}
