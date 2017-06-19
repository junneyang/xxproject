//package com.xxcompany.xxproject.zuul.server;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//public class VndErrorController implements ErrorController {
//	
//	@Value("${error.path:/error}")
//    private String errorPath;
//     
//    @Override
//    public String getErrorPath() {
//        return errorPath;
//    }
// 
//    @RequestMapping(value = "${error.path:/error}", produces = "application/vnd.error+json")
//    public @ResponseBody ResponseEntity error(HttpServletRequest request) {
// 
//        //final String logref = RequestCorrelationUtils.getCurrentCorrelationId();
//        final int status = getErrorStatus(request);
//        final String errorMessage = getErrorMessage(request);
//        //final VndError error = new VndError(logref, errorMessage);
//        return ResponseEntity.status(status).body(errorMessage);
//    }
// 
//    private int getErrorStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
//        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
//    }
// 
//    private String getErrorMessage(HttpServletRequest request) {
//        final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
//        return exc != null ? exc.getMessage() : "Unexpected error occurred";
//    }
//    
//}
