# PluginEmpowerSystem
这是一款加密jar包和远程端授权密匙的控制程序

###### 启动程序
`java -jar Empower.jar`

######里面的指令

输入`help`可以查询命令

###### 其他小特性
* 变量和路径
加入了keyfile的概念，可以将key文件放在这里进行解析

如果在解密或者加密时直到你的key文件在哪里

    > $keyfile={keyfile}

    > eclass ${keyfile}/xx.key xx.class

都可以这样做，除了生成密钥不能这样做，因为它默认是在{jarfile}生成，即和jar包同级的地方

声明变量:$key=value

使用变量:echo ${名字}后面要拼接直接写即可，也可以后续加变量

将解析class文件的路径设置为

/classes/

另外，application文件路径设置在

/application.properties

另外使用前缀classpath:可以指定到项目资源文件

如

classpath:application.properties

使用now: 前缀表示指向当前jar包所在目录

now:application.properties

内置变量
jarin和keyfile，可以直接调用

导入api
```xml
        <dependency>
            <groupId>net.noyark</groupId>
            <artifactId>empower-classcoder</artifactId>
            <version>0.0.2</version>
        </dependency>

    <repositories>
        <repository>
            <id>nexus</id>
            <name>Team Neux Repository</name><url>http://www.noyark.net:8081/nexus/content/groups/public/</url>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <id>nexus</id>
            <name>Team Neux Repository</name>
            <url>http://www.noyark.net:8081/nexus/content/groups/public/</url>
        </pluginRepository>
    </pluginRepositories>
```
api都在api包里




