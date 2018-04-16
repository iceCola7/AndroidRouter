# AndroidRouter
一款轻量级的路由组件

##### 使用方式

```

	// 1.创建Router对象
	LightRouter lightRouter = new LightRouter.Builder().interceptor(new Interceptor() {
	    @Override
	    public boolean intercept(IntentWrapper intentWrapper) {
	        return false;
	    }
	}).build();
	
	// 2.创建Service对象
	final IService service = lightRouter.create(IService.class, this);
	// 3.调用方法跳转
	service.intentToTestActivity("android", 123);
	
	// 或 3.调用方法跳转
	IntentWrapper intentWrapper = service.intentToTest2Activity("android",1234);
	// 得到Intent
	Intent intent = intentWrapper.getIntent();
	// 设置Flags
	intentWrapper.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
	intentWrapper.start();

```