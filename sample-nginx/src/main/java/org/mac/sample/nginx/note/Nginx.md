##Nginx简介

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
  
##Nginx使用实践

###Nginx静态资源WEB服务

    静态资源服务场景-CDN
    
    Nignx节点配置
    
    Syntax : sendfile on | off ;     
    Default: sendfile off;               
    Context: http,server,location,if in location
    
     // sendfile on 时才可以使用
    Syntax : tcp_nopush  on | off ;     
    Default: tcp_nopush  off;               
    Context: http,server,location
   
    Syntax : tcp_nodelay  on | off ;     
    Default: tcp_nodelay  off;               
    Context: http,server,location
    
    //压缩
    Syntax : gzip  on | off ;     
    Default: gzip  off;               
    Context: http,server,location;
    
    Syntax : gzip_comp_level  level ;     
    Default: gzip_comp_level  1 ;               
    Context: http,server,location
    
    Syntax : gzip_http_version  1.0 | 1.1 ;     
    Default: gzip_http_version  1.1 ;               
    Context: http,server,location
    
    [static_server.conf]
    
    server {
        listen 80;
        server_name static.mac.com;
        
        sendfile on;
        access_log /var/log/nginx/log/static_access.log main;
        
        location ~ .*\.(jpg|gif|png)$ {
            gizp on;
            gzip_http_version  1.1;
            gzip_comp_level  2;
            gzip_types text/plain application/javascript  apllication/x-javascript text/css application/xml
            text/javascript application/x-httpd-php image/jpeg image/gif image/png;  
            root /opt/static/images;
        }
        
        location ~ .*\.(txt|xml)$ {
            gizp on;
            gzip_http_version  1.1;
            gzip_comp_level  1;
            gzip_types text/plain application/javascript  apllication/x-javascript text/css application/xml
                    text/javascript application/x-httpd-php image/jpeg image/gif image/png;       
            root /opt/static/doc;
        }
        location ~ ^/download {
            gizp_static on; // giz预读
            tcp_nopush on;
            root /opt/static/download;
        }
    }
    
    静态资源服务场景-浏览器缓存
    
    1 校验过期机制
    
    校验是否过期 Expires Cache-Control(max-age)
    协议中的Etag头信息校验 Etag
    Last-Modified信息校验 Last-Modified
    
    2.配置
    
    添加 Expires Cache-Control头
    
    Syntax : expires  [modified] time;  
             expires epoch|max|off;   
    Default: expires off;               
    Context: http,server,location,if in location
    
    location ~ .*\.(html|htm)$ {
        expires 24; 
        root /opt/static/code;
    }
    
    静态资源服务场景-跨站访问
    
    Syntax : add_header name value [always] ;
    Default: -;               
    Context: http,server,location,if in location
    
    location ~ .*\.(html|htm)$ {
        expires 24; 
        add_header Access-Controll-allow-origin http://code.mac.com;
        add_header Access-Controll-allow-Methods GET,POST,PUT,DELETE,OPTIONS;
        root /opt/static/code;
    }
    
    静态资源服务场景-防盗链
    
    Syntax : valid_referers none|blocked|server_names|string...;
    Default: -;               
    Context: server,location
    
    location ~ .*\.(jpg|gif|png)$ {
        gizp on;
        gzip_http_version  1.1;
        gzip_comp_level  2;
        gzip_types text/plain application/javascript  apllication/x-javascript text/css application/xml
        text/javascript application/x-httpd-php image/jpeg image/gif image/png; 
        valid_referers none blocked http://code.mac.com;
        if ($invalid_referer){
            return 403;
        }
        root /opt/static/images;
    }
    
###代理服务场景-正向/反向代理
    
    Syntax : proxy_pass URL;
    Default: -;               
    Context: location,if in location,limit_expect
    
    Syntax : proxy_buffering on | off;
    Default: proxy_buffering on;               
    Context: http,server,location
    
    Syntax : proxy_redirect default; proxy_redirect off; proxy_redirect redirect replacement;
    Default: proxy_redirect default;               
    Context: http,server,location
    
    Syntax : proxy_set_header field vale;
    Default: proxy_set_header Host $proxy_host;  
             proxy_set_header Connection close;              
    Context: http,server,location
    
    Syntax : proxy_connect_timeout time;
    Default: proxy_connect_timeout 60s;              
    Context: http,server,location
    
    [proxy.conf]
    location / {
        proxy_pass 127.0.0.1:8080;
        
        proxy_redirect default;
        
        proxy_set_header Host $proxy_host;
        proxy_set_header X-Real-IP $remote_addr;
        
        proxy_connect_timeout 30;
        proxy_send_timeout 60;
        proxy_read_timeout 60;
        
        proxy_buffering on;
        proxy_buffer_size 32k;
        proxy_buffers 4 128k;
        proxy_busy_buffers_size 256k;
        proxy_max_temp_file_size 256k; 
    }
    
