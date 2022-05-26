package com.it.zqm;

import com.power.common.enums.HttpCodeEnum;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.constants.DocGlobalConstants;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.ApiErrorCode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe:
 * @author:zqm
 */
public class DocTest extends BaseTest {

    @Test
    public void testBuilderControllersApi() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://localhost:8081");
        //当把AllInOne设置为true时，Smart-doc将会把所有接口生成到一个Markdown、HHTML或者AsciiDoc中
        config.setAllInOne(true);

        //HTML5文档，建议直接放到src/main/resources/static/doc下，Smart-doc提供一个配置常量HTML_DOC_OUT_PATH
        //源码---String HTML_DOC_OUT_PATH = "src/main/resources/static/doc";
        config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH);

        // 设置接口包扫描路径过滤，如果不配置则Smart-doc默认扫描所有的接口类
        // 配置多个报名有英文逗号隔开
        config.setPackageFilters("com.it.zqm.TestController");

        //设置错误错列表，遍历自己的错误码设置给Smart-doc即可
        List<ApiErrorCode> errorCodeList = new ArrayList<>();
        for (HttpCodeEnum codeEnum : HttpCodeEnum.values()) {
            ApiErrorCode errorCode = new ApiErrorCode();
            errorCode.setValue(codeEnum.getCode()).setDesc(codeEnum.getMessage());
            errorCodeList.add(errorCode);
        }

        //不需要显示错误码,则可以不用设置错误码。
        config.setErrorCodes(errorCodeList);
        //生成Markdown文件
        HtmlApiDocBuilder.buildApiDoc(config);
    }

}
