## 171860505-黄鑫豪-作业6



#### 作业要求

1. 下载并安装HBase，尝试单机本地模式、伪分布式模式运行、集群模式（可选）。

2. 熟悉基本的HBase Shell操作。

3. 以伪分布式运行HBase，编写Java程序，创建讲义中的students表。

要求：撰写简单的实验报告，并提交代码和截图。



#### 实验记录

这里记录下遇到的**主要问题**：

- Can‘t get the location for replica 0.

  ![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/bug1.png)

- unable to create Zookeeper connection.

  ![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/bug2.png)

以及相应的**解决方案和思考**（由于没有在做的过程中记录下来，所以解决方案没能和问题一一匹配上，可惜）

- hbase 的版本问题，在安装的时候没有发现自己的 hadoop 版本是 3.2.0，记错为 2.8.5，所以前期选取的 hbase 版本不适配，更换后解决了部分问题；

- pom 配置文件中的版本参数要根据本地配置来调整，与网上教程教程不同，要手动修改；

- 单机和伪分布不能混杂起来做，否则 hbase-site.xml 等文件里写的内容就不支持系统的正常运作；

- idea 在 Ubuntu 上也要和在 Windows 上类似地进行完全配置，否则就不能正常运行；

- 在进行伪分布式作业前期，会忘记 hadoop 的 start-all 操作；全部开启后如下：

  ![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/start-all.png)



#### 实验结果

##### 【A】Hbase shell

开启 hbase shell 进行各种指令操作

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/start&shell.png)

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/create.png)

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/drop.png)

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/describe.png)

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/put.png)

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/get.png)

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/scan.png)

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/delete.png)

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/truncate&quit.png)

##### 【B】IDEA 'students'

在 IDEA 中伪分布式运行 JAVA 代码

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/idea.png)

成功创建讲义中的表 “students”

![](https://raw.githubusercontent.com/GB-Tocix/FBDPHomework6/master/res/result.png)