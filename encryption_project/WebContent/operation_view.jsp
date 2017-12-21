<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Encryption project operation</title>
<script src="resources/js/bootstrap.min.js"></script>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="row">
		<div class="col-md-12">
			<legend>Database operation</legend>

			<table id="example" class="table table-striped table-bordered">
				<thead>

					<form class="form-horizontal" action="balanceController_op"
						method="POST">

						<td><select name="operation" class="form-group">
								<option value="bigger">Get Lower than Balance</option>
								<option value="smaller">Get Higher than Balance</option>
								<option value="equal">Get Equal Balance</option>

						</select></td>

						<td>
							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="balance">balance</label>
								<div class="col-md-4">
									<input id="balance" name="balance" type="number" placeholder=""
										class="form-control input-md">

								</div>
							</div>
						</td>

						<td>
							<!-- Button -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="get_result"></label>
								<div class="col-md-4">
									<input type="submit" class="btn btn-info" value="Get Result">
								</div>
							</div>
						</td>
						<!-- 
						<td><a href="balanceController_op?balanceId=222"
							class="btn btn-danger" type="button">Get Lower than Balance</a></td>

						<td><a href="balanceController_op?balanceId=333"
							class="btn btn-danger" type="button">Get Higher than Balance</a></td>

						<td><a href="balanceController_op?balanceId=444"
							class="btn btn-danger" type="button">Get Equal Balance</a></td>
				 -->
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


</body>
</html>