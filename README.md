# PluginEmpowerSystem
这是一款加密jar包和远程端授权密匙的控制程序

###### 启动程序
`java -jar Empower.jar`

######里面的指令
在开启PES服务时，可以运行指令，下面是指令文档
* 指令
    *参数
* cckey 用于生成密钥文件
    * key 生成密钥的名字
* suser 设置连接账户
    * username 账户名
* spwd 设置数据库密码
    * pwd 密码
* connect 连接远端密钥数据库
    * -f filename 直接导入标准的配置文件
    * ip dbname 默认为mysql数据库
    * ip dbname type 选择数据库类型，有mysql oracle sqlserver
* compare 用于判断这个密钥是否存在
    * key ip port
        * 注意远端key是一个uuid序列码，未来会提供自定义的序列码选择
* ctable 创建标准的key存储表
    * tablename
* dcclass 将代码在运行过程中解码，并确定key是否符合要求，即有授权
    * ip port key classkeyfilename classname...
    * ip port -f filename keyname classkeyfilename classname...
* dclass 解密指定class文件 并执行全部main方法，在内存解密
    * key className className要带包名+类名
* declassall 将所有指定application classpath下的全部class文件解密，在内存解密
    * mainclass keyfile 指定主运行类和解密key文件
* eclass 加密指定class文件，覆盖原来的编译文件
    * keyfile classname...
* enclassall 将application classpath下全部class文件加密，覆盖原来的编译文件
    * mainclass keyfile
* exit 推出程序
* random 随机生成序列码 并插入到设置好的数据库
    * number 个数
* save 将数据库配置保存在硬盘，下一次可以通过connect -f直接读取
* sppf 指定application.properties配置文件，里面指定了classpath
    * application.properties字段
        * classpath 指定了第一级包所在的根目录
* stable 设置插入随机值的表
    * tablename
* keyfile 获取放key文件的地方
* jarin 获取jar包所在绝地路径
* echo 用于输出变量
    * $key=value,{jarin}输出jar包所在文件夹的绝对路径,{keyfile}输出解析key文件所在路径
###### 提供的api
empower提供了一堆的api，用于辅助在代码端实现功能，主要分为
* 本地加密端的服务
    * SimpleClassCoder
        * void encode(String fileName,String... classname)
            * 将class文件加密，覆盖原来class文件，现在可以指定文件
        * void decode() 解码
        * recursiveEncode和recursiveDecode可以将全部application classpath下的class文件加载
        * 

* 远程授权端的服务

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

内置变量
jarin和keyfile，可以直接调用



