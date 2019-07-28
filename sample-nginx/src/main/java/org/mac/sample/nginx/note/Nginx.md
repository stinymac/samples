###什么是Nginx

    Nginx是一个开源的高性能且可靠的HTTP中间件、代理服务。
    其他常见的HTTP服务包括
    
    HHTPD (Apache)
    IIS   (MicroSoft)
    GWS   (Google)
    
###Nginx的优势

    采用IO多路复用epoll
    轻量级、模块化
    CPU亲和(affinity)-把Nginx的工作进程和CPU核心绑定,即把每个worker进程固定到一个CUP核心,
                      减少CPU切换的消耗,获得更好性能。
    sendfile
    
###Nginx安装(yum方式)

    cd /etc/yum.repos.d/
   
    vim nginx.repo
   
    从官网copy
    
    [nginx-stable]
    name=nginx stable repo
    baseurl=http://nginx.org/packages/centos/$releasever/$basearch/
    gpgcheck=1
    enabled=1
    gpgkey=https://nginx.org/keys/nginx_signing.key
    
    到nginx.repo文件中 并修改$releasever = 对应的版本(这里是7)
    
    yum install nginx
    
    
###Nginx的安装目录和文件详解
    
    查看安装信息
    rpm -ql nginx
    
    /etc/logrotate.d/nginx
    /etc/nginx
    /etc/nginx/conf.d
    /etc/nginx/conf.d/default.conf
    /etc/nginx/fastcgi_params
    /etc/nginx/koi-utf
    /etc/nginx/koi-win
    /etc/nginx/mime.types
    /etc/nginx/modules
    /etc/nginx/nginx.conf
    /etc/nginx/scgi_params
    /etc/nginx/uwsgi_params
    /etc/nginx/win-utf
    /etc/sysconfig/nginx
    /etc/sysconfig/nginx-debug
    /usr/lib/systemd/system/nginx-debug.service
    /usr/lib/systemd/system/nginx.service
    /usr/lib64/nginx
    /usr/lib64/nginx/modules
    /usr/libexec/initscripts/legacy-actions/nginx
    /usr/libexec/initscripts/legacy-actions/nginx/check-reload
    /usr/libexec/initscripts/legacy-actions/nginx/upgrade
    /usr/sbin/nginx
    /usr/sbin/nginx-debug
    /usr/share/doc/nginx-1.16.0
    /usr/share/doc/nginx-1.16.0/COPYRIGHT
    /usr/share/man/man8/nginx.8.gz
    /usr/share/nginx
    /usr/share/nginx/html
    /usr/share/nginx/html/50x.html
    /usr/share/nginx/html/index.html
    /var/cache/nginx
    /var/log/nginx
    
    
    /etc/logrotate.d/nginx                        配置文件 nginx日志轮转,用于logrotate服务的日志切割

    /etc/nginx                                    目录、配置文件   nginx的主配置文件
    /etc/nginx/nginx.conf                           
    /etc/nginx/conf.d          
    /etc/nginx/conf.d/default.conf
    
    
    /etc/nginx/fastcgi_params                      配置文件     cgi配置相关
    /etc/nginx/scgi_params
    /etc/nginx/uwsgi_params
    
    
    /etc/nginx/koi-utf                             配置文件     编码转化映射文件
    /etc/nginx/koi-win
    /etc/nginx/win-utf
    
    /etc/nginx/mime.types                          配置文件     设置http协议的Content-Type与扩展名的对应关系
    
    
    /usr/lib/systemd/system/nginx-debug.service    配置文件     Centos7之后的系统守护进程管理器管理方式
    /usr/lib/systemd/system/nginx.service
    /etc/sysconfig/nginx
    /etc/sysconfig/nginx-debug
    
    /etc/nginx/modules                             目录         Nginx模块目录
    /usr/lib64/nginx/modules
    
    
    /usr/sbin/nginx                                命令         Nginx服务启动停止管理命令
    /usr/sbin/nginx-debug
    
    
    /usr/share/doc/nginx-1.16.0                    目录 文件    帮助文档 手册
    /usr/share/doc/nginx-1.16.0/COPYRIGHT 
    /usr/share/man/man8/nginx.8.gz
    
    
    /var/cache/nginx                               目录         默认缓存目录
   
    /var/log/nginx                                 目录         日志目录
  
  
