<%-- 
    Document   : order
    Created on : 13/05/2019, 4:48:06 PM
    Author     : jessicawiradinata
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 4rem">Your Order</h1>
            <div style="display: flex">
                <div style="width: 50%; margin-right: 4rem">
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item" style="display: flex;">
                          <div style="flex-grow: 1">
                              <h4>Movie Title</h4>
                              <p>$20.50</p>
                          </div>
                          <div style="display: flex; align-items: center">
                              <button type="button" class="btn btn-danger">
                                  Remove
                              </button>
                          </div>
                      </li>
                      <li class="list-group-item" style="display: flex;">
                          <div style="flex-grow: 1">
                              <h4>Movie Title</h4>
                              <p>$20.50</p>
                          </div>
                          <div style="display: flex; align-items: center">
                              <button type="button" class="btn btn-danger">
                                  Remove
                              </button>
                          </div>
                      </li>
                      <li class="list-group-item" style="display: flex;">
                          <div style="flex-grow: 1">
                              <h4>Movie Title</h4>
                              <p>$20.50</p>
                          </div>
                          <div style="display: flex; align-items: center">
                              <button type="button" class="btn btn-danger">
                                  Remove
                              </button>
                          </div>
                      </li>
                    </ul>
                </div>
                <div class="panel panel-default" style="width: 50%;  padding-left: 1rem; padding-right: 1rem; display: flex;">
                    <div class="panel-body" style="display: flex; flex-direction: column; flex-grow: 1">
                        <h3 style="margin-top: 1rem; margin-bottom: 2rem;">Order details</h3>
                        <div style="display: flex; flex-grow: 1">
                            <p style="flex-grow: 1">Total price</p>
                            <p>$61.50</p>
                        </div>
                        <div style="display: flex; justify-content: flex-end">
                            <button type="button" class="btn btn-danger" style="margin-right: 1rem">
                                Cancel
                            </button>
                            <button type="button" class="btn btn-primary">
                                Submit order
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
