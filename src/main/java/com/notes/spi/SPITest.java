package com.notes.spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 文件描述
 * 通过ServiceLoader.load或者Service.providers方法拿到实现类的实例。
 * Service.providers包位于sun.misc.Service，而ServiceLoader.load包位于java.util.ServiceLoader

 **/
public class SPITest {

    public static void main(String[] args) {
        Iterator<SPIService> providers = Service.providers(SPIService.class);
        while (providers.hasNext()) {
            SPIService spiService = providers.next();
            spiService.execute();
        }

        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        Iterator<SPIService> iterator = load.iterator();
        while (iterator.hasNext()) {
            SPIService spiService = iterator.next();
            spiService.execute();
        }
    }
}