###Nginx安装编译参数

    查看安装编译参数
    
    nginx -V  
    
    nginx version: nginx/1.16.0
    built by gcc 4.8.5 20150623 (Red Hat 4.8.5-36) (GCC) 
    built with OpenSSL 1.0.2k-fips  26 Jan 2017
    TLS SNI support enabled
    configure arguments: 
    --prefix=/etc/nginx 
    --sbin-path=/usr/sbin/nginx 
    --modules-path=/usr/lib64/nginx/modules 
    --conf-path=/etc/nginx/nginx.conf 
    --error-log-path=/var/log/nginx/error.log 
    --http-log-path=/var/log/nginx/access.log 
    --pid-path=/var/run/nginx.pid 
    --lock-path=/var/run/nginx.lock 
    
    --http-client-body-temp-path=/var/cache/nginx/client_temp 
    --http-proxy-temp-path=/var/cache/nginx/proxy_temp 
    --http-fastcgi-temp-path=/var/cache/nginx/fastcgi_temp 
    --http-uwsgi-temp-path=/var/cache/nginx/uwsgi_temp 
    --http-scgi-temp-path=/var/cache/nginx/scgi_temp 
    
    --user=nginx 
    --group=nginx 
    
    --with-compat 
    --with-file-aio 
    --with-threads 
    --with-http_addition_module 
    --with-http_auth_request_module 
    --with-http_dav_module 
    --with-http_flv_module 
    --with-http_gunzip_module 
    --with-http_gzip_static_module 
    --with-http_mp4_module 
    --with-http_random_index_module 
    --with-http_realip_module 
    --with-http_secure_link_module 
    --with-http_slice_module 
    --with-http_ssl_module 
    --with-http_stub_status_module 
    --with-http_sub_module 
    --with-http_v2_module 
    --with-mail 
    --with-mail_ssl_module 
    --with-stream 
    --with-stream_realip_module 
    --with-stream_ssl_module 
    --with-stream_ssl_preread_module
     
    --with-cc-opt='-O2 -g -pipe -Wall -Wp,-D_FORTIFY_SOURCE=2 -fexceptions -fstack-protector-strong 
    --param=ssp-buffer-size=4 -grecord-gcc-switches -m64 -mtune=generic -fPIC' 
    --with-ld-opt='-Wl,-z,relro -Wl,-z,now -pie'
    
    
    安装目的目录等
    --prefix=/etc/nginx                        
    --sbin-path=/usr/sbin/nginx 
    --modules-path=/usr/lib64/nginx/modules 
    --conf-path=/etc/nginx/nginx.conf 
    --error-log-path=/var/log/nginx/error.log 
    --http-log-path=/var/log/nginx/access.log 
    --pid-path=/var/run/nginx.pid 
    --lock-path=/var/run/nginx.lock 
    
    Nginx运行时临时文件存放目录
    --http-client-body-temp-path=/var/cache/nginx/client_temp 
    --http-proxy-temp-path=/var/cache/nginx/proxy_temp 
    --http-fastcgi-temp-path=/var/cache/nginx/fastcgi_temp 
    --http-uwsgi-temp-path=/var/cache/nginx/uwsgi_temp 
    --http-scgi-temp-path=/var/cache/nginx/scgi_temp 
    
     Nginx运行的用户和用户组
    --user=nginx 
    --group=nginx 
    
    设置额外参数将被添加到CFLAGS变量 
    --with-cc-opt='-O2 -g -pipe -Wall -Wp,-D_FORTIFY_SOURCE=2 -fexceptions -fstack-protector-strong 
    
    设置附加参数 链接系统库
    --with-ld-opt='-Wl,-z,relro -Wl,-z,now -pie'
    
