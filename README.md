# PluginEmpowerSystem
这是一款加密jar包和远程端授权密匙的控制程序

###### 启动程序
`java -jar Empower.jar`

###### 启动加密jar包
`java -jar Empower.jar jar filename key_file`

* filename默认相对路径为何Empower.jar同级

* key_file如果要指向keyfile文件夹，则需要变量指向

######里面的指令

输入`help`可以查询命令

######建议
* 启动加密程序时，不建议在程序内部输入指令
###### 其他小特性
* 内置变量
    * ${jarin} 本jar包所在的文件夹路径
    * ${keyfile} 生成key文件的文件夹
    * ${gclass} 打包的根目录
* 声明变量
    * $name=value
* 调用
    * ${name}

导入api
```xml
<project>
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
</project>
        
```
api都在api包里




