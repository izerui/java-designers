package Abstract;

// 王国工厂基类
public interface KingdomFactory {
  Castle createCastle(); // 创建城堡
  King createKing(); // 创建国王
  Army createArmy(); // 创建军队
}