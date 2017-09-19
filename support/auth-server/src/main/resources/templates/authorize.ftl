<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
</head>
<body>
	<div class="container">
		<h2>Please Confirm</h2>

		<p>
			Do you authorize "${authorizationRequest.clientId}" at "${authorizationRequest.redirectUri}" to access your protected resources
			with scope ${authorizationRequest.scope?join(", ")}.
		</p>
		<!-- <form id="confirmationForm" name="confirmationForm"
			action="../oauth/authorize" method="post">
			<input name="user_oauth_approval" value="true" type="hidden" />
			<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" type="submit">Approve</button>
		</form>
		<form id="denyForm" name="confirmationForm"
			action="../oauth/authorize" method="post">
			<input name="user_oauth_approval" value="false" type="hidden" />
			<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" type="submit">Deny</button>
		</form> -->
		
		<form id='confirmationForm' name='confirmationForm' action='../oauth/authorize' method='post'>
	      <input name='user_oauth_approval' value='true' type='hidden' />
	      <input type='hidden' id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}" />
	      <ul>
	        <#assign keys=scopes?keys>
	      	<#list keys as key>
                <div class='form-group'>${key}:
	            <input type='radio' name='${key}' checked='${scopes[key]}' value='true'>Approve</input>
	            <input type='radio' name='${key}' checked='${scopes[key]}' value='false' checked>Deny</input></div>
            </#list>
	        <!-- <li>
	          <div class='form-group'>scope.webshop:
	            <input type='radio' name='scope.webshop' value='true'>Approve</input>
	            <input type='radio' name='scope.webshop' value='false' checked>Deny</input></div>
	        </li> -->
	      </ul>
	      <label>
	        <input name='authorize' value='Authorize' type='submit' /></label>
	    </form>
	</div>
</body>
</html>
