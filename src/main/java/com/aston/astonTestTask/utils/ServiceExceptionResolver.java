//package com.aston.astonTestTask.utils;
//
//import com.aston.astonTestTask.model.ServiceException;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
//import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class ServiceExceptionResolver extends AbstractHandlerExceptionResolver {
//
//    @Override
//    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
//        if (ex instanceof ServiceException) {
//            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
//            modelAndView.addObject("message", "Exception was handled");
//            return modelAndView;
//
//        }
//        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//        modelAndView.addObject("message", "Another exception was handled");
//        return modelAndView;
//    }
//
//}
