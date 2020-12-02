学习笔记
## Week07 作业题目（周四）：
2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
（1）使用PreparedStatement的批量执行（addBatch+executeBatch）
（2）使用一条插入语句中values后携带多条记录（Mybatis中values后面使用foreach可以添加多个记录）
（3）使用LOAD DATA方式导入

总结：
三种方式导入的结果如下：
采用方案|运行时间
--|--
使用PreparedStatement的批量执行|53479ms
一条插入语句中values后携带多条记录|78201ms
LOAD DATA方式|31635ms

可以看出，LOAD DATA方式最快，批量操作方式次之，而一次性插入携带多个插入值最慢。