<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="_head.jsp" />
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container-fluid">
		<div id="content">
			<div class="row-fluid">
        		<div class="span3 pull-left well">
					<jsp:include page="/_sideSearchBox.jsp" />
        		</div><!--/span-->
		        <div class="span9">
			  		<div class="row-fluid">
		                <div class="span12">
	                        <h2>Admin Airport Management</h2>
							<div class="row-fluid">
								<div class="span10">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Departs</th>
												<th>Arrive</th>
												<th>Stops</th>
												<th>Duration</th>
												<th>Price</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>test2</td>
												<td>test3</td>
												<td>test4</td>
												<td>test5</td>
												<td>test6</td>
											</tr>
											<tr>
	                                            <td>test2</td>
	                                            <td>test3</td>
												<td>test4</td>
												<td>test5</td>
												<td>test6</td>
	                                       </tr>
										</tbody>
									</table>
								</div><!-- span10 -->
							</div><!--/span row-->
		      			</div><!--/span12-->
		      		</div><!--/row-->
				</div><!--/span9-->
    	</div>
    	<div id="footer">
    		
    		<jsp:include page="/_footer.jsp" />
    		
    	</div>
	</div>
</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>