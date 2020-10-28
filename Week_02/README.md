学习笔记

不同GC的总结

1、-Xmx512m -Xms512m，压测1分钟：
	serial  parallel  cms	  g1
rps	1624.8	1920.3	 1662.3	 2489
max	338ms	309ms	 321ms	 275ms
min	0ms	    0ms	     0ms     0ms
avg	1.6ms	1.5ms	 1.5ms	 5ms

2、-Xmx1024m –Xms1024m，压测1分钟：
	serial  parallel  cms     g1
rps	1812.2	1950.5   1940.5	 2510.6
max	310ms	260ms    265ms	 120ms
min	0ms	    0ms	     0ms     0ms
avg	1.5ms	1.1ms    1.1ms	 4.5ms

同种GC:
堆内存太小的话，无论使用哪种GC，执行效率都很低，很容易OOM，
随着堆内存增大， fullGC次数会变少，对应youngGC次数也相应减少

不同GC比较：
1、	不同GC策略在堆内存为1024m时吞吐量比512m均有所提升，GC时间均有所下降；
2、	堆内存相同的情况下，g1的吞吐量最好，最大GC时间最小，但是平均GC时间最大；
3、	综上可以看出GC效率：g1>parallel>cms>serial。
