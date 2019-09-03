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
    
    Gradle工程默认以一个build.gradle文件识别一个Project
    