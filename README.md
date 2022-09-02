# 学习 mockito 测试框架

## mockito 

Mockito 是一种 Java Mock 框架，主要就是用来做 Mock 测试的，它可以模拟任何对象、模拟方法的返回值、模拟抛出异常等等，同时也会记录调用这些模拟方法的参数、调用顺序，从而可以校验出这个 Mock 对象是否有被正确的顺序调用，以及按照期望的参数被调用。

像是 Mockito 可以在单元测试中模拟一个 Service 返回的数据，而不会真正去调用该 Service，通过模拟一个假的 Service 对象，来快速的测试当前想要测试的类。

目前在 Java 中主流的 Mock 测试工具有 Mockito、JMock、EasyMock等等，而 SpringBoot 目前默认的测试框架是 Mockito 框架。

## 基本用法

### mock() / @Mock
创建 mock 对象

- 可通过  Answer/MockSettings 指定 mock 对象的行为
- 通过 when()/given() 来指定模拟应该如何表现
- 可通过自义定 Answer 灵活的对 mock 行为进行扩展

### spy()/@Spy
部分 mock，调用真实方法，可以使用 mock 对象的任何方法

### @InjectMocks
自动注入带有带@Spy 或 @Mock的字段

### verify
检查方法是否按某种参数顺序被调用
- 可以使用灵活的参数匹配，例如通过 any() 的任何表达式
- 或者使用 @Captor 来捕获调用的参数

#### Answers 默认枚举

|enum|说明|
|---|---|
|RETURNS_DEFAULTS|返回默认值，如 Integer -> 0|
|RETURNS_SMART_NULLS|返回非 null 的值，但不能调用该返回值的方法，否则抛异常|
|RETURNS_MOCKS|返回一个mock对象|
|RETURNS_DEEP_STUBS|返回值的返回值也是mock对象|
|CALLS_REAL_METHODS|进行真实调用|
|RETURNS_SELF|返回自己，用于 builder 模式|
