package com.anhtester.Bai17_Keyword;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.EndPointGlobal;
import com.anhtester.keywords.ApiKeyword;
import com.anhtester.utils.LogUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class BookTest_Keyword extends BaseTest {

    @Test
    public void testGetBooks(){
        Response response = ApiKeyword.get(EndPointGlobal.EP_BOOKS);
        ApiKeyword.verifyStatusCode(response, 200);
        LogUtils.info(ApiKeyword.getResponseKeyValue(response, "response[0].name"));
    }
}
