package com.it.zqm.controller;

import com.it.zqm.dao.entity.NsProvince;
import com.it.zqm.dao.mapper.NsProvinceMapper;
import com.it.zqm.exception.BaseException;
import com.it.zqm.executor.AsyncExecutorService;
import com.it.zqm.result.Result;
import com.it.zqm.result.ResultGenerator;
import com.it.zqm.vo.CreateSliceRo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 测试接口入口
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {
    @Resource
    private NsProvinceMapper mapper;
    @Value("${test.read.message}")
    private String message;
    @Autowired
    private AsyncExecutorService asyncExecutorService;


    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String testHello() {
        return message;
    }

    @RequestMapping(value = "log",method = RequestMethod.GET)
    public String testLog() {
        log.error("lll");
        log.info("info");
        return message;
    }


    /**
     * 查询省份设置列表
     */
    @PostMapping("/list")
    public Result list(NsProvince nsProvince) {
        return ResultGenerator.getSuccessResult(mapper.queryNsProvinceList(nsProvince));
    }

    /**
     * 查询省份设置列表
     */
    @PostMapping("/list1")
    public Result list1(NsProvince nsProvince) {
        return ResultGenerator.getSuccessResult(mapper.queryNsProvinceList(nsProvince));
    }


    @PostMapping("/error")
    public Result testException() {
        log.error("测试error");
        throw new BaseException("测试错误");
    }

    @GetMapping("/exception")
    public Result testCustomException() {
        throw new BaseException(800001, "测试错误");
    }

    /**
     * 测试 @Valid 优雅校验
     *
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Result createSlice(@RequestBody @Valid CreateSliceRo createSliceRo) {
        return ResultGenerator.getSuccessResult();
    }


    @RequestMapping(value = "thread",method = RequestMethod.GET)
    public Result testAsyncService() throws ExecutionException, InterruptedException {
        /**
         *         asyncExecutorService.execute(new Runnable() {
         *             @Override
         *             public void run() {
         *
         *             }
         *         });
         *         Future<Integer> submit = asyncExecutorService.submit(new Callable<Integer>() {
         *             @Override
         *             public Integer call() throws Exception {
         *                 return add(1, 2);
         *             }
         *         });
         */

        asyncExecutorService.execute(() -> printAdd(1, 2));
        Future<Integer> submit = asyncExecutorService.submit(() -> add(1, 2));
        Integer add = submit.get();
        log.info("异步带返回值:" + add);
        return ResultGenerator.getSuccessResult();
    }


    private static int add(int a, int b) {
        return a + b;
    }

    private static void printAdd(int a, int b) {
        log.info("这是异步不返回结果的输出:" + (a + b));
    }
}
