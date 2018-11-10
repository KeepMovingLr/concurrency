package com.enyi.concurrency.example.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author enyi.lr
 * @date 2018/11/10 7:10 PM
 * @description ${DESCRIPTION}
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {


    @RequestMapping("/test")
    @ResponseBody
    public Long test() {
        return RequestHolder.getId();
    }



}
