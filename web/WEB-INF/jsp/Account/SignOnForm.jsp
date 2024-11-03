<%@ include file="../common/top.jsp" %>

<div id="Catalog">
    <form action="SignOnForm" method="post">
        <p>Please enter your username and password.</p>
        <p>
            Username: <label>
            <input type="text" name="username" />
        </label><br />
            Password: <label>
            <input type="password" name="password" />
        </label>
        </p>
        <input type="submit" name="SignOn" value="Login" />
    </form>
    Need a username and password?
    <a href="NewAccountForm">Register Now!</a>
</div>

<%@ include file="../common/bottom.jsp" %>

