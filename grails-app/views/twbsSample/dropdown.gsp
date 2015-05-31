<%@ page import="dk.danthrane.twbs.NavBarPlacement; dk.danthrane.twbs.ContextualColor; dk.danthrane.twbs.Validation" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="twbsmain"/>
    <title>TWBS Demo - Dropdown</title>
</head>

<body>

<twbs:row>
    <twbs:column>
        <twbs:dropdownButton>
            Test
            <g:content key="dropdown-menu">
                <twbs:dropdownMenu labelledBy="dropdown">
                    <twbs:dropdownHeader>This is a header</twbs:dropdownHeader>
                    <twbs:dropdownItem action="navbar">Hello</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Hello 2</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Hello 3</twbs:dropdownItem>
                    <twbs:dropdownDivider />
                    <twbs:dropdownItem action="navbar">Item </twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Item 2</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Item 3</twbs:dropdownItem>
                </twbs:dropdownMenu>
            </g:content>
        </twbs:dropdownButton>
        <twbs:dropdownButton expanded="true">
            Expanded
            <g:content key="dropdown-menu">
                <twbs:dropdownMenu labelledBy="dropdown">
                    <twbs:dropdownHeader>This is a header</twbs:dropdownHeader>
                    <twbs:dropdownItem action="navbar" disabled="true">Hello</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Hello 2</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Hello 3</twbs:dropdownItem>
                    <twbs:dropdownDivider />
                    <twbs:dropdownItem action="navbar">Item </twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Item 2</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Item 3</twbs:dropdownItem>
                </twbs:dropdownMenu>
            </g:content>
        </twbs:dropdownButton>
        <twbs:dropdownButton dropup="true">
            Test
            <g:content key="dropdown-menu">
                <twbs:dropdownMenu labelledBy="dropdown">
                    <twbs:dropdownHeader>This is a header</twbs:dropdownHeader>
                    <twbs:dropdownItem action="navbar">Hello</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Hello 2</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Hello 3</twbs:dropdownItem>
                    <twbs:dropdownDivider />
                    <twbs:dropdownItem action="navbar">Item </twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Item 2</twbs:dropdownItem>
                    <twbs:dropdownItem action="navbar">Item 3</twbs:dropdownItem>
                </twbs:dropdownMenu>
            </g:content>
        </twbs:dropdownButton>
    </twbs:column>
</twbs:row>

</body>
</html>