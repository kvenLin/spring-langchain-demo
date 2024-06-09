package com.louye.springlangchaindemo.service.ai;

import com.louye.springlangchaindemo.domain.aidata.CartDataList;
import com.louye.springlangchaindemo.domain.aidata.ProductDataList;
import com.louye.springlangchaindemo.domain.aidata.UserDataList;
import com.louye.springlangchaindemo.template.TableDataGeneratePrompt;

public interface Factory {

    ProductDataList generateTestDataForProduct(TableDataGeneratePrompt prompt);

    CartDataList generateTestDataForCart(TableDataGeneratePrompt prompt);

    UserDataList generateTestDataForUser(TableDataGeneratePrompt prompt);
}
