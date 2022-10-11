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
         <link href="home.css" rel="stylesheet" type="text/css"/>
         <title>::Home::</title>
      </head>
      <body>
         <div id="all">
            <div id="header">
               <table>
                  <tr>
                     <td id="header1">
                        <a href="exe.cute?action=goToHome" id="HomeDefaultHeaderLink">Home<img border="" src=""/>
                        </a>
                     </td>
                     <td id="header2">
                        <span id="HomeDefaultHeaderTitle">FREETEXT</span>
                     </td>
                     <td id="header3">
                        <ul id="HomeDefaultMainMenu">
                           <li id="HomeDefaultMainMenuItem1">ITEM<a href="exe.cute?action=goToSeller" id="HomeDefaultMainMenuItem1SellerLink">Seller</a>
                           </li>
                           <li id="HomeDefaultMainMenuItem2">ITEM<a href="exe.cute?action=goToCatalog" id="HomeDefaultMainMenuItem2CatalogLink">Catalog</a>
                           </li>
                           <li id="HomeDefaultMainMenuItem3">ITEM<a href="exe.cute?action=goToHome" id="HomeDefaultMainMenuItem3HomeLink">Home</a>
                           </li>
                        </ul>
                     </td>
                  </tr>
               </table>
            </div>
            <div id="main">
               <div id="section1">
                  <span id="HomeDefaultWelcomeTitle">FREETEXT</span>
               </div>
               <div id="section2">
                  <span id="HomeDefaultWelcomeMessage">FREETEXT</span>
               </div>
            </div>
            <div id="bottom">
               <span id="HomeDefaultFooter">FREETEXT</span>
            </div>
         </div>
      </body>
   </html>
</jsp:root>