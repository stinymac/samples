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

who

root pts/1 Apr 8 00:06 (172.29.0.29)
root pts/2 Apr 8 04:15 (172.29.0.21)

2.通知该用户将要关闭他：

echo "I will close your connection" > /dev/pts/2    //这样他的终端将显示该信息。

3.关闭用户连接

fuser -k /dev/pts/2

  
##目录操作

###1.查看目录信息


