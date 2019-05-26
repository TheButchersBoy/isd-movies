<%-- 
    Document   : Payment
    Created on : 2019-5-26, 15:41:29
    Author     : Frank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
    </head>
    <body>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 4rem">Bill Lists</h1>
            <div style="display: flex">
                <div style="width: 100%; margin-right: 4rem">
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item" style="display: flex;">
                          <div style="flex-grow: 1">
                              <h4>Movie Title</h4>
                              <p>$20</p>
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
                              <p>$20</p>
                          </div>
                          <div style="display: flex; align-items: center">
                              <button type="button" class="btn btn-danger">
                                  Remove
                              </button>
                          </div>
                      </li>
                      <li class="list-group-item" style="display: flex;">
                          <div style="flex-grow: 1">
                              <h4>Total Payment</h4>
                              <p>$40</p>
                          </div>
                          <div style="display: flex; align-items: center">
                              <div style="display: flex; justify-content: flex-end">
                            <button type="button" class="btn btn-danger" style="margin-right: 1rem">
                                Cancel
                            </button>
                            <button type="button" class="btn btn-primary">
                                Confirm Payment
                            </button>
                          </div>
                      </li> 
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>