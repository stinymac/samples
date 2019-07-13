###查询系统整体磁盘使用情况 

    基本语法
    df -h
    
###查询指定目录的磁盘占用情况 
    基本语法
    du -h /目录
    
    查询指定目录的磁盘占用情况，默认为当前目录 
    -s            指定目录占用大小汇总 
    -h            带计量单位 
    -a            含文件
    --max-depth=1 子目录深度
    -c            列出明细的同时，增加汇总值
    
    du -ach --max-depth=1
    
###磁盘情况-实用指令
    1) 统计/home文件夹下文件的个数
    
    ls -l /home | grep "^-" | wc -l
    
    2) 统计/home文件夹下目录的个数
    
    ls -l /home | grep "^d" | wc -l
    
    3) 统计/home文件夹下文件的个数，包括子文件夹里的
    
    ls -lR /home | grep "^-" | wc -l
    
    4) 统计文件夹下目录的个数，包括子文件夹里的
    
    ls -lR /home | grep "^d" | wc -l
    
    5) 以树状显示目录结构
    
    tree /home