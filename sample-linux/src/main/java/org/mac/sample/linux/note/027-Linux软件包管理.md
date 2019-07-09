###rpm包的管理

    一种用于互联网下载包的打包及安装工具，它包含在某些Linux分发版中。它生成
    具有.RPM扩展名的文件。RPM是RedHat Package Manager（RedHat软件包管理工
    具）的缩写，类似windows的setup.exe，这一文件格式名称虽然打上了RedHat的
    标志，但理念是通用的。
    Linux的分发版本都有采用（suse,redhat, centos 等等），可以算是公认的行业标
    准了。
    
####rpm包的简单查询指令

    查询已安装的rpm列表 rpm –qa|grep xx
    
    rpm包名基本格式： 一个rpm包名：firefox-45.0.1-1.el6.centos.x86_64.rpm
    名称:firefox
    版本号：45.0.1-1
    适用操作系统: el6.centos.x86_64 
    表示centos6.x的64位系统
    如果是i686、i386表示32位系统，noarch表示通用。
    
####rpm包的其它查询指令
    rpm -qa :查询所安装的所有rpm软件包 
    rpm -qa | more 
    rpm -qa | grep X [rpm -qa | grep firefox ]
    rpm -ql 软件包名 :查询软件包中的文件
    rpm -ql firefox
    rpm -qf 文件全路径名 查询文件所属的软件包
    rpm -qf /etc/passwd
    rpm -qf /root/install.log
    rpm -q 软件包名 :查询软件包是否安装 
    rpm -q firefox
    rpm -qi 软件包名 ：查询软件包信息
    rpm -qi file
    
####卸载rpm包

    基本语法
    rpm -e RPM包的名称
    
    如果其它软件包依赖于要卸载的软件包，卸载时则会产生错误信息。
    
    可以增加参数 --nodeps ,就可以强制删除，但是一般不推荐这样做,
    因为依赖于该软件包的程序可能无法运行
    
####安装rpm包

    基本语法
    rpm -ivh RPM包全路径名称
    
    参数说明
    i=install 安装
    v=verbose 提示
    h=hash 进度条

###yum

    Yum 是一个Shell前端软件包管理器。
    基于RPM包管理，能够从指定的服务器自动下载RPM包并且安装，
    可以自动处理依赖性关系，并且一次安装所有依赖的软件包。
    
    
####yum的基本指令

    查询yum服务器是否有需要安装的软件
    yum list|grep xx软件列表
    
    安装指定的yum包
    yum install xxx 下载安装