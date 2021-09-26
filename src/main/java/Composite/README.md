# 组合 Composite

## 目的

将对象组合成树结构以表示部分整体层次结构。 组合可以使客户统一对待单个对象和组合对象。

## 解释

真实世界例子

> 每个句子由单词组成，单词又由字符组成。这些对象中的每一个都是可打印的，它们可以在它们之前或之后打印一些内容，例如句子始终以句号结尾，单词始终在其前面有空格。

通俗的说

> 组合模式使客户能够以统一的方式对待各个对象。

维基百科说

> 在软件工程中，组合模式是一种分区设计模式。组合模式中，一组对象将像一个对象的单独实例一样被对待。组合的目的是将对象“组成”树状结构，以表示部分整体层次结构。实现组合模式可使客户统一对待单个对象和组合对象。

**程序示例**

使用上面的句子例子。 这里我们有基类`LetterComposite 字母组合`和不同的可打印类型`Letter 字母`，`Word 单词`和`Sentence 句子`。

```java
/**
 * 组合抽象
 */
public abstract class LetterComposite {

    private final List<LetterComposite> children = new ArrayList<>();

    public void add(LetterComposite letter) {
        children.add(letter);
    }

    public int count() {
        return children.size();
    }

    protected void printThisBefore() {
    }

    protected void printThisAfter() {
    }

    /**
     * Print.
     */
    public void print() {
        printThisBefore();
        children.forEach(LetterComposite::print);
        printThisAfter();
    }
}

/**
 * 字母.
 */
public class Letter extends LetterComposite {

    private final char character;

    public Letter(char character) {
        this.character = character;
    }

    @Override
    protected void printThisBefore() {
        System.out.print(character);
    }
}

/**
 * 单词.
 */
public class Word extends LetterComposite {

    /**
     * 单词的构造方法.
     * @param letters 字母集合
     */
    public Word(List<Letter> letters) {
        letters.forEach(this::add);
    }

    /**
     * 单词的构造方法.
     * @param letters 字母数组
     */
    public Word(char... letters) {
        for (char letter : letters) {
            this.add(new Letter(letter));
        }
    }

    @Override
    protected void printThisBefore() {
        System.out.print(" ");
    }
}

/**
 * 句子.
 */
public class Sentence extends LetterComposite {

    /**
     * 构造方法.
     */
    public Sentence(List<Word> words) {
        words.forEach(this::add);
    }

    @Override
    protected void printThisAfter() {
        System.out.print(".\n");
    }
}
```

然后我们有一个`Message`消息。

```java
/**
 * 消息类
 */
public class Messenger {

    // 来自兽人的消息
    LetterComposite messageFromOrcs() {

        List<Word> words = Arrays.asList(
                new Word('W', 'h', 'e', 'r', 'e'),
                new Word('t', 'h', 'e', 'r', 'e'),
                new Word('i', 's'),
                new Word('a'),
                new Word('w', 'h', 'i', 'p'),
                new Word('t', 'h', 'e', 'r', 'e'),
                new Word('i', 's'),
                new Word('a'),
                new Word('w', 'a', 'y')
        );

        return new Sentence(words);

    }

    // 来自精灵的消息
    LetterComposite messageFromElves() {

        List<Word> words = Arrays.asList(
                new Word('M', 'u', 'c', 'h'),
                new Word('w', 'i', 'n', 'd'),
                new Word('p', 'o', 'u', 'r', 's'),
                new Word('f', 'r', 'o', 'm'),
                new Word('y', 'o', 'u', 'r'),
                new Word('m', 'o', 'u', 't', 'h')
        );

        return new Sentence(words);

    }

}

```

然后它可以这样使用:

```java
var messenger = new Messenger();

LOGGER.info("来自兽人的消息: ");
messenger.messageFromOrcs().print();

LOGGER.info("来自精灵的消息: ");
messenger.messageFromElves().print();
```

## 类图

![alt text](../../resources/puml/uml/composite.urm.png "Composite class diagram")

## 时序图

![alt text](../../resources/puml/puml/Composite.png)

## 实现方式

