<html>
  <head>
    <title>Mail template with freemarker</title>
    <meta name="viewport" content="width=device-width" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
    <h1>Welcome ${name} to MakingDevs!!!</h1>
    <h2>You're in the training course of ${courseName}</h2>
    <h3>This is the agile manifesto</h3>
    <ul>
    <#list manifesto as principle>
      <li>${principle}</li> 
    </#list>
    </ul>
  </body>
</html>
