# File

> 文件函数

    //判断文件是否存在  
    myFile.exists()
    //读取文件名称  
    myFile.getName()
    //读取文件路径(相对路径)  
    myFile.getPath()
    //读取文件绝对路径  
    myFile.getAbsolutePath()
    //读取文件的父级路径  
    new File(myFile.getAbsolutePath()).getParent()
    //读取文件的大小  
    myFile.length()
    //判断文件是否被隐藏  
    myFile.isHidden()
    //判断文件是否可读  
    myFile.canRead()
    //判断文件是否可写  
    myFile.canWrite()
    //判断文件是否为文件夹  
    myFile.isDirectory()

> 字节流和字符流概念
```text
字节流：字节流读取的时候，读到一个字节就返回一个字节；主要用于读取图片，MP3，AVI视频文件。
字符流：字符流使用了字节流读到一个或多个字节，如读取中文时，就会一次读取2个字节。只要是处理纯文本数据，就要优先考虑使用字符流。
```

> 字节流和字符流区别
```text
字节流操作的基本单元为字节；字符流操作的基本单元为Unicode码元。
字节流默认不使用缓冲区；字符流使用缓冲区。
字节流通常用于处理二进制数据，实际上它可以处理任意类型的数据，但它不支持直接写入或读取Unicode码元；字符流通常处理文本数据，它支持写入及读取Unicode码元。
```

> 写文件对比
```text
FileWriter效率最高，BufferedOutputStream次之，FileOutputStream，实际中常用BufferedWriter和PrintWriterFileWriter，其使用的也是FileWriter
```