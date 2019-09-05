###Gradle的组成

    Groovy核心语法
    
    build script block
    
    gradle API
    
###Gradle的执行流程
    
    Initialization 初始化阶段
        
        解析整个工程中所有的Project
    
    Configuration 配置阶段
        
        解析所有project中的task 构建task的拓扑图
    
    Execution  执行阶段
    
        执行具体的task及其依赖的task
    
####Gradle生命周期监听

    /**配置阶段开始前 this.gradle.beforeProject {}*/
    this.beforeEvaluate {println '配置阶段开始前...'}
    
    /**配置阶段完成 this.gradle.afterProject {}*/
    this.afterEvaluate {println '配置阶段完成...'}
    
    /**gradle执行完成后*/
    this.gradle.buildFinished {println 'gradle执行完成后...'}
    
    
###Gradle Project
    
    Gradle默认以一个build.gradle文件识别一个Project
    
    查看Projects命令: gradle projects
    
####Project API

    Gradle生命周期API
        beforeEvaluate()
        afterEvaluate()
        buildFinished()
    
    Project相关API
        getAllprojects()
        getSubprojects()
        getParent()
        getRootProject()
        
        project(path,configurationClosure)
        allprojects(closure)
        subprojects(closure)
        
    Task相关API
    
    属性相关API
        String DEFAULT_BUILD_FILE = "build.gradle";
        String PATH_SEPARATOR = ":";
        String DEFAULT_BUILD_DIR_NAME = "build";
        String GRADLE_PROPERTIES = "gradle.properties";
        String SYSTEM_PROP_PREFIX = "systemProp";
        String DEFAULT_VERSION = "unspecified";
        String DEFAULT_STATUS = "release";
        //自定义扩展属性
        ext {
            JUNIT_VERSION='4.12'
        }
        // 或者使用gradle.properties文件
    File相关API
    
        getRootDir()
        getBuildDir()
        getProjectDir()
        
        file(path)
        files(...)
        
        copy(closure)
        
        fileTree()
    其他API
        依赖相关API
            repositories()
            dependencies()
        外部命令执行

###Gradle Task        

####Task的定义及配置    
    /**
     String TASK_NAME = "name";
     String TASK_DESCRIPTION = "description";
     String TASK_GROUP = "group";
     String TASK_TYPE = "type";
     String TASK_DEPENDS_ON = "dependsOn";
     String TASK_OVERWRITE = "overwrite";
     String TASK_ACTION = "action";
     */
     
    task helloWorld {
        println 'Hello World!!!'
    }
    
    this.tasks.create(name:'sample') {
        println 'Task sample run...'
    }
    
####Task 执行

    def buildStartTime,buildEndTime
    
    this.afterEvaluate {
        def preBuildTask = this.tasks.findByName('build')
        preBuildTask?.doFirst {
            buildStartTime = System.currentTimeMillis()
            println " build task start time:$buildStartTime"
        }
    
        def buildTask = this.tasks.findByName('build')
        buildTask?.doLast {
            buildEndTime = System.currentTimeMillis()
            println " build task end time:$buildEndTime"
        }
    }
    
####Task 执行顺序

    dependsOn 依赖
    
    Task指定输入输出
    
    通过API指定顺序
    
    /****************************
    查看任务依赖详情
    gradle build -dry-run
    gradle build -m
    ****************************/
    
####Gradle Task Type


###settings.gradle

    include 引入子工程
    
###Source Set

    sourceSets {
        main {
            resources {
                setSrcDirs ([
                        'src/main/resources',
                        'src/main/resources/common'
                ])
            }
        }
    }
    
###自定义插件

    