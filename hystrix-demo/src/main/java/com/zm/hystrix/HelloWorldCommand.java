package com.zm.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class HelloWorldCommand extends HystrixCommand<String> {

    private final String name;

    protected HelloWorldCommand(String name) {
        // 指定命令组名
        super(HystrixCommandGroupKey.Factory.asKey("ExampleKey"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //依赖逻辑封装在run()方法中
        return "Hello " + name + "! thread: " + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception{
        /**
         * 每个Command对象只能调用一次,不可以重复调用
         * 重复调用对应的异常信息：This instance can only be executed once.
         * Please instantiate a new instance
         */
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Synchronous-hystrix");
        //使用execute()同步调用代码,效果等同于:commandHelloWorld.queue().get();
        String s = helloWorldCommand.execute();
        s = helloWorldCommand.queue().get();
        System.out.println(" 同步 ====== " + s);

        //异步调用,可以自由控制获取结果的时机
        helloWorldCommand = new HelloWorldCommand("Asynchronous-hystrix");
        Future<String> future = helloWorldCommand.queue();
        //get()操作不能超过command定义的超时时间,默认为1秒
        s = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println(" 异步 ====== " + s);


        System.out.println(" 主函数 ===== " + Thread.currentThread().getName());
        /**
         * 注意：
         * 异步调用使用 command.queue()get(timeout, TimeUnit.MILLISECONDS);
         * 同步调用使用command.execute() 等同于 command.queue().get();
         */
    }
}
