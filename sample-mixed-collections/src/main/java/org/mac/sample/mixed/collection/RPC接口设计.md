###RPC接口的痛点

    1.查询接口过多
    
    各种 findBy 方法，加上各自的重载，几乎占据了一个接口 80% 的代码量。
    这也符合一般人的开发习惯，因为页面需要各式各样的数据格式，加上查询条件差异很大，便造成了:一个查询条件，一个方法的尴尬场景。
    这样会导致另外一个问题，需要使用某个查询方法时，直接新增了方法，但实际上可能这个方法已经出现过了，隐藏在了令人眼花缭乱的方法中。
    
    2.难以扩展
    
    接口的任何改动，比如新增一个入参，都会导致调用者被迫升级，这也通常是 RPC 设计被诟病的一点，
    不合理的 RPC 接口设计会放大这个缺点。
    
    3.升级困难
    
    版本管理的粒度是 project，而不是 module，这意味着：api 即使没有发生变化，app 版本演进，也会造成 api 的被迫升级，
    因为 project 是一个整体。
    
    4.难以测试
    
    接口一多，职责随之变得繁杂，业务场景各异，测试用例难以维护。
    
    5.异常设计不合理
    
    1) 业务 CommonResponse，封装异常，每次调用后，优先判断调用结果是否 success，再进行业务逻辑处理；
    2) 直接 try catch 异常，不需要进行业务包裹。
    
    在没有明确规范时，这两种风格的代码同时存在于项目中，十分难看！
    
    
###单参数接口
    
     Specification 模式解决查询接口过多的问题
     
     public interface StudentApi{
         Student findByName(String name);
         List<Student> findAllByName(String name);
         Student findByNameAndNo(String name,String no);
         Student findByIdcard(String Idcard);
     }
     
     修改为
     
     public interface StudentApi{
         Student findBySpec(StudentSpec spec);
         List<Student> findListBySpec(StudentListSpec spec);
         Page<Student> findPageBySpec(StudentPageSpec spec);
     }
     
     查询条件都被封装在了 StudentSpec,StudentListSpec,StudentPageSpec 之中，
     分别满足了单对象查询，批量查询，分页查询的需求。
     
     
     单参数易于做统一管理
     
     由于是单个入参，所以可以统一继承 AbstractRequest，
     AbstractRequest 可以定义 traceId、clientIp、clientType等公共入参，减少了重复命名。
     
     统一继承抽象实体，可以更加轻松地在其之上做AOP 如:
     
     请求入参统一校验
     实体变更统一加锁，降低锁粒度
     请求分类统一处理
     请求报文统一记日志
     操作成功统一发消息
     
     单参数入参兼容性强
     
     自动生成 HTTP 接口实现
     通过 Swagger UI实现对RPC接口的可视化便捷测试
     有利于TestNg集成测试
     
###接口异常设计     
     
     RPC 框架是可以封装异常的，Exception 也是返回值的一部分
     但try catch 的方法捕获异常以便于显式处理。
     
     public interface ModuleAProvider {
         void opA(ARequest request) throws ModuleAException;
         void opB(BRequest request) throws ModuleAException;
         CommonResponse<C> opC(CRequest request) throws ModuleAException;
     }
     
     ModuleAException 定义为 checked Exception
     
     
     调用方处理
     
     public class ModuleBService implements ModuleBProvider {
         @Reference
         ModuleAProvider moduleAProvider;
     
         @Override
         public void someOp() throws ModuleBexception{
             try{
                 moduleAProvider.opA(...);
             }catch(ModuleAException e){
                 throw new ModuleBException(e.getMessage());
             }
         }
     
         @Override
         public void anotherOp(){
             try{
                 moduleAProvider.opB(...);
             }catch(ModuleAException e){
                 // 业务逻辑处理
             }
         }
     }
     
     someOp 演示了一个异常流的传递，ModuleB 暴露出去的异常应当是 ModuleB 的 api 模块中异常类，
     虽然其依赖了 ModuleA ，但需要将异常进行转换，或者对于那些意料之中的业务异常可以像 anotherOp() 一样进行处理，不再传递。
     这时如果新增 ModuleC 依赖 ModuleB，那么 ModuleC 完全不需要关心 ModuleA 的异常。
     
     
     
     异常与熔断
     
     
     RPC 调用，失败是常态。通常我们需要对 RPC 接口做熔断处理，比如集成 Netflix 提供的熔断组件 Hystrix。
     Hystrix 需要知道什么样的异常需要进行熔断，什么样的异常不能够进行熔断。
     
     public class ModuleAProviderProxy {
     
         @Reference
         private ModuleAProvider moduleAProvider;
     
         @HystrixCommand(ignoreExceptions = {ModuleAException.class})
         public void opA(ARequest request) throws ModuleAException {
             moduleAProvider.opA(request);
         }
     
         @HystrixCommand(ignoreExceptions = {ModuleAException.class})
         public void opB(BRequest request) throws ModuleAException {
             moduleAProvider.oBB(request);
         }
     
         @HystrixCommand(ignoreExceptions = {ModuleAException.class})
         public CommonResponse<C> opC(CRequest request) throws ModuleAException {
             return moduleAProvider.opC(request);
         }
     
     }
     
     服务不可用等原因引发的多次接口调用超时异常，会触发 Hystrix 的熔断；而对于业务异常，则认为不需要进行熔断，
     因为对于接口 throws 出的业务异常，认为是正常响应的一部分，只不过借助于 JAVA 的异常机制来表达。
          
###API 版本单独演进

     api 版本的演进应该是缓慢的，而 app 版本的演进应该是频繁的。所以，对于这两个演进速度不一致的模块，应该单独做版本管理，他们有自己的版本号。     