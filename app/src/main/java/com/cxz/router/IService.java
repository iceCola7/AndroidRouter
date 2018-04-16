package com.cxz.router;

import com.cxz.lightrouter.IntentWrapper;
import com.cxz.lightrouter.annotations.ClassName;
import com.cxz.lightrouter.annotations.Key;
import com.cxz.lightrouter.annotations.RequestCode;

public interface IService {

    @ClassName("com.cxz.router.TestActivity")
    @RequestCode(1001)
    void intentToTestActivity(@Key("key1") String key1, @Key("key2") int key2);

    @ClassName("com.cxz.router.Test2Activity")
    IntentWrapper intentToTest2Activity(@Key("key1") String key1, @Key("key2") int key2);

}
