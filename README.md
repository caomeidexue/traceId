该项目是为了学习非关系型数据库
redis

es

mongodb

分布式traceId 日志集成 https://www.cnblogs.com/tong-yuan/p/12128796.html

分布式系统中日志追踪需要考虑的几个点？
需要一个全服务唯一的id，即traceId，如何保证？
traceId如何在服务间传递？
traceId如何在服务内部传递？
traceId如何在多线程中传递？
我们一一来解答：

全服务唯一的traceId，可以使用uuid生成，正常来说不会出现重复的；
关于服务间传递，对于调用者，在协议头加上traceId，对于被调用者，通过前置拦截器或者过滤器统一拦截；
关于服务内部传递，可以使用ThreadLocal传递traceId，一处放置，随处可用；
关于多线程传递，分为两种情况：
子线程，可以使用InheritableThreadLocal
线程池，需要改造线程池对提交的任务进行包装，把提交者的traceId包装到任务中