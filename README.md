##  System.getProperty(“”),可以操作一下参数：
 * java.version:Java运行时环境版本
 * java.vendor:Java运行时环境供应商
 * java.vendor.url:Java供应商的 URL
 * java.home:Java安装目录
 * java.vm.specification.version:Java虚拟机规范版本
 * java.vm.specification.vendor:Java虚拟机规范供应商
 * java.vm.specification.name:Java虚拟机规范名称
 * java.vm.version:Java虚拟机实现版本
 * java.vm.vendor:Java虚拟机实现供应商
 * java.vm.name:Java虚拟机实现名称
 * java.specification.version:Java运行时环境规范版本
 * java.specification.vendor：Java运行时环境规范供应商
 * java.specification.name：Java运行时环境规范名称
 * java.class.version：Java类格式版本号
 * java.class.path：Java类路径
 * java.library.path：加载库时搜索的路径列表
 * java.io.tmpdir：默认的临时文件路径
 * java.compiler：要使用的 JIT 编译器的名称
 * java.ext.dirs：一个或多个扩展目录的路径
 * os.name：操作系统的名称
 * os.arch：操作系统的架构
 * os.version：操作系统的版本
 * file.separator：：路径分隔符（在 UNIX 系统中是“:”）
 * line.separator：行分隔符（在 UNIX 系统中是“/n”）
 * user.name：用户的账户名称
 * user.home：用户的主目录
 * user.dir：用户的当前工作目录
 
 # springboot 多环境配置文件加载
 在日常开发过程中，我们可能会使用多环境模式：开发、测试、生产。此时怎么配置来指定使用对应环境的配置文件呢？
 springboot支持配置多环境下指定对应的配置文件。
 比如application-dev.yaml/application-prod.yaml/application-test.yaml分别为开发、生产、测试对应的配置文件：
        
 * application.yaml通过spring.profiles.active: dev来指定默认加载哪一个配置文件。
 * 使用java -jar启动应用程序时可以使用：java -jar java-note.jar -spring.profiles.active=dev
 * 虚拟机参数：-Dspring.profiles.active=dev    优先级比配置文件高
 