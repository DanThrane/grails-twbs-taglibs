<%@ page import="dk.danthrane.twbs.Icon; dk.danthrane.twbs.ButtonSize; dk.danthrane.twbs.ButtonStyle; dk.danthrane.twbs.NavBarPlacement; dk.danthrane.twbs.ContextualColor; dk.danthrane.twbs.Validation" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="twbsmain"/>
    <title>TWBS Demo - Buttons</title>
    <style>
        table {
            border-collapse: separate;
            border-spacing: 1em 1em;
        }
    </style>
</head>

<body>
<twbs:row>
    <twbs:column>
        <h1>Buttons</h1>

        <h2>Basic Buttons</h2>
        <table>
            <thead>
                <tr>
                    <th>Native button</th>
                    <th>Anchor button</th>
                </tr>
            </thead>
            <tbody>
            <g:each in="${ButtonStyle.values()}" var="style">
                <tr>
                    <td><twbs:button style="${style}">${style} button</twbs:button></td>
                    <td><twbs:linkButton action="table" style="${style}">${style} button</twbs:linkButton></td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <h2>Disabled Buttons</h2>
        <table>
            <thead>
            <tr>
                <th>Native button</th>
                <th>Anchor button</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${ButtonStyle.values()}" var="style">
                <tr>
                    <td><twbs:button style="${style}" disabled="true">${style} button</twbs:button></td>
                    <td><twbs:linkButton style="${style}" disabled="true">${style} button</twbs:linkButton></td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <h2>Active Buttons</h2>
        <table>
            <thead>
            <tr>
                <th>Native button</th>
                <th>Anchor button</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${ButtonStyle.values()}" var="style">
                <tr>
                    <td><twbs:button style="${style}" active="true">${style} button</twbs:button></td>
                    <td><twbs:linkButton style="${style}" active="true">${style} button</twbs:linkButton></td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <h2>Block Buttons</h2>
        <table>
            <thead>
            <tr>
                <th>Native button</th>
                <th>Anchor button</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${ButtonStyle.values()}" var="style">
                <tr>
                    <td><twbs:button style="${style}" block="true">${style} button</twbs:button></td>
                    <td><twbs:linkButton style="${style}" block="true">${style} button</twbs:linkButton></td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <h2>Different Size Buttons</h2>
        <table>
            <thead>
            <tr>
                <th>Native button</th>
                <th>Anchor button</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${ButtonSize.values()}" var="size">
                <tr>
                    <td><twbs:button size="${size}">${size} button</twbs:button></td>
                    <td><twbs:linkButton size="${size}">${size} button</twbs:linkButton></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </twbs:column>
</twbs:row>
</body>
</html>