* 确保应用的核心模型能够以树状结构表示。 尝试将其分解为简单元素和容器。 记住， 容器必须能够同时包含简单元素和其他容器。
* 声明组件接口及其一系列方法， 这些方法对简单和复杂元素都有意义。
* 创建一个叶节点类表示简单元素。 程序中可以有多个不同的叶节点类。
* 创建一个容器类表示复杂元素。 在该类中， 创建一个数组成员变量来存储对于其子元素的引用。 该数组必须能够同时保存叶节点和容器， 因此请确保将其声明为组合接口类型。
* 实现组件接口方法时， 记住容器应该将大部分工作交给其子元素来完成。
* 最后， 在容器中定义添加和删除子元素的方法。
* 记住， 这些操作可在组件接口中声明。 这将会违反_接口隔离原则_， 因为叶节点类中的这些方法为空。 但是， 这可以让客户端无差别地访问所有元素， 即使是组成树状结构的元素。

## 适用场景

* 如果你需要实现树状对象结构， 可以使用组合模式。
* 如果你希望客户端代码以相同方式处理简单和复杂元素， 可以使用该模式。

**优点**

* 你可以利用**多态**和**递归**机制更方便地使用复杂树结构。
* 开闭原则。 无需更改现有代码， 你就可以在应用中添加新元素， 使其成为对象树的一部分。

**缺点**

## 与其他模式的关系

* [桥接模式](Bridge)、 [状态模式](State)和[策略模式](Strategy) （在某种程度上包括[适配器模式](Adapter)） 模式的接口非常相似。 实际上， 它们都基于组合模式——即将工作委派给其他对象， 不过也各自解决了不同的问题。 模式并不只是以特定方式组织代码的配方， 你还可以使用它们来和其他开发者讨论模式所解决的问题。
* 你可以在创建复杂组合树时使用[生成器模式](Builder)， 因为这可使其构造步骤以递归的方式运行。
* [责任链模式](Chain)通常和组合模式结合使用。 在这种情况下， 叶组件接收到请求后， 可以将请求沿包含全体父组件的链一直传递至对象树的底部。
* 你可以使用[迭代器模式](Iterator)来遍历组合树。
* 你可以使用[访问者模式](Visitor)对整个组合树执行操作。
* 你可以使用[享元模式](Flyweight)实现组合树的共享叶节点以节省内存。
* 组合和[装饰模式](Decorator)的结构图很相似， 因为两者都依赖递归组合来组织无限数量的对象。
* 装饰类似于组合， 但其只有一个子组件。 此外还有一个明显不同： 装饰为被封装对象添加了额外的职责， 组合仅对其子节点的结果进行了 “求和”。
* 但是， 模式也可以相互合作： 你可以使用[装饰模式](Decorator)来扩展组合树中特定对象的行为。
* 可以通过[原型模式](Prototype)更加方便的使用[组合模式](Builder)和[装饰模式](Decorator)来构建大型应用,这样你可以通过[原型模式](Prototype)来复制复杂结构,而非从零开始构造.

## 真实世界例子

* org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter RequestMappingHandlerAdapter使用了组合模式处理多样化参数映射问题,返回值映射等问题
* [java.awt.Container](http://docs.oracle.com/javase/8/docs/api/java/awt/Container.html) and [java.awt.Component](http://docs.oracle.com/javase/8/docs/api/java/awt/Component.html)
* [Apache Wicket](https://github.com/apache/wicket) component tree, see [Component](https://github.com/apache/wicket/blob/91e154702ab1ff3481ef6cbb04c6044814b7e130/wicket-core/src/main/java/org/apache/wicket/Component.java) and [MarkupContainer](https://github.com/apache/wicket/blob/b60ec64d0b50a611a9549809c9ab216f0ffa3ae3/wicket-core/src/main/java/org/apache/wicket/MarkupContainer.java)

## 鸣谢

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
* [Refactoring to Patterns](https://www.amazon.com/gp/product/0321213351/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0321213351&linkCode=as2&tag=javadesignpat-20&linkId=2a76fcb387234bc71b1c61150b3cc3a7)
