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



