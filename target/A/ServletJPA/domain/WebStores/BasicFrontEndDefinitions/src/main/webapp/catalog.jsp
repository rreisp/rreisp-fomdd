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
         <link href="catalog.css" rel="stylesheet" type="text/css"/>
         <title>::Catalog::</title>
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
                  <span id="CatalogDefaultCatalogTitle">FREETEXT</span>
               </div>
               <div id="section2">
                  <form id="CatalogDefaultCatalogForm">
                     <div id="CatalogDefaultCatalogFormItem1">
                        <table id="CatalogDefaultCatalogFormItem1ProductTable">
                           <tr id="CatalogDefaultCatalogFormItem1ProductTableRow1">
                              <th id="CatalogDefaultCatalogFormItem1ProductTableRow1Header1">
                                 <span id="CatalogDefaultCatalogFormItem1ProductTableRow1Header1ImageHeader">FREETEXT</span>
                              </th>
                              <th id="CatalogDefaultCatalogFormItem1ProductTableRow1Header2">
                                 <span id="CatalogDefaultCatalogFormItem1ProductTableRow1Header2NameHeader">FREETEXT</span>
                              </th>
                              <th id="CatalogDefaultCatalogFormItem1ProductTableRow1Header3">
                                 <span id="CatalogDefaultCatalogFormItem1ProductTableRow1Header3DescriptionHeader">FREETEXT</span>
                              </th>
                              <th id="CatalogDefaultCatalogFormItem1ProductTableRow1Header4">
                                 <span id="CatalogDefaultCatalogFormItem1ProductTableRow1Header4PriceHeader">FREETEXT</span>
                              </th>
                              <th id="CatalogDefaultCatalogFormItem1ProductTableRow1Header5">
                                 <span id="CatalogDefaultCatalogFormItem1ProductTableRow1Header5BuyHeader">FREETEXT</span>
                              </th>
                           </tr>
                           <jsp:scriptlet>// Dynamic code starts here// Use CDATA to insert your Java source code</jsp:scriptlet>
                           <tr id="CatalogDefaultCatalogFormItem1ProductTableRow2">
                              <td id="CatalogDefaultCatalogFormItem1ProductTableRow2Column1">
                                 <img border="" src=""/>
                              </td>
                              <td id="CatalogDefaultCatalogFormItem1ProductTableRow2Column2">
                                 <span id="CatalogDefaultCatalogFormItem1ProductTableRow2Column2NameItem">FREETEXT</span>
                              </td>
                              <td id="CatalogDefaultCatalogFormItem1ProductTableRow2Column3">
                                 <span id="CatalogDefaultCatalogFormItem1ProductTableRow2Column3DescriptionItem">FREETEXT</span>
                              </td>
                              <td id="CatalogDefaultCatalogFormItem1ProductTableRow2Column4">
                                 <span id="CatalogDefaultCatalogFormItem1ProductTableRow2Column4PriceItem">FREETEXT</span>
                              </td>
                              <td id="CatalogDefaultCatalogFormItem1ProductTableRow2Column5">
                                 <input id="CatalogDefaultCatalogFormItem1ProductTableRow2Column5BuyItem"
                                        name="CatalogDefaultCatalogFormItem1ProductTableRow2Column5BuyItem"
                                        type="checkbox"
                                        value=""/>
                              </td>
                           </tr>
                           <jsp:scriptlet>// Dynamic code ends here// Use CDATA to insert your Java source code</jsp:scriptlet>
                        </table>
                     </div>
                     <div id="CatalogDefaultCatalogFormItem2">
                        <input name="action" type="hidden" value=""/>
                        <br/>
                        <input id="CatalogDefaultCatalogFormItem2CatalogFormSubmit"
                               name="CatalogDefaultCatalogFormItem2CatalogFormSubmit"
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