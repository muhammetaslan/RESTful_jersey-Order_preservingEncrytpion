<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Balance</title>

<script src="resources/js/bootstrap.min.js"></script>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-6">
						<form class="form-horizontal" action="balanceController"
							method="POST">
							<fieldset>

								<!-- Form Name -->
								<legend>Balance Add</legend>



								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="username">username</label>
									<div class="col-md-4">
										<input id="username" name="username" type="text"
											placeholder="" class="form-control input-md">

									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="cardId">cardId</label>
									<div class="col-md-4">
										<input id="cardId" name="cardId" type="number" placeholder=""
											class="form-control input-md">

									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="balance">balance</label>
									<div class="col-md-4">
										<input id="balance" name="balance" type="number"
											placeholder="" class="form-control input-md">

									</div>
								</div>

								<!-- Button -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="add"></label>
									<div class="col-md-4">
										<input type="submit" class="btn btn-info" value="Add Balance">
									</div>
								</div>

							</fieldset>
						</form>
					</div>
					<div class="col-md-6"></div>
				</div>


				<div class="row">
					<div class="col-md-6">
						<legend>Balance List</legend>
						<div class="table-responsive">
							<table id="example" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>username</th>
										<th>cardId</th>
										<th>Balance</th>
									</tr>
								</thead>
								<tbody>


									<c:forEach items="${allBalance}" var="balance">
										<tr>
											<td><c:out value="${balance.username}" /></td>
											<td><c:out value="${balance.cardId}" /></td>
											<td><c:out value="${balance.balance}" /></td>




											<td><a href="balanceController?balanceId=${balance.id}"
												class="btn btn-danger" type="button">Delete</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-6"></div>
				</div>
			</div>
		</div>
	</div>



<!-- 

	<div class="row">
		<div class="col-md-12">
			<legend>Database operation</legend>

			<table id="example" class="table table-striped table-bordered">
				<thead>

					<form class="form-horizontal" action="balanceController_op"
						method="POST">

						<td><select  name ="operation" class ="form-group">
								<option value="bigger">Get Lower than Balance</option>
								<option value="smaller">Get Higher than Balance</option>
								<option value="equal">Get Equal Balance</option>

						</select></td>
						
						<td>
						
							<div class="form-group">
								<label class="col-md-4 control-label" for="balance">balance</label>
								<div class="col-md-4">
									<input id="balance" name="balance" type="number" placeholder=""
										class="form-control input-md">
	
								</div>
							</div>
						</td>
						
						<td>
							
								<div class="form-group">
									<label class="col-md-4 control-label" for="get_result"></label>
									<div class="col-md-4">
										<input type="submit" class="btn btn-info" value="Get Result">
									</div>
								</div>
						</td>
				-->
				<!-- 
						<td><a href="balanceController_op?balanceId=222"
							class="btn btn-danger" type="button">Get Lower than Balance</a></td>

						<td><a href="balanceController_op?balanceId=333"
							class="btn btn-danger" type="button">Get Higher than Balance</a></td>

						<td><a href="balanceController_op?balanceId=444"
							class="btn btn-danger" type="button">Get Equal Balance</a></td>
				 
					</form>

					<tr>
						<th>username</th>
						<th>cardId</th>
						<th>Balance</th>
					</tr>
				</thead>
				<tbody>


					<c:forEach items="${allBalance_two}" var="balance1">
						<tr>
							<td><c:out value="${balance1.username}" /></td>
							<td><c:out value="${balance1.cardId}" /></td>
							<td><c:out value="${balance1.balance}" /></td>

						</tr>
					</c:forEach>


				</tbody>
			</table>
		</div>
	</div>
-->
</body>
</html>