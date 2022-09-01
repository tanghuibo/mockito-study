package io.github.tanghuibo.mockitostudy.service.impl;

import io.github.tanghuibo.mockitostudy.service.AService;
import org.springframework.stereotype.Service;

/**
 * AServiceImpl
 *
 * @author tanghuibo
 * @date 2022/9/1 15:37
 */
@Service
public class AServiceImpl implements AService {

    private Integer aData = Double.valueOf(Math.random() * 10000).intValue();

    @Override
    public Integer getAData() {
        return aData;
    }

    @Override
    public void setAData(Integer aData) {
        this.aData = aData;
    }
}
