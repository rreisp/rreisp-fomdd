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
         <link href="response.css" rel="stylesheet" type="text/css"/>
         <title>::Response::</title>
      </head>
      <body>
         <div id="all">
            <div id="header">
               <table>
                  <tr>
                     <td id="header1">
                        <a href="exe.cute?action=goToHome" id="ResponseDefaultHeaderLink">Home<img border="" src=""/>
                        </a>
                     </td>
                     <td id="header2">
                        <span id="ResponseDefaultHeaderTitle">FREETEXT</span>
                     </td>
                     <td id="header3">
                        <ul id="ResponseDefaultMainMenu">
                           <li id="ResponseDefaultMainMenuItem1">ITEM<a href="exe.cute?action=goToSeller" id="ResponseDefaultMainMenuItem1SellerLink">Seller</a>
                           </li>
                           <li id="ResponseDefaultMainMenuItem2">ITEM<a href="exe.cute?action=goToCatalog" id="ResponseDefaultMainMenuItem2CatalogLink">Catalog</a>
                           </li>
                           <li id="ResponseDefaultMainMenuItem3">ITEM<a href="exe.cute?action=goToHome" id="ResponseDefaultMainMenuItem3HomeLink">Home</a>
                           </li>
                        </ul>
                     </td>
                  </tr>
               </table>
            </div>
            <div id="main">
               <div id="section1">
                  <span id="ResponseDefaultMessageTitle">FREETEXT</span>
               </div>
               <div id="section2">
                  <span id="ResponseDefaultMessage">FREETEXT</span>
               </div>
            </div>
            <div id="bottom">
               <span id="ResponseDefaultFooter">FREETEXT</span>
            </div>
         </div>
      </body>
   </html>
</jsp:root>