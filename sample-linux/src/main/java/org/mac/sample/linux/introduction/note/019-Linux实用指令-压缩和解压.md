###gzip/gunzip 指令
    gzip 用于压缩文件， gunzip 用于解压的
    
    基本语法
    gzip 文件      （功能描述：压缩文件，只能将文件压缩为*.gz文件）
    gunzip 文件.gz （功能描述：解压缩文件命令）
    
###zip/unzip 指令
    zip 用于压缩文件， unzip 用于解压的，这个在项目打包发布中很有用的
    
    基本语法
    
    zip   [选项] XXX.zip 将要压缩的内容（功能描述：压缩文件和目录的命令）
    unzip [选项] XXX.zip              （功能描述：解压缩文件）
    
    zip常用选项
    -r：递归压缩，即压缩目录
    
    unzip的常用选项
    -d<目录> ：指定解压后文件的存放目录
    
    将 /home下的 所有文件进行压缩成 mypackage.zip
    
    zip -r  mypackage.zip /home/
    
    将 mypackge.zip 解压到 /opt/tmp 目录下
    
    zip -d  /opt/tmp mypackage.zip
    
###tar 指令
    tar 指令 是打包指令，最后打包后的文件是 .tar.gz 的文件。
    
    基本语法
    tar [选项] XXX.tar.gz 打包的内容 (功能描述：打包目录，压缩后的文件格式.tar.gz)
    
    选项说明
   
    -c 产生.tar打包文件
    -v 显示详细信息
    -f 指定压缩后的文件名
    -z 打包同时压缩
    -x 解包.tar文件
    
    
    tar -zcvf a.tar.gz a1.txt a2.txt
    tar -zcvf home.tar.gz /home/
    
    tar -zxvf home.tar.gz
    tar -zxvf home.tar.gz -C /opt/tmp
    
    