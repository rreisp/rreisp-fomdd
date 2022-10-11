<?xml version="1.0" encoding="UTF-8"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="1.2">
   <jsp:output doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"
               doctype-root-element="html"
               doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
   <jsp:directive.page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
                       session="false"/>
   <jsp:directive.page import="model.repository.*, model.*, java.util.List, java.util.ArrayList"/>
   <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
         <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
         <link href="seller.css" rel="stylesheet" type="text/css"/>
         <title>::Seller::</title>
      </head>
      <body>
         <div id="all">
            <div id="header">
               <table>
                  <tr>
                     <td id="header1"/>
                     <td id="header2"/>
                     <td id="header3"/>
                  </tr>
               </table>
            </div>
            <div id="main">
               <div id="section1">
                  <form id="SellerDefaultSellerForm">
                     <div id="SellerDefaultSellerFormItem1">
                        <span id="SellerDefaultSellerFormItem1SellerInfo">FREETEXT</span>
                     </div>
                     <div id="SellerDefaultSellerFormItem2">
                        <label for="" id="SellerDefaultSellerFormItem2SellerName">LABEL</label>
                        <input id="SellerDefaultSellerFormItem2SellerNameField"
                               name="SellerDefaultSellerFormItem2SellerNameField"
                               type="text"/>
                     </div>
                     <div id="SellerDefaultSellerFormItem3">
                        <label for="" id="SellerDefaultSellerFormItem3SellerAddress">LABEL</label>
                        <input id="SellerDefaultSellerFormItem3SellerAddressField"
                               name="SellerDefaultSellerFormItem3SellerAddressField"
                               type="text"/>
                     </div>
                     <div id="SellerDefaultSellerFormItem4">
                        <label for="" id="SellerDefaultSellerFormItem4SellerMail">LABEL</label>
                        <input id="SellerDefaultSellerFormItem4SellerMailField"
                               name="SellerDefaultSellerFormItem4SellerMailField"
                               type="text"/>
                     </div>
                     <div id="SellerDefaultSellerFormItem9">
                        <input name="action" type="hidden" value=""/>
                        <br/>
                        <input id="SellerDefaultSellerFormItem9SellerFormSubmit"
                               name="SellerDefaultSellerFormItem9SellerFormSubmit"
                               type="submit"
                               value=""/>
                     </div>
                  </form>
               </div>
            </div>
            <div id="bottom"/>
         </div>
      </body>
   </html>
</jsp:root>