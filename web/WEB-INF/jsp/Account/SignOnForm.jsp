<%@ include file="../common/top.jsp" %>

<div id="Catalog">
    <form action="SignOnForm" method="post">
        <p>Please enter your username and password.</p>
        <p>
            Username: <input type="text" name="username" required><br>
            Password: <input type="password" name="password" required><br>
            Captcha: <input type="text" name="captcha" required><br>
            <img src="captcha" alt="Captcha" /><br>
        </p>
        <input type="submit" name="SignOn" value="Login" />
    </form>
    Need a username and password?
    <a href="NewAccountForm">Register Now!</a>
</div>

<%@ include file="../common/bottom.jsp" %>