###Nginx配置文件及其配置语法

    查看配置文件
    vim /etc/nginx/nginx.conf
    
    其中 include /etc/nginx/conf.d/*.conf; 引入了conf.d下的默认配置
    
    [nginx.conf]
    
    #Nginx运行的系统用户
    user  nginx;
    #工作进程数
    worker_processes  1;
    
    #错误日志
    error_log  /var/log/nginx/error.log warn;
    #工作时的PID
    pid        /var/run/nginx.pid;
    
    #
    events {
        #每个进程允许的最大连接数
        worker_connections  1024;
    }
    
    
    http {
        include       /etc/nginx/mime.types;
        default_type  application/octet-stream;
    
        log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                          '$status $body_bytes_sent "$http_referer" '
                          '"$http_user_agent" "$http_x_forwarded_for"';
    
        access_log  /var/log/nginx/access.log  main;
    
        sendfile        on;
        #tcp_nopush     on;
    
        keepalive_timeout  65;
    
        #gzip  on;
    
        include /etc/nginx/conf.d/*.conf;
    }
    
    [default.conf]
    server {
        listen       80;
        server_name  localhost;
    
        #charset koi8-r;
        #access_log  /var/log/nginx/host.access.log  main;
    
        location / {
            root   /usr/share/nginx/html;
            index  index.html index.htm;
        }
    
        #error_page  404              /404.html;
    
        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   /usr/share/nginx/html;
        }
    
        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}
    
        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}
    
        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }

/***********************************************************************
###CentOS7查看和关闭防火墙

    查看防火墙状态
    firewall-cmd --state

    停止firewall
    systemctl stop firewalld.service

    禁止firewall开机启动
    systemctl disable firewalld.service 

    关闭selinux 
    
    进入到/etc/selinux/config文件
    vi /etc/selinux/config

    SELINUX=enforcing改为SELINUX=disabled

***********************************************************************/

###Nginx虚拟主机

    Nginx下，一个server标签就是一个虚拟主机。可以使用三种方式实现虚拟主机
    
    1、基于域名的虚拟主机，通过域名来区分虚拟主机
    2、基于端口的虚拟主机，通过端口来区分虚拟主机
    3、基于ip的虚拟主机
    
    eg.基于域名的虚拟主机配置
    
    本地hosts添加服务ip地址对应的域名解析
    192.168.0.88 www.code.com
    192.168.0.88 www.blog.com
    
    建立对应目录
    /data/code 
    /data/blog
    
    目录下添加index.html文件
    
    配置nginx server
    
    server {
       listen 80;
       server_name www.code.com;
       index index.html;
       root /data/code ;
    }
    server {
       listen 80;
       server_name www.blog.com;
       index index.html;
       root /data/blog;
    }
    
    
###Nginx日志
    
    error.log
    access.log
    
    log_format配置
    
    Syntax : log_format name [escape = default|json] string ...;
    Default: log-format combined "...";
    Context: http
    
###Nginx变量

    HTTP请求变量 arg_PARAMETER http_HEADER sent_http_HEADER
                eg. $http_user_agent
                
    内置变量  nginx内置的变量
    
    @see http://nginx.org/en/docs/http/ngx_http_core_module.html
    
    '$remote_addr - $remote_user [$time_local] "$request" '
    '$status $body_bytes_sent "$http_referer" '
    '"$http_user_agent" "$http_x_forwarded_for"';
    
    自定义变量
    
    
###Nginx模块

    nginx -V 可以查看Nginx开启的模块
    
    
     --with-compat 
     --with-file-aio 
     --with-threads 
     --with-http_addition_module 
     --with-http_auth_request_module 
     --with-http_dav_module 
     --with-http_flv_module 
     --with-http_gunzip_module 
     --with-http_gzip_static_module 
     --with-http_mp4_module 
     --with-http_random_index_module 
     --with-http_realip_module 
     --with-http_secure_link_module 
     --with-http_slice_module 
     --with-http_ssl_module 
     --with-http_stub_status_module 
     --with-http_sub_module 
     --with-http_v2_module 
     --with-mail 
     --with-mail_ssl_module 
     --with-stream 
     --with-stream_realip_module 
     --with-stream_ssl_module 
     --with-stream_ssl_preread_module
     
     模块                           作用
     --with-http_stub_status_module Nginx的客户端状态
     
     配置
     Syntax : stub_status;     
     Default: -               默认未配置
     Context: server,location 配置上下文(可以在何处配置)
     
     eg.
     location /show-stub-status {
          stub_status;
     }
     
     访问/show-stub-status 响应:
     
     Active connections: 4 
     server accepts handled requests
      5 5 3 
     Reading: 0 Writing: 1 Waiting: 3
     
      模块                            作用
     --with-http_random_index_module  目录中选择一个随机主页
     
     配置
     Syntax : random_index on|off;     
     Default: random_index off;               
     Context: location 
     
     
     模块                              作用
     --with-http_sub_module            http内容替换
     
     配置
     Syntax : sub_filter string replacement;    
     Default: -;               
     Context: http server location 
     
     Syntax : sub_filter_last_modified on|off;     
     Default: sub_filter_last_modified off;               
     Context: http server location 
     
     Syntax : sub_filter_once on|off;     
     Default: sub_filter_once on;               
     Context: http server location 
     
