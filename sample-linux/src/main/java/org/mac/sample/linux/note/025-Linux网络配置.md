###linux网络环境配置

    指定固定的ip
    
    说明
    直接修改配置文件来指定IP,并可以连接到外网,
    编辑 vi /etc/sysconfig/network-scripts/ifcfg-eth0
    
    ifcfg-eth0文件说明
    
    DEVICE=eth0 #接口名（设备,网卡）
    HWADDR=00:0C:2x:6x:0x:xx #MAC地址
    TYPE=Ethernet #网络类型（通常是Ethemet）
    UUID=926a57ba-92c6-4231-bacb-f27e5e6a9f44 #随机id
    #系统启动的时候网络接口是否有效（yes/no）
    ONBOOT=yes 
    # IP的配置方法[none|static|bootp|dhcp]（引导时不使用协议|静态分配IP|BOOTP协议|DHCP协议）
    BOOTPROTO=static
    #IP地址
    IPADDR=192.168.184.130 
    #网关
    GATEWAY=192.168.184.2 
    #域名解析器
    DNS1=192.168.184.2
    
    重启网络服务或者重启系统生效
    service network restart 、reboot