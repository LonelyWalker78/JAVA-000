学习笔记

### 不同GC的总结

1、-Xmx512m -Xms512m，压测1分钟：
|  | serial | parallel | cms | g1 |
| --- | --- | --- | --- | --- |
| rps | 1578.4 | 1864.5 | 1592.6 | 2369 |
| max | 353ms | 324ms | 336ms | 290ms |
| min | 0ms | 0ms  | 0ms | 0ms |
| avg | 1.7ms | 1.6ms | 1.6ms | 5.1ms |

2、-Xmx1024m –Xms1024m，压测1分钟：
|  | serial | parallel | cms | g1 |
| --- | --- | --- | --- | --- |
| rps | 1765.3 | 1843.6 | 1834.8 | 2461.8 |
| max | 325ms | 275ms | 280ms | 135ms |
| min | 0ms | 0ms  | 0ms | 0ms |
| avg | 1.6ms | 1.2ms | 1.2ms | 4.6ms |

同种GC:
堆内存太小的话，无论使用哪种GC，执行效率都很低，很容易OOM，
随着堆内存增大， fullGC次数会变少，对应youngGC次数也相应减少

不同GC比较：
1、	不同GC策略在堆内存为1024m时吞吐量比512m均有所提升，GC时间均有所下降；
2、	堆内存相同的情况下，g1的吞吐量最好，最大GC时间最小，但是平均GC时间最大；
3、	综上可以看出GC效率：g1>parallel>cms>serial。
