##日常操作命令

1.pwd  查看当前所在的工作目录的全路径 

2.date +%Y-%m-%d  当前系统的时间 date //2019-06-10

  date -s "2016-07-28 16:12:00"  修改时间

3.who  查看当前在线

4.last  查看最近的登陆历史记录

5.clear  清屏/或者用快捷键  ctrl + l

6.ctrl+c  退出当前进程

7.ctrl+z  进程会挂起到后台
  bg jobid   让进程在后台继续执行
  fg jobid   让进程回到前台
  
###linux 关闭SSH 连接用户

1.查明登陆端口；

who 或 w

root pts/1 Apr 8 00:06 (172.29.0.29)
root pts/2 Apr 8 04:15 (172.29.0.21)

2.通知该用户将要关闭他：

echo "I will close your connection" > /dev/pts/2    //这样他的终端将显示该信息。

3.关闭用户连接

fuser -k /dev/pts/2

  
##目录操作

###1.查看目录信息


##查看系统信息常用命令

01.CPU -> cat /proc/cpuinfo | grep "model name" && cat /proc/cpuinfo | grep "physical id" //Physical Processor ID来区分单核和双核
02.内存大小 -> cat /proc/meminfo | grep MemTotal
03.硬盘大小 -> fdisk -l |grep Disk
04.查看内核/操作系统/CPU信息 -> uname -a
05.CPU详细信息 -> cat /proc/cpuinfo
06.计算机名 -> hostname
07.所有PCI设备 -> lspci -tv
08.所有USB设备 -> lsusb -tv
09.加载的内核模块 -> lsmod
10.环境变量资源 -> env
11.内存使用量和交换区使用量 -> free -m
12.查看指定目录的大小 -> du -sh 
13.查看内存总量 -> grep MemTotal /proc/meminfo 
14.查看空闲内存量 -> grep MemFree /proc/meminfo
15.查看系统运行时间、用户数、负载 -> uptime
16.查看系统负载磁盘和分区 -> cat /proc/loadavg
17.查看挂接的分区状态 -> mount | column -t
18.查看所有分区 -> fdisk -l
19.查看所有交换分区 -> wapon -s
20.查看磁盘参数(仅适用于IDE设备) -> dparm -i /dev/hda
21.查看启动时IDE设备检测状况网络 -> mesg | grep IDE
22.查看所有网络接口的属性 -> ifconfig
23.查看防火墙设置 -> iptables -L
24.查看路由表 -> route -n
25.查看所有监听端口 -> netstat -lntp
26.查看所有已经建立的连接 -> netstat -antp
27.查看网络统计信息进程 -> netstat -s
28.查看所有进程 -> ps -ef
29.实时显示进程状态用户 -> top
30.各分区使用情况 -> df -h
31.查看指定用户信息 -> id
32.查看用户登录日志 -> last
33.查看系统所有用户 -> cut -d: -f1 /etc/passwd
34.查看系统所有组 -> cut -d: -f1 /etc/group
35.查看当前用户的计划任务服务 -> crontab -l
36.列出所有系统服务 -> chkconfig –list
37.列出所有启动的系统服务程序 ->chkconfig –list | grep on
38.查看所有安装的软件包 -> rpm -qa
39.查看linux硬盘和分区信息 -> cat /proc/partitions
40.查看linux系统内存信息 -> cat /proc/meminfo
41.查看版本 -> cat /proc/version  //类似uname -r
42.查看设备io端口 -> cat /proc/ioports
43.查看中断 -> cat /proc/interrupts
44.查看pci设备的信息 -> cat /proc/pci
45.查看所有swap分区的信息 -> cat /proc/swaps

