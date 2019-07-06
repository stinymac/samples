###Linux组基本介绍

    linux中的每个用户必须属于一个组，不能独立于组外。
    linux中每个文件有所有者、所在组、其它组的概念。
    
###文件/目录 所有者
    一般为文件的创建者,谁创建了该文件，就自然的成为该文件的所有者。
    
####查看文件的所有者

    ls –ahl
    
    groupadd police
    useradd -g police tom
    passwd tom
    // chang user tom
    touch ok.txt
    ls –ahl
    
####修改文件所有者

    指令：chown 用户名 文件名
    
    使用root 创建一个文件apple.txt ，然后将其所有者修改成tom
    
    touch apple.txt 
    chown tom apple.txt  // 组不变
    
###组的创建
   
    基本指令
    groupadd 组名
    
    创建一个组 monster 
    groupadd monster
    创建一个用户 fox , 并放入到 monster组中
    useradd -g monster fox
    
###文件/目录 所在组
    
    当某个用户创建了一个文件后，这个文件的所在组就是该用户所在的组。
    
    查看文件/目录所在组
   
    ls –ahl 
   
    修改文件所在的组
   
    chgrp 组名 文件名 
    
    使用root用户创建文件 orange.txt ,看看当前这个文件属于哪个组，
    然后将这个文件所在组，修改到 fruit组。
    
    chgrp fruit orange.txt
    
###其它组
    除文件的所有者和所在组的用户外，系统的其它用户都是文件的其它组。
    
    
###改变用户所在组
    在添加用户时，可以指定将该用户添加到哪个组中，同样的用root的管理权限可以改变某
    个用户所在的组。
    
    改变用户所在组
    1) usermod –g 组名   用户名
       
       usermod -g bandit tom
       
    2) usermod –d 目录名 用户名 //改变该用户登陆的初始目录。
    