package io.github.tanghuibo.mockitostudy.service.impl;

import io.github.tanghuibo.mockitostudy.service.AService;
import io.github.tanghuibo.mockitostudy.service.BService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * BServiceImpl
 *
 * @author tanghuibo
 * @date 2022/9/1 15:38
 */
@Service
public class BServiceImpl implements BService {

    @Resource
    AService aService;

    @Override
    public void aDataIncrement() {
        aService.setAData(aService.getAData() + 1);
    }

    @Override
    public Integer showAData() {
        return aService.getAData();
    }

}
