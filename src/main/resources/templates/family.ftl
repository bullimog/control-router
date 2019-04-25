<!DOCTYPE html>
<html>
<head>
    <title>Family</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

Family:<br/>
${family.id}<br/>
${family.address1}<br/>
${family.address2}<br/>
<br/><br/>

People:<br/>
---------------<br/>
<#list family.persons as person>
    Person: ${person.id}<br/>
    ${person.first} ${person.last} (Age: ${person.age})<br/>
    ---------------<br/>
</#list>
</body>
</html>