###负载均衡服务场景
    
    配置语法
    Syntax : upstream name {...};
    Default: -;              
    Context: http
    
    服务在负载均衡调度中的状态
    down             当前server不参与负载均衡调度
    backup           预留备份
    max_fails        允许请求失败的次数
    fail_timeout     经过max_fails失败后 服务暂停时间
    max_conns        限制最大的接受连接数
    
    Nginx负载均衡调度算法
    
    轮询           按时间顺序逐一分配到不同服务
    加权轮询        weight越大 机率越高
    ip_hash        同IP到同一个服务
    url_hash       同URL到同一个服务
    least_conn     服务连接最少 分配连接到对应服务
    hash关键数值    hash自定义key
    
    url_hash
    Syntax : hash key [consistent];
    Default: -;              
    Context: upstream
    
    
###缓存服务场景
    
    配置
    
    Syntax : proxy_cache_path path [levels = levels]
             [user_temp_path = on | off] ......;
    Default: -;              
    Context: http
    
    Syntax : proxy_cache zone | off;
    Default: proxy_cache off;              
    Context: http,server,location
    
    Syntax : proxy_cache_valid [code...] time;
    Default: -;              
    Context: http,server,location
    
    Syntax : proxy_cache_key string;
    Default: proxy_cache_key $scheme$proxy_host$request_uri;           
    Context: http,server,location
    
    
    [proxy_cache.conf]
    proxy_cache_path /opt/app/cache levels = 1:2 keys_zone=mac_cache:10m max_size=10g inactive=60m use_tem_path=off
    server {
        ...
        location / {
            proxy_pass http://code.mac.com;
            proxy_cache mac_cache;
            proxy_cache_valid 200 304 12h;
            proxy_cache_valid any 10m;
            proxy_cache_key $host$uri$is_args$args;
            add_header Nginx-Cache "$upsteam_cache_status";
            
            proxy_next_upstream error timeout invalid_header http_500 http_502 http_503 http_504;
        }
    }
    
###动静分离
    
    server {
        ...
        root /opt/app/code
        
        location ~ \.jsp$ { 
            proxy_pass  http://127.0.0.1:8080;
            index index.html;
        }
        location ~ \.(jpg|jpeg|gif|png)$ {
            expires 1h;
            gzip on;     
        }
    }
    
###rewrite规则
    
    使用场景
    1 URL访问跳转
      页面跳转 兼容支持 展示效果等
    2 SEO优化
    3 维护
      后台维护 流量转发等
    4 安全
    
    配置
    
    Syntax : rewrite regex replacement [flag];
    Default: -;           
    Context: server,location,if
    
    flag
    
    last           停止rewrite检测
    break          停止rewrite检测
    redirect       返回302临时重定向
    permanent      返回301永久重定向
    
    eg
    
    location / {
        rewrite ^/course-(\d+)-(\d+)-(\d+).html  /course/$1/$2/course-$3.html break;
        
        if ($http-user-agent ~* Chrome) {
            rewrite ^/course http://code.mac.com/course.html break;
        }
        
        if (!-f $request_filename) {
            rewrite ^(.*)/$ https://www.baodu.com/$1 redirect;
        }
    }
    
