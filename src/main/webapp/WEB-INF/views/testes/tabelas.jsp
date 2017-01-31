<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="for" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../_comum/header.jsp" />

<style>

@import url(http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100);

body {
  background-color: #3e94ec;
  font-family: "Roboto", helvetica, arial, sans-serif;
  font-size: 16px;
  font-weight: 400;
  text-rendering: optimizeLegibility;
}

div.table-title {
   display: block;
  margin: auto;
  max-width: 600px;
  padding:5px;
  width: 100%;
}

.table-title h3 {
   color: #000;
   font-size: 30px;
   font-weight: 400;
   font-style:normal;
   font-family: "Roboto", helvetica, arial, sans-serif;
   text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
   text-transform:uppercase;
}


/*** Table Styles **/

.table-fill {
  background: white;
  border-radius:3px;
  border-collapse: collapse;
  height: 320px;
  margin: auto;
  max-width: 600px;
  padding:5px;
  width: 100%;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
  animation: float 5s infinite;
}
 
th {
  color:#D5DDE5;;
  background:#1b1e24;
  border-bottom:4px solid #9ea7af;
  border-right: 1px solid #343a45;
  font-size:23px;
  font-weight: 100;
  padding:24px;
  text-align:left;
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
  vertical-align:middle;
}

th:first-child {
  border-top-left-radius:3px;
}
 
th:last-child {
  border-top-right-radius:3px;
  border-right:none;
}
  
tr {
  border-top: 1px solid #C1C3D1;
  border-bottom-: 1px solid #C1C3D1;
  color:#666B85;
  font-size:16px;
  font-weight:normal;
  text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
}
 
tr:hover td {
  background:#4E5066;
  color:#FFFFFF;
  border-top: 1px solid #22262e;
  border-bottom: 1px solid #22262e;
}
 
tr:first-child {
  border-top:none;
}

tr:last-child {
  border-bottom:none;
}
 
tr:nth-child(odd) td {
  background:#EBEBEB;
}
 
tr:nth-child(odd):hover td {
  background:#4E5066;
}

tr:last-child td:first-child {
  border-bottom-left-radius:3px;
}
 
tr:last-child td:last-child {
  border-bottom-right-radius:3px;
}
 
td {
  background:#FFFFFF;
  padding:20px;
  text-align:left;
  vertical-align:middle;
  font-weight:300;
  font-size:18px;
  text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
  border-right: 1px solid #C1C3D1;
}

td:last-child {
  border-right: 0px;
}

th.text-left {
  text-align: left;
}

th.text-center {
  text-align: center;
}

th.text-right {
  text-align: right;
}

td.text-left {
  text-align: left;
}

td.text-center {
  text-align: center;
}

td.text-right {
  text-align: right;
}

/* --------------------------------------------------------------------------------------------------------------  */
.wrapper {
  margin: 0 auto;
  padding: 40px;
  max-width: 800px;
}

.table {
  margin: 0 0 40px 0;
  width: 100%;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
  display: table;
}
@media screen and (max-width: 580px) {
  .table {
    display: block;
  }
}

.row {
  display: table-row;
  background: #f6f6f6;
}
.row:nth-of-type(odd) {
  background: #e9e9e9;
}
.row.header {
  font-weight: 900;
  color: #ffffff;
  background: #ea6153;
}
.row.green {
  background: #27ae60;
}
.row.blue {
  background: #2980b9;
}
@media screen and (max-width: 580px) {
  .row {
    padding: 8px 0;
    display: block;
  }
}

.cell {
  padding: 6px 12px;
  display: table-cell;
}
@media screen and (max-width: 580px) {
  .cell {
    padding: 2px 12px;
    display: block;
  }
}


