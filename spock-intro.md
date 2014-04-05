# BDD Big Picture

<table>
    <tr>
        <th>Approach</th>
        <th>Framework input</th>
        <th>Source of Truth</th>
        <th>Business Readability</th>
        <th>Frameworks</th>
    </tr>
    <tr>
        <td>Classical BDD</td>
        <td>user story</td>
        <td>user story</td>
        <td>excellent</td>
        <td><a href="http://jbehave.org/">JBehave</a> Java,
            <a href="http://cukes.info/">Cucumber</a> Ruby
        </td>
    </tr>
    <tr>
        <td>Spec-based BDD</td>
        <td>specification</td>
        <td>specification</td>
        <td>moderate</td>
        <td><a href="http://docs.spockframework.org">Spock</a> Groovy,
            <a href="http://jasmine.github.io/">Jasmine</a> JS

        </td>
    </tr>
    <tr>
        <td>TDD</td>
        <td>unit test</td>
        <td>test or src?</td>
        <td>forget</td>
        <td>Junit, <a href="http://testng.org">TestNG</a> </td>
    </tr>
</table>

------------
# Spock Big Picture

* Designed as testing framework for Groovy applications
* Now, Spock integrates seamlessly with Java ecosysten.
  Thanks to its JUnit runner, Spock is compatible with most IDEs, build tools, and CI servers
* Excellent support in IntelliJ IDEA
* Groovy is a dynamic and 'cool' language for JVM. Is it suitable for writing large, enterprise systems?
  Not sure, but its an excellent choice for writing tests against Java code.
