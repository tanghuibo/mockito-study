package io.github.tanghuibo.mockitostudy.service;

/**
 * BService
 *
 * @author tanghuibo
 * @date 2022/9/1 15:34
 */
public interface BService {

    /**
     * 改变 a 的数据
     */
    void aDataIncrement();

    /**
     * 展示 a 的结果
     * @return
     */
    Integer showAData();
}
