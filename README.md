# 学习 mockito 测试框架

## Mockito.Answers

|enum|说明|
|---|---|
|RETURNS_DEFAULTS|返回默认值，如 Integer -> 0|
|RETURNS_SMART_NULLS|返回非 null 的值，但不能调用该返回值的方法，否则抛异常|
|RETURNS_MOCKS|返回一个mock对象|
|RETURNS_DEEP_STUBS|返回值的返回值也是mock对象|
|CALLS_REAL_METHODS|进行真实调用|
|RETURNS_SELF|返回自己，用于 builder 模式|