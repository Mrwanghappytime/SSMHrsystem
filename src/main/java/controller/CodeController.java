package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.AuthImg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class CodeController {

    @RequestMapping(value = "authImg",method = RequestMethod.GET)
    public void authImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthImg.genAuthImg(request,response);
    }

}
