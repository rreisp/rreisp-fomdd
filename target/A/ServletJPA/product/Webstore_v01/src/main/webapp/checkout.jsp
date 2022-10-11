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
         <link href="checkout.css" rel="stylesheet" type="text/css"/>
         <title>::Checkout::</title>
      </head>
      <body>
         <div id="all">
            <div id="header">
               <table>
                  <tr>
                     <td id="header1">
                        <a href="#" id="CheckoutDefaultHeaderLink">LINK<img border="" src=""/>
                        </a>
                     </td>
                     <td id="header2">
                        <span id="CheckoutDefaultHeaderTitle">FREETEXT</span>
                     </td>
                     <td id="header3">
                        <ul id="CheckoutDefaultMainMenu">
                           <li id="CheckoutDefaultMainMenuItem1">ITEM<a href="exe.cute?action=goToSeller" id="CheckoutDefaultMainMenuItem1SellerLink">Seller</a>
                           </li>
                           <li id="CheckoutDefaultMainMenuItem2">ITEM<a href="exe.cute?action=goToCatalog" id="CheckoutDefaultMainMenuItem2CatalogLink">Catalog</a>
                           </li>
                           <li id="CheckoutDefaultMainMenuItem3">ITEM<a href="exe.cute?action=goToHome" id="CheckoutDefaultMainMenuItem3HomeLink">Home</a>
                           </li>
                        </ul>
                     </td>
                  </tr>
               </table>
            </div>
            <div id="main">
               <div id="section1">
                  <span id="CheckoutDefaultDetailsMsg">FREETEXT</span>
               </div>
               <div id="section2">
                  <table id="CheckoutDefaultCheckoutTable">
                     <tr id="CheckoutDefaultCheckoutTableRow1">
                        <th id="CheckoutDefaultCheckoutTableRow1Header1">
                           <span id="CheckoutDefaultCheckoutTableRow1Header1ImageHeader">FREETEXT</span>
                        </th>
                        <th id="CheckoutDefaultCheckoutTableRow1Header2">
                           <span id="CheckoutDefaultCheckoutTableRow1Header2NameHeader">FREETEXT</span>
                        </th>
                        <th id="CheckoutDefaultCheckoutTableRow1Header3">
                           <span id="CheckoutDefaultCheckoutTableRow1Header3PriceHeader">FREETEXT</span>
                        </th>
                     </tr>
                     <jsp:scriptlet>// Dynamic code starts here// Use CDATA to insert your Java source code</jsp:scriptlet>
                     <tr id="CheckoutDefaultCheckoutTableRow2">
                        <td id="CheckoutDefaultCheckoutTableRow2Column1">
                           <img border="" src=""/>
                        </td>
                        <td id="CheckoutDefaultCheckoutTableRow2Column2">
                           <span id="CheckoutDefaultCheckoutTableRow2Column2NameItem">FREETEXT</span>
                        </td>
                        <td id="CheckoutDefaultCheckoutTableRow2Column3">
                           <span id="CheckoutDefaultCheckoutTableRow2Column3PriceItem">FREETEXT</span>
                        </td>
                     </tr>
                     <jsp:scriptlet>// Dynamic code ends here// Use CDATA to insert your Java source code</jsp:scriptlet>
                  </table>
               </div>
               <div id="section3">
                  <form id="CheckoutDefaultCheckoutForm">
                     <div id="CheckoutDefaultCheckoutFormItem1">
                        <span id="CheckoutDefaultCheckoutFormItem1CustomerInfo">FREETEXT</span>
                     </div>
                     <div id="CheckoutDefaultCheckoutFormItem2">
                        <label for="" id="CheckoutDefaultCheckoutFormItem2CustomerName">LABEL</label>
                        <input id="CheckoutDefaultCheckoutFormItem2CustomerNameField"
                               name="CheckoutDefaultCheckoutFormItem2CustomerNameField"
                               type="text"/>
                     </div>
                     <div id="CheckoutDefaultCheckoutFormItem3">
                        <label for="" id="CheckoutDefaultCheckoutFormItem3CustomerAddress">LABEL</label>
                        <input id="CheckoutDefaultCheckoutFormItem3CustomerAddressField"
                               name="CheckoutDefaultCheckoutFormItem3CustomerAddressField"
                               type="text"/>
                     </div>
                     <div id="CheckoutDefaultCheckoutFormItem4">
                        <label for="" id="CheckoutDefaultCheckoutFormItem4CustomerMail">LABEL</label>
                        <input id="CheckoutDefaultCheckoutFormItem4CustomerMailField"
                               name="CheckoutDefaultCheckoutFormItem4CustomerMailField"
                               type="text"/>
                     </div>
                     <div id="CheckoutDefaultCheckoutFormItem5">
                        <input name="action" type="hidden" value=""/>
                        <br/>
                        <input id="CheckoutDefaultCheckoutFormItem5CheckoutFormSubmit"
                               name="CheckoutDefaultCheckoutFormItem5CheckoutFormSubmit"
                               type="submit"
                               value=""/>
                     </div>
                  </form>
               </div>
            </div>
            <div id="bottom">
               <span id="CheckoutDefaultFooter">FREETEXT</span>
            </div>
         </div>
      </body>
   </html>
</jsp:root>