###Nginx请求限制

    连接频率的限制 - limit_conn_module
    请求频率的限制 - limit_req_module
    
    连接频率的限制配置:
    Syntax : limit_conn_zone key zone=name:size;     
    Default: -;               
    Context: http
     
    Syntax : limit_conn key zone number;     
    Default: -;               
    Context: http server location
    
    请求频率的限制配置:
    
    Syntax : limit_req_zone key zone=name:size rate=rate;     
    Default: -;               
    Context: http
    
    Syntax : limit_req zone=name [burst=number] [nodelay];     
    Default: -;               
    Context: http server location
    
    
    eg.
    
    1.配置限制
    [default.conf]
    
    limit_conn_zone $binary_remote_addr zone=conn_zone:1m;
    limit_req_zone  $binary_remote_addr zone=req_zone:1m rate=1r/s;
    server {
        listen       80;
        server_name  localhost;
    
    
        location / {
            root   /usr/share/nginx/html;
            #limit_conn conn_zone 1;
            #limit_req  zone=req_zone burst=3 nodelay; // 3个延迟到下一秒 其他的不延迟
            #limit_req  zone=req_zone burst=3;
            limit_req  zone=req_zone;
            index  index.html index.htm;
        }
    
       
        location = /50x.html {
            root   /usr/share/nginx/html;
        }
    }
    2.测试
    ab -n 50 -c 20 http://localhost/index.html
    [
    ......
    Concurrency Level:      20
    Time taken for tests:   0.009 seconds
    Complete requests:      50
    Failed requests:        49
       (Connect: 0, Receive: 0, Length: 49, Exceptions: 0)
    Write errors:           0
    Non-2xx responses:      49
    ......
    ]
    
###Nginx访问控制

    基于IP的访问控制 - http_access_module
    基于用户的信任登录 - http_auth_basic_module
    
    
    基于IP的访问控制配置
    
    Syntax : allow address | CIDR | unix:| all ;     
    Default: -;               
    Context: http server location limit_except
    
    Syntax : deny address | CIDR | unix:| all ;     
    Default: -;               
    Context: http server location limit_except
    
    eg.
    
    location ~ ^/admin.html {
        root   /usr/share/nginx/html;
        allow  112.96.176.39;    
        deny all;      
    }
    
    基于IP的访问控制局限性
    
    由于基于remote_addr 在客户端使用代理等访问服务端时 无法完成访问控制
    
    解决方法:
    1.使用http_x_forward_for
    2.结合geo模块
    3.通过http自定义变量
    
    基于用户的信任登录配置
    
    
    Syntax : auth_basic string | off ;     
    Default: auth_basic off;               
    Context: http server location limit_except
    
    Syntax : auth_basic_use_file file ;     
    Default: -;               
    Context: http server location limit_except
    
     eg.
        
     location ~ ^/admin.html {
         root   /usr/share/nginx/html;
         auth_basic "login"
         auth_basic_use_file /etc/nginx/auth_conf // 可以使用htpasswd工具生成 或查询使用官网支持的其他方式      
     }
     
     基于用户的信任登录局限性
     
     依赖文件
     
     解决方法:
     
     1.Nginx结合LUA
     2.利用nginx-auth-ldap模块
    