 <div class="navigation">
  <#if username??>
    <a href="/">my home</a> |
    <form id="signout" action="/signout" method="get">
      <a href="#" onclick="event.preventDefault(); signout.submit();">sign out [${username}]</a>
    </form>
  <#else>
    <a href="/signin">sign in</a>
  </#if>
 </div>
