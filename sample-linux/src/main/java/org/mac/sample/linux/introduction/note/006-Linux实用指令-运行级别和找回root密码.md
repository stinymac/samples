###运行级别
####基本介绍:
    运行级别说明：
    0 ：关机
    1 ：单用户【找回丢失密码】 2：多用户状态没有网络服务
    3：多用户状态有网络服务
    4：系统未使用保留给用户
    5：图形界面
    6：系统重启
    常用运行级别是3和5 ，要修改默认的运行级别可改文件
    /etc/inittab的id:5:initdefault:这一行中的数字
    命令：init [012356]
    
###找回root密码

    开机 -> 引导时按Enter键 -> 进入界面输入 e 进入编辑界面选择编辑内核选项->
    再次输入 e 进入编辑 然后输入 1 (即单用户模式) -> 按 Enter键后输入 b 启动
    进入单用户模式
    
    使用passwd 修改roo用户密码