/* --------------------------------------------------------------------------------------------------------------  */
</style>
<div class="table-title">
<h3>Data Table</h3>
</div>
<table class="table-fill">
	<thead>
		<tr>
			<th class="text-left">Month</th>
			<th class="text-left">Sales</th>
			<th class="text-left">Sales</th>
			<th class="text-left">Sales</th>
			<th class="text-left">Sales</th>
		</tr>
	</thead>
	<tbody class="table-hover">
		<tr>
			<td class="text-left">January</td>
			<td class="text-left">$ 50,000.00</td>
			<td class="text-left">$ 50,000.00</td>
			<td class="text-left">$ 50,000.00</td>
			<td class="text-left">$ 50,000.00</td>
		</tr>
		<tr>
			<td class="text-left">February</td>
			<td class="text-left">$ 10,000.00</td>
			<td class="text-left">$ 10,000.00</td>
			<td class="text-left">$ 10,000.00</td>
			<td class="text-left">$ 10,000.00</td>
		</tr>
		<tr>
			<td class="text-left">March</td>
			<td class="text-left">$ 85,000.00</td>
			<td class="text-left">$ 85,000.00</td>
			<td class="text-left">$ 85,000.00</td>
			<td class="text-left">$ 85,000.00</td>
		</tr>
		<tr>
			<td class="text-left">April</td>
			<td class="text-left">$ 56,000.00</td>
			<td class="text-left">$ 56,000.00</td>
			<td class="text-left">$ 56,000.00</td>
			<td class="text-left">$ 56,000.00</td>
		</tr>
		<tr>
			<td class="text-left">May</td>
			<td class="text-left">$ 98,000.00</td>
			<td class="text-left">$ 98,000.00</td>
			<td class="text-left">$ 98,000.00</td>
			<td class="text-left">$ 98,000.00</td>
		</tr>
	</tbody>
</table>

<br><br>

<div class="wrapper">
  
  <div class="table">
    
    <div class="row header">
      <div class="cell">
        Name
      </div>
      <div class="cell">
        Age
      </div>
      <div class="cell">
        Occupation
      </div>
      <div class="cell">
        Location
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        Luke Peters
      </div>
      <div class="cell">
        25
      </div>
      <div class="cell">
        Freelance Web Developer
      </div>
      <div class="cell">
        Brookline, MA
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        Joseph Smith
      </div>
      <div class="cell">
        27
      </div>
      <div class="cell">
        Project Manager
      </div>
      <div class="cell">
        Somerville, MA
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        Maxwell Johnson
      </div>
      <div class="cell">
        26
      </div>
      <div class="cell">
        UX Architect & Designer
      </div>
      <div class="cell">
        Arlington, MA
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        Harry Harrison
      </div>
      <div class="cell">
        25
      </div>
      <div class="cell">
        Front-End Developer
      </div>
      <div class="cell">
        Boston, MA
      </div>
    </div>
    
  </div>
  
  <div class="table">
    
    <div class="row header green">
      <div class="cell">
        Product
      </div>
      <div class="cell">
        Unit Price
      </div>
      <div class="cell">
        Quantity
      </div>
      <div class="cell">
        Date Sold
      </div>
      <div class="cell">
        Status
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        Solid oak work table
      </div>
      <div class="cell">
        $800
      </div>
      <div class="cell">
        10
      </div>
      <div class="cell">
        03/15/2014
      </div>
      <div class="cell">
        Waiting for Pickup
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        Leather iPhone wallet
      </div>
      <div class="cell">
        $45
      </div>
      <div class="cell">
        120
      </div>
      <div class="cell">
        02/28/2014
      </div>
      <div class="cell">
        In Transit
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        27" Apple Thunderbolt displays
      </div>
      <div class="cell">
        $1000
      </div>
      <div class="cell">
        25
      </div>
      <div class="cell">
        02/10/2014
      </div>
      <div class="cell">
        Delivered
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        Bose studio headphones
      </div>
      <div class="cell">
        $60
      </div>
      <div class="cell">
        90
      </div>
      <div class="cell">
        01/14/2014
      </div>
      <div class="cell">
        Delivered
      </div>
    </div>
    
  </div>
  
  <div class="table">
    
    <div class="row header blue">
      <div class="cell">
        Username
      </div>
      <div class="cell">
        Email
      </div>
      <div class="cell">
        Password
      </div>
      <div class="cell">
        Active
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        ninjalug
      </div>
      <div class="cell">
        misterninja@hotmail.com
      </div>
      <div class="cell">
        ************
      </div>
      <div class="cell">
        Yes
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        jsmith41
      </div>
      <div class="cell">
        joseph.smith@gmail.com
      </div>
      <div class="cell">
        ************
      </div>
      <div class="cell">
        No
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        1337hax0r15
      </div>
      <div class="cell">
        hackerdude1000@aol.com
      </div>
      <div class="cell">
        ************
      </div>
      <div class="cell">
        Yes
      </div>
    </div>
    
    <div class="row">
      <div class="cell">
        hairyharry19
      </div>
      <div class="cell">
        harryharry@gmail.com
      </div>
      <div class="cell">
        ************
      </div>
      <div class="cell">
        Yes
      </div>
    </div>
    
  </div>
  
</div>

<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />












</body>
</html>