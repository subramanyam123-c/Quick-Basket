package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.ZipCode;
import com.example.service.ZipCodeService;
import com.example.service.impl.ZipCodeServiceImpl;

public class ZipCodeController {
    
    private ZipCodeService zipCodeService;
    
    public ZipCodeController() {
        zipCodeService = new ZipCodeServiceImpl();
    }
    
    public void addZipCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        ZipCode zipCode = new ZipCode(code);
        zipCodeService.addZipCode(zipCode);
        // Redirect to a success page or back to the form page
    }
    
    public void updateZipCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        ZipCode zipCode = zipCodeService.getZipCodeByCode(code);
        // Update the fields of the zipCode object
        zipCodeService.updateZipCode(zipCode);
        // Redirect to a success page or back to the form page
    }
    
    public void deleteZipCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        zipCodeService.deleteZipCode(code);
        // Redirect to a success page or back to the list page
    }
    
    public void getZipCodeByCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        ZipCode zipCode = zipCodeService.getZipCodeByCode(code);
        // Send the zipCode object to a JSP page for display
    }
    
    public void getAllZipCodes(HttpServletRequest request, HttpServletResponse response) {
        List<ZipCode> zipCodes = zipCodeService.getAllZipCodes();
        // Send the list of zipCode objects to a JSP page for display
    }
}
