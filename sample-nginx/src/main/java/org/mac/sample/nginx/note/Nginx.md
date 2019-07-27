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