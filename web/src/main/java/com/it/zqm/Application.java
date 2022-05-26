package com.it.zqm;



import com.it.zqm.constants.VersionConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.it.zqm.**"})
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        if (args.length > 0 && StringUtils.equalsAny(args[0], "-v", "-V", "-version", "-Version")) {
            System.out.println("打包时间：" + VersionConstants.DATE);
            System.out.println("打包时间：" + VersionConstants.DATE);
            System.out.println("打包版本：" + VersionConstants.VERSION);
            System.out.println("打包分支：" + VersionConstants.BRANCH);
            System.out.println("提交commitId：" + VersionConstants.COMMIT_ID);
            return;
        }
        SpringApplication.run(Application.class, args);
    }
}
