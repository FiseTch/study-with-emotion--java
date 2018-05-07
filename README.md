# java-skill
## java 技能图谱

* 计算机基础 ；
	* 操作系统<现代操作系统>
	* 编译原理
	* 计算机网络
	* 互联网协议 
		* tcp/ip协议<tcp/ip协议簇>
		* Http1.1/2.0
		* HTTPs
		* REST
* JAVA编程
	 * IDE 
	 	* eclispe
		* intellij
	 * 版本控制
	 	* Git
		* svn
* 基础语法   jdk8.0<核心技术卷I>
* 集合框架 (源码)
	* List 
		* ArrayList  长度可变，初始长度为10，如果长度超过10，重新分派空间。10->16->25->38->58,易存易取
		* LinkList 	  易删
	* set 元素不允许重复
		* HashSet
		* TreeSet
	* Map 键值对
	 * HashMap
	 * TreeMap
	 * concurrentHashMap	?
	 * concurrentLinkedQueue ?
	* stack 栈（堆的区别）
	* collections 的方法（多进程的同步synchronize）
* java高级特性
	* 泛型（）
	* 多态的实现原理
	* Lambda -> java 8 出现
	* 线程
		* 单线程
		* 多线程 并发 
	* 网络编程 Socket ，SocketService socket服务器涉及到同步异步操作
	* IO
		* 字节流 
		* 文件流
		* 缓冲流
	* Exception java异常的产生
	
	
## JVM

* 认识java虚拟机
* 类加载机制 ：静态（双亲委派） ，反射 3种 工厂模式
* 内存模型
* GC （堆栈的含义）
	* 回收算法
	* 垃圾收集器
* 调优


## 设计模式（23种）
* 工厂模式
* 代理模式
* 单例模式

## web开发
* JavaEE开发
	* jsp 页面 九大内置对象
	* session/cookie的使用 session用户登录唯一标识，cookies浏览器缓存用户，常用于用户购物车方面
	* jstl/el表达式 使用$来取值
* web开发常用框架
	* Spring
		* Ioc 控制反转 接口的使用
		* Aop 面向切面编程 
		* 自动装配（注解）@resource @service @autowired等
	* SpringMvc
	* struts
* 常用数据库工具
	* Jdbc （java原生api）
	* hibernate
	* Mybatis （简单使用）通过配置xml文件来访问数据库
* 服务器
	* tomcat （简单的系统）servlet的实现
	* Nginx （分布式）

	
	
	
	
