package com.it.zqm.controller;

import com.it.zqm.result.Result;
import com.it.zqm.result.ResultGenerator;
import com.it.zqm.service.socket.SocketClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @author:zqm
 */
@RestController
public class SocketController {


    @RequestMapping("socket")
    public Result testSocket() {
        SocketClient.client();
        return ResultGenerator.getSuccessResult();
    }
}