###secure_link_module模块

    1 制定并检查请求链接的真实性，保护资源免遭未授权的访问。
    2 限制链接生效周期
    
    配置
    
    Syntax : secure_link expression;
    Default: -;           
    Context: http,server,location
    
    Syntax : secure_link_md5 expression;
    Default: -;           
    Context: http,server,location
    
    
    location / {
        secure_link $arg_md5,$arg_expires;
        secure_link_md5 "$secure_link_expires$uri";
        
        if ($secure_link = "" {
            return 403;
        }
        
        if ($secure_link = "0" {
            return 410;
        }
    }
    
###geoip_module模块

    基于IP地址匹配MaxMind GeoIP二进制文件 读取IP所在的地域信息
    
    1.区别国内国外的访问做访问规则控制
    2.区别城市地域做访问规则控制
    
    模块安装
    
    yum install nginx_module_geoip
    
    编辑nginx.conf加载安装的nginx_module_geoip模块
    
    load_module "modules/ngx_http_geoip_module.so";
    load_module "modules/ngx_stream_geoip_module.so";
    
    下载geoip数据文件
    http://geolite.maxmind.com/download/geoip/database/GeoLiteCountry/GeoIP.dat.gz
    http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz
    
    解压数据文件
    
    配置
    [geoip.conf]
    
    geoip_country /etc/nginx/geoip/GeoIP.dat;
    geoip_country_city /etc/nginx/geoip/GeoLiteCity.dat;
    
    server {
        ...
        
        location /{
            if ($geo_country_code != CN) {
                return 403;
            }
            ...
        }
        
        location /ip{
            default_type text/plain;
            return 200 "$remote_addr $geo_country_name $geo_country_code $geo_country_city";
        }
    }
    
    
###Nginx使用https服务

    生成密钥和CA证书
    
    1. 生成key密钥
       openssl genrsa -idea -out mac.key 1024
    2. 生成证书签名请求文件(csr文件)
       openssl req -new -key mac.key -out mac.csr
    3. 生成证书签名文件(CA文件)
       openssl x509 -req -days 36500 -in mac.csr -signkey mac.key -out mac.crt
       
    配置
    
    Syntax : ssl on | off;
    Default: ssl off;           
    Context: http,server
    
    Syntax : ssl_certificate file;
    Default: -;           
    Context: http,server
    
    Syntax : ssl_certificate_key file;
    Default: -;           
    Context: http,server
    
    配置苹果要求的HTTPS
    
    openssl req -days 36500 -x509 -sha256 -nodes -newkey rsa:2048 -keyout mac.key -out mac_apple.crt
    
    跳过nginx启动ssl时要求输入密码
    openssl rsa -in ./mac.key -out ./mac_nopass.key
    然后 ssl_certificate_key使用mac_nopass.key
    
    
    https服务优化
    
    1.激活keepalive长连接
    2.设置ssl session缓存
    
###Nginx与Lua

    Nginx+Lua环境
    
    1.LuaJIT
    
    wget http://luajit.org/download/LuaJIT-2.0.2.tar.gz
    
    make install PREFIX=/usr/local/LuaJIT
    
    export LUAJIT_LIB=/usr/local/LuaJIT/lib
    
    export LUAJIT_INC=/usr/local/LuaJIT/include/luajit-2.0
    
   
    2.ngx_devel_kit和lua_nginx_module
    
    cd /opt/download
    
    wget https://github.com/simpl/ngx_devel_kit/archive/v0.3.0.tar.gz
    
    wget https://github.com/openresty/lua-nginx-module/archive/v0.10.9rc7.tar.gz
    
    分别解压、安装
    
    3.重新编译nginx
    
    cd /opt/download
    wget http://nginx.org/download/nginx-1.12.1.tar.gz
    执行解压，后按照如下方式编译：
    ./configure --prefix=/etc/nginx --sbin-path=/usr/sbin/nginx --modules-path=/usr/lib64/nginx/modules --conf-path=/etc/nginx/nginx.conf --error-log-path=/var/log/nginx/error.log --http-log-path=/var/log/nginx/access.log --pid-path=/var/run/nginx.pid --lock-path=/var/run/nginx.lock --http-client-body-temp-path=/var/cache/nginx/client_temp --http-proxy-temp-path=/var/cache/nginx/proxy_temp --http-fastcgi-temp-path=/var/cache/nginx/fastcgi_temp --http-uwsgi-temp-path=/var/cache/nginx/uwsgi_temp --http-scgi-temp-path=/var/cache/nginx/scgi_temp --user=nginx --group=nginx --with-compat --with-file-aio --with-threads --with-http_addition_module --with-http_auth_request_module --with-http_dav_module --with-http_flv_module --with-http_gunzip_module --with-http_gzip_static_module --with-http_mp4_module --with-http_random_index_module --with-http_realip_module --with-http_secure_link_module --with-http_slice_module --with-http_ssl_module --with-http_stub_status_module --with-http_sub_module --with-http_v2_module --with-mail --with-mail_ssl_module --with-stream --with-stream_realip_module --with-stream_ssl_module --with-stream_ssl_preread_module --with-cc-opt='-O2 -g -pipe -Wall -Wp,-D_FORTIFY_SOURCE=2 -fexceptions -fstack-protector-strong --param=ssp-buffer-size=4 -grecord-gcc-switches -m64 -mtune=generic -fPIC' --with-ld-opt='-Wl,-z,relro -Wl,-z,now -pie' 
    --add-module=/opt/download/ngx_devel_kit-0.3.0 --add-module=/opt/download/lua-nginx-module-0.10.9rc7
    make -j 4 && make install
    
    4.加载lua库，加入到ld.so.conf文件
    echo "/usr/local/LuaJIT/lib" >> /etc/ld.so.conf
    然后执行如下命令：
    ldconfig
  
    