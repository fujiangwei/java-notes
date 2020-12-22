package com.notes.controller;

import com.notes.domain.MyBean;
import com.notes.utils.LocalConfigInfoUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 文件描述
 **/
@RestController
@RequestMapping(value = "test")
public class TestController {

    @GetMapping(value = "hello")
    public String test() {

        System.out.println("hello");

        return "ok";
    }

    @GetMapping(value = "cpuInfo")
    public String cpuInfo() {
        try {
            LocalConfigInfoUtils.cpuInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping(value = "/api/stackinfo")
    public String stackinfo() {
        int tc = Thread.activeCount();
        Thread[] ts = new Thread[tc];
        Thread.enumerate(ts);
        Thread[] threadList = ts;

        String str = "<strong>Memory:</strong>";
        str +="<ol>";
        str +="<li>freeMemory="+Runtime.getRuntime().freeMemory()/(1024*1024)+"M</li>";
        str +="<li>totalMemory="+Runtime.getRuntime().totalMemory()/(1024*1024)+"M</li>";
        str +="<li>maxMemory="+Runtime.getRuntime().maxMemory()/(1024*1024)+"M</li>";
        str +="</ol>";
        str +="<br/>";
        str +="<strong>Thread:</strong>";
        str +="<ol>";
        for(Thread t : threadList) {
            str +="<li>"+t.getName()+","+t.getState()+":"+ t.getClass().getName()+"</li>";
            StackTraceElement[] elems = t.getStackTrace();
            str += "<ol>";
            for(StackTraceElement elem : elems){
                str +="<li>    "+elem.toString()+"</li>";
            }
            str += "</ol>";
        }
        str +="</ol>";
        return str;
    }

    @GetMapping(value = "/api/stackinfo2")
    public String stackinfo2() {

        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"1\" width=\"80%\" cellpadding=\"2\" bordercolor=\"#0000FF\">");
        sb.append("<th width=\"33%\" colspan=\"1\" valign=\"bottom\">no</th>");
        sb.append("<th width=\"33%\" colspan=\"1\" valign=\"bottom\">name</th>");
        sb.append("<th width=\"33%\" colspan=\"1\" valign=\"bottom\">state</th>");
        sb.append("<th width=\"33%\" colspan=\"1\" valign=\"bottom\">threaddump</th>");
        int i = 0;
        for (Map.Entry stackTrace : Thread.getAllStackTraces().entrySet()) {
            Thread thread = (Thread) stackTrace.getKey();
            StackTraceElement[] elems = (StackTraceElement[])stackTrace.getValue();
            sb.append("<tr>");
            sb.append("<td>" + (i++) + "</td>");
            sb.append("<td>" + thread.getName() + " id: " + thread.getId() + "</td>");
            sb.append("<td>" + thread.getState() + "</td>");
            sb.append("<td>" + thread + "\n" + handleStackTraceElement(elems) + "</td>");
            sb.append("<tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    public String handleStackTraceElement(StackTraceElement[] elems) {
        StringBuilder sb = new StringBuilder();
        for(StackTraceElement elem : elems){
            sb.append("\t" + elem.toString() + "\n");
        }

        return sb.toString();
    }

    @PostMapping("/beanSetTest")
    public String beanSetTest(@RequestBody MyBean bean) {
        MyBean newBean = new MyBean();

        MyBean newBean2 = new MyBean();
        newBean2.setBidRatio(bean.getBidRatio());
        newBean2.setBeginBidDate(newBean.getBeginBidDate());

        return bean.toString() + "\n" + newBean.toString() + "\n" + newBean2.toString();
    }
}
