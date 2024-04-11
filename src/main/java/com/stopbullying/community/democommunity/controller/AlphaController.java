package com.stopbullying.community.democommunity.controller;


import com.stopbullying.community.democommunity.service.AlphaService;
import com.stopbullying.community.democommunity.util.CommunityUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired  //注入依赖Service
    private AlphaService alphaService;

    @RequestMapping("/hello")  //路径声明
    @ResponseBody
    public String sayHello() {

        return "Hello Spring Boot.";
    }

    @RequestMapping("/date")  //路径声明
    @ResponseBody
    public String getDate(){
        return alphaService.find();
    }

    @RequestMapping("/http")  //http使用底层逻辑方式
    public void http(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());  //请求行
        Enumeration<String>enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+":"+value);
        }  //消息头
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset = utf-8");  //设置返回类型为文本网页，编码为utf-8
        PrintWriter writer = response.getWriter();
        writer.write("<h1>hello hello</h1>");
    }

    //GET请求
    //如     /student?current=1&limit=20  即当前页数等于1且一次展示20个数据
    @RequestMapping(path ="/students",method = RequestMethod.GET)  //确定请求路径以及限制请求方式
    @ResponseBody //返回字符串注释
    public String getStudents(  //可以对传入参数做更详细的声明，如第一次访问没有参数，可以设置默认参数
            @RequestParam(name="current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //如    /student/123  即获取编号为123的学生数据且编号编入网页内
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        System.out.println(id);
        return "a student";
    }

    //POST请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){  //用于返回封装的model和view两份数据
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",28);
        mav.setViewName("/demo/view");
        return mav;
    }

    //或者可以这样
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age","126");

        return "/demo/view";
    }

    //响应JSON数据(异步请求：如当前网页不刷新，但做出了访问数据库的请求)
    //JAVA对象 -> JSON字符串 -> JS对象
    //返回key:value形式数据

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    //返回多个数据
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 26);
        emp.put("salary", 7000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 28);
        emp.put("salary", 9000.00);
        list.add(emp);

        return list;
    }

    // cookie示例

    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(javax.servlet.http.HttpServletResponse response) {
        // 创建cookie
        javax.servlet.http.Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的范围
        cookie.setPath("/community/alpha");
        // 设置cookie的生存时间
        cookie.setMaxAge(60 * 10);
        // 发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }

    // session示例

    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(javax.servlet.http.HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "Test");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

}
