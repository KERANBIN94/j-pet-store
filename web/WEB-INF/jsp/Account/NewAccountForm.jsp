<%@ include file="../common/top.jsp" %>

<div id="Catalog">
    <h3>User Information</h3>

    <form action="NewAccountForm" method="post">
        <table>
            <tr>
                <td>username:</td>
                <td><input type="text" name="username" required /></td>
            </tr>
            <tr>
                <td>email:</td>
                <td><input type="email" name="email" required /></td>
            </tr>
            <tr>
                <td>password:</td>
                <td><input type="password" name="password" required /></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="password" name="repeatedPassword" required /></td>
            </tr>
        </table>

        <input type="submit" name="NewAccount" value="Create Your Account!" />
    </form>
</div>

<%@ include file="../common/bottom.jsp" %>
